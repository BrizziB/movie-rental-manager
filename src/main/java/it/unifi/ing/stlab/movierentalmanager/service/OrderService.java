package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.OrderController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("orders")
public class OrderService {

    @Inject private OrderController orderController;

    @GET
    @Path("/searchById")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveOrderById(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( orderController.getOrderById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByDate")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveOrderByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( orderController.getOrderBetweenDates(start, end) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllOrders() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( orderController.getAllOrders(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response addNewOrder(String json) {
        try {
            orderController.addOrderToDb(json);
            return Response.ok("New order added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateOrder(String json, @QueryParam("id") Long id) {
        try {
            orderController.updateOrderOnDb(json, id);
            return Response.ok("Order " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableOrder(@QueryParam("id") Long id) {
        try {
            orderController.disableOrderOnDb(true, id);
            return Response.ok("Order " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreOrder(@QueryParam("id") Long id) {
        try {
            orderController.disableOrderOnDb(false, id);
            return Response.ok("Order " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
