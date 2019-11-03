package farmtrack.db.service;

import farmtrack.db.entity.FarmLand;
import farmtrack.db.repo.FarmlandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
public class FarmlandServiceImpl implements FarmlandService {

    @Autowired
    private FarmlandRepository farmlandRepository;

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
    public List<FarmLand> getLands() {
        return farmlandRepository.findAll();
    }
}
