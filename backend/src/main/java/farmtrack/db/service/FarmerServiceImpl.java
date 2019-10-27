package farmtrack.db.service;

import farmtrack.db.entity.Farmer;
import farmtrack.db.repo.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    FarmerRepository farmerRepository;

    @Override
    public Farmer getFarmer(String email, String pwd) {
        Farmer farmer = farmerRepository.findByPwd(pwd);

        return isValid(farmer, email) ? farmer : null;
    }

    @Override
    public void save(Farmer newFarmer) {
        farmerRepository.save(newFarmer);
    }

    private boolean isValid(Farmer farmer, String email) {
        return !Objects.isNull(farmer) && farmer.getFarmer_email().equalsIgnoreCase(email);
    }
}
