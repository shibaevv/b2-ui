package au.com.totemsoft.b2.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class WebConfig implements WebMvcConfigurer {

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Autowired
    private ServletContext servletContext;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("servletContext.realPath={}", servletContext.getRealPath("/"));
        registry.addViewController(contextPath).setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler(contextPath + "*.*")
            .addResourceLocations("/", "/WEB-INF/view/react/build/");
        registry
            .addResourceHandler(contextPath + "static/**")
            .addResourceLocations("/", "/static/", "/WEB-INF/view/react/build/static/");
    }

    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                updateLocation(request, response);
            }
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                updateLocation(request, response);
            }
        });
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response) {
        if (request.getRequestURI().contains("perform_login")) {
            String location = response.getHeader(HttpHeaders.LOCATION);
            log.debug("http header location: {}", location);
            if (location != null && location.startsWith("http://")) {
                location = location.replace("http://", "https://");
                log.debug("updating http header location: {}", location);
                response.setHeader(HttpHeaders.LOCATION, location);
            }
        }
    }
    */

}
