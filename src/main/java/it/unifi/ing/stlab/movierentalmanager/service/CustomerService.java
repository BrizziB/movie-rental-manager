package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.CustomerController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customers")
public class CustomerService {

    @Inject private CustomerController customerController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveCustomerById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( customerController.getCustomerById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/customers-names")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveCustomerByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( customerController.getCustomersByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/all")
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllCustomers() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( customerController.getAllCustomers(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response addNewCustomer(String json) {
        try {
            customerController.addCustomerToDb(json);
            return Response.status(Response.Status.OK).entity("New customer added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/update")
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateCustomer(String json, @QueryParam("id") Long id) {
        try {
            customerController.updateCustomerOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Customer " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response disableCustomer(@QueryParam("id") Long id) {
        try {
            customerController.disableCustomerOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Customer " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response restoreCustomer(@QueryParam("id") Long id) {
        try {
            customerController.disableCustomerOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Customer " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
