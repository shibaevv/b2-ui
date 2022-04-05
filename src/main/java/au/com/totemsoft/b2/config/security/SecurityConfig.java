package au.com.totemsoft.b2.config.security;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.reactive.function.client.WebClient;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2
public class SecurityConfig extends AADWebSecurityConfigurerAdapter /* WebSecurityConfigurerAdapter */ {

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    //@Autowired
    //private AADB2COidcLoginConfigurer configurer;

    @Autowired
    private B2AuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("test@company.com").password(passwordEncoder().encode("Passw0rd")).roles("USER")
            .and()
            .withUser("admin@company.com").password(passwordEncoder().encode("Passw0rd")).roles("ADMIN");
        ;
    }

    @Bean
    public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
            new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
        return WebClient.builder()
            .filter(oauth2).build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return request -> {
            OAuth2User user = delegate.loadUser(request);
            String registrationId = request.getClientRegistration().getRegistrationId();
            if (!"google".equals(registrationId)) {
                return user;
            }

            OAuth2AuthorizedClient client = new OAuth2AuthorizedClient
                (request.getClientRegistration(), user.getName(), request.getAccessToken());
            String url = user.getAttribute("organizations_url");
            List<Map<String, Object>> orgs = rest
                .get().uri(url)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(List.class)
                .block();
            if (orgs.stream().anyMatch(org -> "spring-projects".equals(org.get("login")))) {
                return user;
            }

            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token", "Not in Spring Team", ""));
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
//        http.authorizeRequests()
//            .anyRequest().authenticated();
        // Do some custom configuration

        final SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler(contextPath);
        http
            .csrf(c -> c
                .disable()
                //.csrfTokenRepository(org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .antMatcher("/**")
            .authorizeRequests(a -> {
                try {
                    a
                        //.antMatchers("/admin/**").hasRole("ADMIN")
                        //.antMatchers("/anonymous*").anonymous()
                        .antMatchers("/index*").permitAll()
                        .antMatchers(HttpMethod.GET, "/", "/health", "/error", "/webjars/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/static/**", "/*.js", "/*.json", "/*.ico").permitAll()
                        .anyRequest().authenticated()
                        .and()
                            .httpBasic()
                            .authenticationEntryPoint(authEntryPoint)
                        .and()
                            .formLogin()
                            .loginPage("/index.html")
                            .loginProcessingUrl("/perform_login")
                            .defaultSuccessUrl("/index.html", true) // redirect
                            .failureUrl("/index.html?error=true") // redirect
                        .and()
                            .logout()
                            .logoutUrl("/perform_logout")
                            .deleteCookies("JSESSIONID", "authenticated")
                            .invalidateHttpSession(true)
                            .logoutSuccessUrl("/") // redirect
                            //.logoutSuccessHandler(logoutSuccessHandler())
                        ;
                } catch (Exception ex) {
                    log.error(ex);
                }
            })
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .logout(l -> l
                .logoutSuccessUrl("/").permitAll()
            )
            .oauth2Login(o -> o
                .failureHandler((request, response, exception) -> {
                    request.getSession().setAttribute("error.message", exception.getMessage());
                    handler.onAuthenticationFailure(request, response, exception);
                })
            )
            //.apply(configurer)
            //.portMapper().http(8080).mapsTo(8443)
        ;
    }

}
