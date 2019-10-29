package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;

public interface FarmlandService {
    FarmLand getLandByName(String name);
    void save(FarmLand farmLand);
}
