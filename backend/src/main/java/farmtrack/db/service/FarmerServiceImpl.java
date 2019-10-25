package farmtrack.db.service;

import farmtrack.db.entity.Farmer;
import farmtrack.db.repo.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    FarmerRepository farmerRepository;

    @Override
    public Farmer getFarmer(String name, String pwd) {
        return farmerRepository.findByPwd(pwd);
    }
}
