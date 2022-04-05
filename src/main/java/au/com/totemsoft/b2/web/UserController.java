package au.com.totemsoft.b2.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    //public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    public Map<String, Object> user(@AuthenticationPrincipal OidcUser principal) {
        return Collections.singletonMap("name",
            principal != null ? principal.getAttribute("name") : "UNKNOWN");
    }

}
