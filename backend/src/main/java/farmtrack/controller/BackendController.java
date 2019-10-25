package farmtrack.controller;

import farmtrack.db.entity.Farmer;
import farmtrack.db.service.FarmerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private FarmerService farmerService;

    @RequestMapping(path = "/restlogin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String accessoSocio (@RequestParam("utente") String requestNomeUtente, @RequestParam("password")  String requestPassword) {
        System.out.println("logincontroller password: "+ requestPassword);
        System.out.println("logincontroller utente: "+ requestNomeUtente);
        Farmer farmer = farmerService.getFarmer(requestNomeUtente, requestPassword);
        System.out.println(farmer);
        if(!Objects.isNull(farmer)){
            return farmer.getFarmer_name();
        }
        else{
            System.out.println("nome utente o password errati");
            return "nome utente o password errati";
        }
    }
  

}
