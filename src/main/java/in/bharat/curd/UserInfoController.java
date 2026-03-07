package in.bharat.curd;

import in.bharat.curd.entity.UserInfo;
import in.bharat.curd.model.GreetingReq;
import in.bharat.curd.model.UserInfoRequest;
import in.bharat.curd.repo.UserInfoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoRepo userInfoRepo;

    public UserInfoController(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    @GetMapping("/")
    public List<UserInfo> getUserInfo() {
        return userInfoRepo.findAll();
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public String addGreeting(@RequestBody UserInfoRequest req) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(req.getName());
        userInfo.setEmail(req.getEmail());
        userInfo.setMobileNo(req.getMobileNo());
        userInfo.setPassword(req.getPassword());
        userInfoRepo.save(userInfo);
        return "Greeting Added";
    }
//
//    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
//    public String updateGreeting(@RequestBody GreetingReq req) {
//        Optional great = list.stream().filter(req1 -> req1.id().equals(req.id())).findFirst();
//        if (great.isPresent()) {
//            log.info("updateGreeting : ", req.greeting());
//            list.remove(great.get());
//        }
//        list.add(req);
//        return "Greeting Updated";
//    }
//
//    @DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
//    public String deleteGreeting(@RequestBody GreetingReq req) {
//        list.add(req);
//        log.info("deleteGreeting : ", req.greeting());
//        list.remove(req);
//        return "Greeting Deleted";
//    }
}
