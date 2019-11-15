package at.htl.panachemodels;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select c from Car c"),
        @NamedQuery(name = "Car.findSold", query = "select c from Car c where c.isSold = true")
})
public class Car extends PanacheEntity {

    public String chassisNumber;
    public int mileage; //Kilometerstand
    public LocalDate firstRegistration; //Erstzulassung
    public boolean isSold; //Wurde das Auto bereits verkauft
    public int priceExpected; //Erwarteter Preis

    @ManyToOne (cascade = CascadeType.ALL)
    public Model model; //Legt das Automodell fest z.B. Audi A4

    //region Constructor
    public Car() {
    }

    public Car(String chassisNumber, int mileage, LocalDate firstRegistration, boolean isSold, int priceExpected, Model model) {
        this.chassisNumber = chassisNumber;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
        this.isSold = isSold;
        this.priceExpected = priceExpected;
        this.model = model;
    }

    public Car(int mileage, LocalDate firstRegistration, boolean isSold, int priceExpected) {
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
        this.isSold = isSold;
        this.priceExpected = priceExpected;
    }

    public Car(LocalDate firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public Car(int mileage) {
        this.mileage = mileage;
    }
    //endregion
}