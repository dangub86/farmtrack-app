package farmtrack.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import farmtrack.config.MongoConfig;
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

    @RequestMapping(path = "/restlogin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String accessoSocio (@RequestParam("utente") String requestNomeUtente, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller utente: "+ requestNomeUtente);
        Farmer farmer = farmerService.getFarmer(requestNomeUtente, requestPassword);
        System.out.println(farmer);
        List<Farmer> users = farmerRepository.findAll(new Sort(Sort.Direction.ASC, "farmer_name"));
        System.out.println(users);
        if(!Objects.isNull(farmer)){
            return farmer.getFarmer_name();
        }
        else{
            System.out.println("nome utente o password errati");
            return "nome utente o password errati";

        }
    }
  

}
