package farmtrack.db.entity;

import org.hibernate.search.annotations.DocumentId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Farmer {
    @DocumentId
    String id;

    String farmer_name;
    String farmer_surname;
    String farmer_email;
    String farmer_pwd;

    public Farmer() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFarmer_pwd() {
        return farmer_pwd;
    }

    public void setFarmer_pwd(String farmer_pwd) {
        this.farmer_pwd = farmer_pwd;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id='" + id + '\'' +
                ", farmer_name='" + farmer_name + '\'' +
                ", farmer_surname='" + farmer_surname + '\'' +
                ", farmer_email='" + farmer_email + '\'' +
                ", farmer_pwd='" + farmer_pwd + '\'' +
                '}';
    }
}
