package farmtrack.db.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trees")
public class Tree {
    public enum Age {
        YOUNG, MEDIUM, OLD;
    }
    String id;
    String name;
    String genre;
    String variety;
    Age age;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", variety='" + variety + '\'' +
                ", age=" + age +
                '}';
    }
}
