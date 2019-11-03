package farmtrack.controller;

import farmtrack.db.entity.FarmLand;
import farmtrack.db.entity.Farmer;
import farmtrack.db.entity.Tree;
import farmtrack.db.service.FarmerService;
import farmtrack.db.service.FarmlandService;
import farmtrack.db.service.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    private FarmlandService farmlandService;

    @Autowired
    private TreeService treeService;


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
            return "";

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

    @RequestMapping(path = "/addLand", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody boolean addLand(@RequestParam("name") String name, @RequestParam("unity") String unity,
                                         @RequestParam("height") int height, @RequestParam("width") int width,
                                         @RequestParam("gradient") int gradient, @RequestParam("composition") String composition ) {

        System.out.println("Adding land with name " + name);
        FarmLand farmLand = new FarmLand();
        farmLand.setName(name);
        farmLand.setUnity(unity);
        farmLand.setHeight(height);
        farmLand.setWidth(width);
        farmLand.setGradient(gradient);
        farmLand.setComposition(composition);
        farmlandService.save(farmLand);

        return true;
    }

    @RequestMapping(path = "/addTree", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody boolean addTree(@RequestParam("name") String name, @RequestParam("genre") String genre,
                                         @RequestParam("variety") String variety, @RequestParam("age") Tree.Age age,
                                         @RequestParam("landName") String landName ) {

        FarmLand farmLand = farmlandService.getLandByName(landName);
        System.out.println("Farmland: " + farmLand);
        System.out.println("Adding tree with name " + name);
        Tree tree = new Tree();
        tree.setName(name);
        tree.setGenre(genre);
        tree.setVariety(variety);
        tree.setAge(age);
        tree.setLand(farmLand);
        treeService.save(tree);

        farmLand.addTree(tree);
        farmlandService.save(farmLand);

        return true;
    }

    @RequestMapping(path="/lands", method = RequestMethod.GET)
    public @ResponseBody
    List<FarmLand> getSoci() {
        return farmlandService.getLands();
    }
}
