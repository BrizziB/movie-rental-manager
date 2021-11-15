package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.OrderController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("orders")
public class OrderService {

    @Inject private OrderController orderController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveOrderById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( orderController.getOrderById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/customer/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveOrdersByCustomerId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( orderController.getOrdersByCustomerID(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/orders-dates")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveOrderByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( orderController.getOrderBetweenDates(start, end) )
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
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveAllOrders() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( orderController.getAllOrders(0, 25) )
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
    public Response addNewOrder(String json, @QueryParam("id") Long customerID) {
        try {
            orderController.addOrderToDb(json, customerID);
            return Response.status(Response.Status.OK).entity("New order added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    public Response updateOrder(String json, @QueryParam("id") Long customerID) {
        try {
            orderController.updateOrderOnDb(json, customerID);
            return Response.status(Response.Status.OK).entity("Order " + customerID + " correctly updated").build();
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
    public Response disableOrder(@QueryParam("id") Long customerID) {
        try {
            orderController.disableOrderOnDb(true, customerID);
            return Response.status(Response.Status.OK).entity("Order " + customerID + " correctly disabled").build();
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
    public Response restoreOrder(@QueryParam("id") Long customerID) {
        try {
            orderController.disableOrderOnDb(false, customerID);
            return Response.status(Response.Status.OK).entity("Order " + customerID + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
