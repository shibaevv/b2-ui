package au.com.totemsoft.b2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${openapi.connectFlowProduce.base-path:/}")
public class HomeController implements HealthIndicator {

    //@GetMapping("/")
    public String index() {
        return "Greetings from \"Connect Flow Produce\"!";
    }

    @GetMapping(path = "/health",
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Override
    public Health health() {
        try {
            return Health.up()
                .withDetail("version", "1.0.0-SNAPSHOT")
                .build();
        }
        catch (Exception ex) {
            return Health.down(ex).build();
        }
    }

    @GetMapping("/error")
    @ResponseBody
    public String error(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("error.message");
        session.removeAttribute("error.message");
        return message;
    }

}
