package farmtrack.db.entity;

import org.hibernate.search.annotations.DocumentId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "farmers")
public class Farmer {

    String id;
    String farmer_name;
    String farmer_surname;
    String farmer_email;
    @Field("farmer_pwd")
    String pwd;

    public Farmer() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getFarmer_surname() {
        return farmer_surname;
    }

    public void setFarmer_surname(String farmer_surname) {
        this.farmer_surname = farmer_surname;
    }

    public String getFarmer_email() {
        return farmer_email;
    }

    public void setFarmer_email(String farmer_email) {
        this.farmer_email = farmer_email;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "farmer_name='" + farmer_name + '\'' +
                ", farmer_surname='" + farmer_surname + '\'' +
                ", farmer_email='" + farmer_email + '\'' +
                ", farmer_pwd='" + pwd + '\'' +
                '}';
    }
}
