package farmtrack.db.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lands")
public class FarmLand {
    String id;
    String name;
    String unity;
    int height;
    int width;
    int gradient;
    String composition;

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
                '}';
    }
}
