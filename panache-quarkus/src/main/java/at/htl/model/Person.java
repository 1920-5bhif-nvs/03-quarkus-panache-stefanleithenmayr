package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends PanacheEntity {
    public String firstName, lastName;
    public LocalDate birth;
    public String phoneNumber;
    public String address;
    public String email;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Car> cars;

    public Person() {
        this.cars = new ArrayList<>();
    }

    public Person(String firstName, String lastName, LocalDate birth, String phoneNumber, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        if (cars == null){
            cars = new ArrayList<>();
        }
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
