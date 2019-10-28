package farmtrack.controller;

import farmtrack.db.entity.Farmer;
import farmtrack.db.repo.FarmerRepository;
import farmtrack.db.service.FarmerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api")
@EnableMongoRepositories(basePackages = "farmtrack.db.repo")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private FarmerRepository farmerRepository;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String login (@RequestParam("email") String requestEmail, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller email: "+ requestEmail);
        Farmer farmer = farmerService.getFarmer(requestEmail, requestPassword);
        if(!Objects.isNull(farmer)){
            System.out.println(farmer.getId());
            return farmer.getId();
        }
        else{
            System.out.println("nome utente o password errati");
            return "nome utente o password errati";

        }
    }

    @RequestMapping(path = "/sign", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody boolean signiIn (@RequestParam("name") String name, @RequestParam("surname")  String surname,
        @RequestParam("email") String email, @RequestParam("pwd") String pwd ) {
        System.out.println("signincontroller name: "+ name);
        System.out.println("signincontroller surname: "+ surname);
        System.out.println("signincontroller email: "+ email);
        System.out.println("signincontroller pwd: "+ pwd);
        Farmer newFarmer = new Farmer();
        newFarmer.setFarmer_name(name);
        newFarmer.setFarmer_surname(surname);
        newFarmer.setFarmer_email(email);
        newFarmer.setPwd(pwd);
        farmerService.save(newFarmer);

        return true;
    }
  

}
