package farmtrack.db.repo;

import farmtrack.db.entity.Farmer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmerRepository extends MongoRepository<Farmer, String> {
    Farmer findByPwd(String pwd);
}