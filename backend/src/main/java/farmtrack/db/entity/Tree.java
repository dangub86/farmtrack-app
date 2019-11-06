package farmtrack.db.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "trees")
public class Tree {
     enum Age {
        YOUNG(0, 10, "Giovane"),
        MEDIUM(10, 25, "Media"),
        OLD(25, 1000, "Vecchia");

        int min, max;
        String description;
        Age(int min, int max, String description) {
            this.min = min;
            this.max = max;
            this.description = description;
        }

        public static Age getAgeEnumByInt(int age) {
            List<Age> ageValues = Arrays.asList(Age.values());
            return ageValues.stream()
                    .filter(e -> age >= e.min && age < e.max)
                    .findFirst().get();
        }
    }
    String id;
    String name;
    String genre;
    String variety;
    int age;
    Age ageEnum;
    FarmLand land;

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

    public Age getAgeEnum() {
        return ageEnum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        this.ageEnum = Age.getAgeEnumByInt(age);
    }

    public FarmLand getLand() {
        return land;
    }

    public void setLand(FarmLand land) {
        this.land = land;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", variety='" + variety + '\'' +
                ", age=" + age +
                ", land=" + land +
                '}';
    }

}
