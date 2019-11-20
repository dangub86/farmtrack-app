package farmtrack.db.repo;

import farmtrack.db.entity.Tree;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends MongoRepository<Tree, String> {
    Tree getTreeByName(String name);
    Tree getTreeById(ObjectId id);
}
