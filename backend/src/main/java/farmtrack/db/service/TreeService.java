package farmtrack.db.service;

import farmtrack.db.entity.Tree;

import java.util.List;

public interface TreeService {
    Tree save(Tree tree);
    List<Tree> getTrees();
}
