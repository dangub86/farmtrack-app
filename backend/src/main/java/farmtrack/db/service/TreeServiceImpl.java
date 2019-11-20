package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;
import farmtrack.db.entity.Tree;
import farmtrack.db.repo.TreeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepository treeRepository;

    @Override
    public Tree save(Tree tree) {
        if (Objects.isNull(tree)) {
            throw new IllegalArgumentException("Tree must not be null");
        }
        return treeRepository.save(tree);
    }

    @Override
    public List<Tree> getTrees() {
        return treeRepository.findAll();
    }

    @Override
    public Tree getTreeById(String id) {
        System.out.println(id);
        ObjectId oId = new ObjectId(id);
        System.out.println(oId);
        return treeRepository.getTreeById(oId);
    }
}
