package in.bharat.curd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    List<GreetingReq> list = new ArrayList<>();

    @GetMapping("/")
    public List<GreetingReq> hello() {
        log.info("Greeting List: ", list.toString());

        return list;
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public String addGreeting(@RequestBody GreetingReq req) {
        list.add(req);
        log.info("sayHello : ", req.greeting());
        return "Greeting Added";
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String updateGreeting(@RequestBody GreetingReq req) {
        Optional great = list.stream().filter(req1 -> req1.id().equals(req.id())).findFirst();
        if (great.isPresent()) {
            log.info("updateGreeting : ", req.greeting());
            list.remove(great.get());
        }
        list.add(req);
        return "Greeting Updated";
    }

    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
    public String deleteGreeting(@RequestBody GreetingReq req) {
        list.add(req);
        log.info("deleteGreeting : ", req.greeting());
        list.remove(req);
        return "Greeting Deleted";
    }
}
