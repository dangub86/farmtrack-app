package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;

import java.util.List;

public interface FarmlandService {
    FarmLand getLandByName(String name);
    void save(FarmLand farmLand);

    List<FarmLand> getLands();
}
