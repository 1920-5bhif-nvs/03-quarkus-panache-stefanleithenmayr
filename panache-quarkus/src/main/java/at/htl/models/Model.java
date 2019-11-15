package at.htl.models;

import javax.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;

    //region Constructor
    public Model() {
    }

    public Model(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    //endregion

    //region Getter and Setter Methods
    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //endregion
}