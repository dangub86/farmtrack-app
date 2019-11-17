package farmtrack.db.repo;

import farmtrack.db.entity.FarmLand;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmlandRepository extends MongoRepository<FarmLand, String> {
    FarmLand getFarmLandByName(String name);
    FarmLand getFarmLandById(ObjectId id);
    List<FarmLand> getFarmLandByFarmer(String farmerId);
}
