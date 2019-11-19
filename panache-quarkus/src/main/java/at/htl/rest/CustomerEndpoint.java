package at.htl.rest;

import at.htl.model.Customer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
public class CustomerEndpoint {

    @Inject
    EntityManager em;

    //Create
    @POST
    @Path("insertCustomer")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(Customer customer) {
        em.persist(customer);
        return Response.ok().entity(customer).build();
    }

    //Read - Methods
    @GET
    @Path("getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @GET
    @Path("getCustomer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") long id) {
        return em.find(Customer.class, id);
    }

    //Update Methods
    @PUT
    @Path("/updateCustomer/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id, Customer updatedCustomer) {
        if (updatedCustomer == null || em.find(Customer.class, id) == null){
            return Response.serverError().build();
        }
        updatedCustomer.id = id;
        em.merge(updatedCustomer);
        return Response.ok().entity(em.find(Customer.class, id)).build();
    }

    //Delete Methods
    @DELETE
    @Transactional
    @Path("deleteCustomer/{id}")
    public void deleteCustomer(@PathParam("id") long id) {
        Customer customer = em.find(Customer.class, id);
        if(customer != null) {
            em.remove(em.contains(customer) ? customer : em.merge(customer));
        }
    }
}