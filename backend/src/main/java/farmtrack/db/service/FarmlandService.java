package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;
import farmtrack.db.entity.Tree;
import org.bson.types.ObjectId;

import java.util.List;

public interface FarmlandService {
    FarmLand getLandByName(String name);
    void save(FarmLand farmLand);
    List<FarmLand> getLands();
    FarmLand getLandById(String land_id);
    boolean hasTree(String land_id);
    List<Tree> getTreesByLand(String land_id);
}
