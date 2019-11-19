package at.htl.rest;

import at.htl.business.hibernate.CarFacade;
import at.htl.model.Car;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cars")
public class CarsEndpoint {
    @Inject
    EntityManager em;

    @Inject
    CarFacade carFacade;

    //region Create
    @POST
    @Path("/insertCar")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCar(Car car) {
        em.persist(car);
        return Response.ok().entity(car).build();
    }
    //endregion

    //region Read - Methods
    @GET
    @Path("/getCars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars(){
        return carFacade.getAll();
    }

    @GET
    @Path("/getSoldCars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getSoldCars(){
        TypedQuery<Car> query = em.createNamedQuery("Car.findSold", Car.class);
        return query.getResultList();
    }

    @GET
    @Path("/getCar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("id") long id) {
        return em.find(Car.class, id);
    }
    //endregion

    //region Update Methods
    @PUT
    @Path("/updateCar/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") String id, Car updatedCar) {
        if (updatedCar == null || em.find(Car.class, id) == null){
            return Response.serverError().build();
        }
        updatedCar.setChassisNumber(id);
        em.merge(updatedCar);
        return Response.ok().entity(em.find(Car.class, id)).build();
    }
    //endregion

    //region Delete Methods
    @DELETE
    @Transactional
    @Path("/deleteCar/{id}")
    public void deleteCar(@PathParam("id") long id) {
        Car car = em.find(Car.class, id);
        if(car != null) {
            em.remove(em.contains(car) ? car : em.merge(car));
        }
    }
    //endregion
}