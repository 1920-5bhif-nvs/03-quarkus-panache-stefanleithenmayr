package at.htl.rest;

import at.htl.model.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeEndpoint {

    @Inject
    EntityManager em;

    //Create
    @POST
    @Path("insertEmployee")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmployee(Employee employee) {
        em.persist(employee);
        return Response.ok().entity(employee).build();
    }

    //Read - Methods
    @GET
    @Path("getEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees(){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
        return query.getResultList();
    }

    @GET
    @Path("getEmployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") long id) {
        return em.find(Employee.class, id);
    }

    //Update Methods
    @PUT
    @Path("/updateEmployee/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") long id, Employee updatedEmployee) {
        if (updatedEmployee == null || em.find(Employee.class, id) == null){
            return Response.serverError().build();
        }
        updatedEmployee.id = id;
        em.merge(updatedEmployee);
        return Response.ok().entity(em.find(Employee.class, id)).build();
    }

    //Delete Methods
    @DELETE
    @Transactional
    @Path("deleteEmployee/{id}")
    public void deleteEmployee(@PathParam("id") long id) {
        Employee employee = em.find(Employee.class, id);
        if(employee != null) {
            em.remove(em.contains(employee) ? employee : em.merge(employee));
        }
    }
}