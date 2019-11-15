package at.htl.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select e from Employee e"),
})
public class Employee extends Person {

    public double salary;
    public Long socialNumber;
    public LocalDate employeedSince;

    //region Constructur
    public Employee() {
    }

    public Employee(double salary, Long socialNumber, LocalDate employeedSince, String firstName, String lastName, LocalDate birth, String phoneNumber, String address, String email) {
        super(firstName, lastName, birth, phoneNumber, address, email);
        this.salary = salary;
        this.socialNumber = socialNumber;
        this.employeedSince = employeedSince;
    }
    //endregion
}
