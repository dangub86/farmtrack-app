package farmtrack.db.service;

import farmtrack.db.entity.Farmer;

public interface FarmerService {

    Farmer getFarmer(String name, String pwd);
}
