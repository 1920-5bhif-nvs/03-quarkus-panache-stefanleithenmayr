package at.htl.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select c from Car c"),
        @NamedQuery(name = "Car.findSold", query = "select c from Car c where c.isSold = true")
})
public class Car {
    @Id
    private String chassisNumber;

    private int mileage; //Kilometerstand
    private LocalDate firstRegistration; //Erstzulassung
    private boolean isSold; //Wurde das Auto bereits verkauft
    private int priceExpected; //Erwarteter Preis

    @ManyToOne (cascade = CascadeType.ALL)
    private Model model; //Legt das Automodell fest z.B. Audi A4

    public Car() {
    }

    public Car(String chassisNumber, int mileage, LocalDate firstRegistration, boolean isSold, int priceExpected, Model model) {
        this.chassisNumber = chassisNumber;
        this.mileage = mileage;
        this.firstRegistration = firstRegistration;
        this.isSold = isSold;
        this.priceExpected = priceExpected;
        this.model = model;
        //this.from = from;
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

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDate firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public int getPriceExpected() {
        return priceExpected;
    }

    public void setPriceExpected(int priceExpected) {
        this.priceExpected = priceExpected;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}