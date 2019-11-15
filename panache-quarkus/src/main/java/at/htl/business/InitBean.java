package at.htl.business;

import at.htl.models.*;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent ev){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Customer customer = new Customer("Franz", "Muster", LocalDate.parse("1997-03-10"), "+234524552", "Musterstraße 99", "franz.muster@muster.at", LocalDate.parse("2018-04-13", formatter));
        em.persist(customer);
        Employee emp = new Employee(2500, 1234010180L, LocalDate.parse("2010-05-10", formatter), "Max",
                "Mustermann", LocalDate.parse("1980-01-10", formatter), "+12345678910", "Musterstrasse 3", "x.muster@muster.at");
        em.persist(emp);
        Model model = new Model("Audi", "A4");
        em.persist(model);
        Car car = new Car("WDD 169 007-1J-236589",101032,LocalDate.parse("2014-05-01"), false, 12500, model);
        em.persist(car);
        Trade trade = new Trade(car, customer, emp, 'V', 10000, LocalDate.parse("2010-05-10", formatter));
        em.persist(trade);
        emp = new Employee(1750, 6789010180L, LocalDate.parse("2010-05-10", formatter), "Susi",
                "Musterfrau", LocalDate.parse("1985-01-10", formatter), "+12345678910", "Gasse 7", "x.muster@muster.at");
        em.persist(emp);
        emp = new Employee(2000,4321010180L, LocalDate.parse("2010-05-10", formatter), "Tim",
                "Mayr", LocalDate.parse("2000-01-10", formatter), "+12345678910", "Hofhausen 3", "x.muster@muster.at");
        em.persist(emp);
    }
}