package at.htl.panachemodels;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model extends PanacheEntity {
    public String brand;
    public String model;
    //region Constructor
    public Model() {
    }

    public Model(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    //endregion
}