package farmtrack.db.repo;

import farmtrack.db.entity.FarmLand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmlandRepository extends MongoRepository<FarmLand, String> {
    FarmLand getFarmLandByName(String name);
}
