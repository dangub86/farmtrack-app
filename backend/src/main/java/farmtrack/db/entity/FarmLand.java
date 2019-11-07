package farmtrack.db.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document(collection = "lands")
public class FarmLand {
    String id;
    String name;
    String unity;
    int height;
    int width;
    int gradient;
    String composition;
    List<String> treeList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getGradient() {
        return gradient;
    }

    public void setGradient(int gradient) {
        this.gradient = gradient;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public List<String> getTreeList() {
        List<String> listCopy = new ArrayList<>(treeList);
        return listCopy;
    }

    public boolean addTree(String treeId) {
        if (!treeList.contains(treeId)) {
            this.treeList.add(treeId);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "FarmLand{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unity='" + unity + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", gradient=" + gradient +
                ", composition='" + composition + '\'' +
                ", treeList=" + treeList +
                '}';
    }
}
