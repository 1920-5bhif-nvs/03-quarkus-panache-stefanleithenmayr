package at.htl.business.hibernate;

import at.htl.model.Car;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CarFacade extends BaseFacade<Car>{

    @Override
    public List<Car> getAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public Car getById(Long id){
        return em.find(Car.class, id);
    }
}
