package farmtrack.db.service;

import farmtrack.db.entity.Farmer;

public interface FarmerService {

    Farmer getFarmer(String email, String pwd);

    void save(Farmer newFarmer);
}
