package in.bharat.curd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping
    public String hello() {
        log.info("Hello Spring ");
        return "Hello Spring";
    }

    @GetMapping("/{name}")
    public String hello(@PathVariable String name) {

        return "Hello Spring";
    }

    @PostMapping("/greet")
    public String sayHello(GreetingReq req) {
        log.info("sayHello : ",req.greeting());
        return "Hello Spring: "+req.greeting();
    }
}
