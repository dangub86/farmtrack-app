package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;
import farmtrack.db.entity.Tree;
import farmtrack.db.repo.FarmlandRepository;
import farmtrack.db.repo.TreeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FarmlandServiceImpl implements FarmlandService {

    @Autowired
    private FarmlandRepository farmlandRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Override
    public FarmLand getLandByName(String name) {
        return farmlandRepository.getFarmLandByName(name);
    }

    @Override
    public void save(FarmLand farmLand) {
        if (Objects.isNull(farmLand)) {
            throw new IllegalArgumentException("Farmland must not be null");
        }
        farmlandRepository.save(farmLand);
    }

    @Override
    public List<FarmLand> getLands(String farmer) {
        return farmlandRepository.getFarmLandByFarmer(farmer);
    }

    @Override
    public FarmLand getLandById(String land_id) {
        System.out.println(land_id);
        ObjectId oId = new ObjectId(land_id);
        System.out.println(oId);
        return farmlandRepository.getFarmLandById(oId);
    }

    @Override
    public boolean hasTree(String land_id) {
        ObjectId oId = new ObjectId(land_id);
        FarmLand farmland = farmlandRepository.getFarmLandById(oId);

        return farmland.getTreeList().size() > 0;
    }

    @Override
    public List<Tree> getTreesByLand(String land_id) {
        ObjectId oId = new ObjectId(land_id);
        FarmLand farmland = farmlandRepository.getFarmLandById(oId);

        return farmland.getTreeList().stream()
                .map(e -> treeRepository.findById(e))
                .map(e -> e.get())
                .collect(Collectors.toList());
    }
}
