package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Trade extends PanacheEntity {
    @OneToOne (cascade = CascadeType.ALL)
    public Car car;
    @OneToOne (cascade = CascadeType.ALL)
    public Customer customer;
    @OneToOne (cascade = CascadeType.ALL)
    public Employee employee;

    public char type; //S == Sold, B == Buyed
    public int price; //Wenn Type ein B ist, dann ist das der Einkaufspreis und wenn es ein S ist der Verkaufspreis

    public LocalDate tradeDate;

    public Trade() {
    }

    public Trade(Car car, Customer customer, Employee employee, char type, int price, LocalDate tradeDate) {
        this.car = car;
        this.customer = customer;
        this.employee = employee;
        this.type = type;
        this.price = price;
        this.tradeDate = tradeDate;
        this.customer.getCars().add(this.car);
    }
}
