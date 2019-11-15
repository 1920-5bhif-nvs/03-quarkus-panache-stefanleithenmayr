package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

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