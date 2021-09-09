package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.PaymentProfileController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payment-profiles")
public class PaymentProfileService {

    @Inject private PaymentProfileController paymentProfileController;

    @GET
    @Path("/searchById")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrievePaymentProfileById(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( paymentProfileController.getPaymentProfileById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByCustomer")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrievePaymentProfilesByCustomerId(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( paymentProfileController.getPaymentProfilesByCustomerId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllPaymentProfiles() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( paymentProfileController.getAllPaymentProfiles(0, 25) )
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
    public Response addNewPaymentProfile(String json) {
        try {
            paymentProfileController.addPaymentProfileToDb(json);
            return Response.ok("New payment profile added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updatePaymentProfile(String json, @QueryParam("id") Long id) {
        try {
            paymentProfileController.updatePaymentProfileOnDb(json, id);
            return Response.ok("Payment profile " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disablePaymentProfile(@QueryParam("id") Long id) {
        try {
            paymentProfileController.disablePaymentProfileOnDb(true, id);
            return Response.ok("Payment profile " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restorePaymentProfile(@QueryParam("id") Long id) {
        try {
            paymentProfileController.disablePaymentProfileOnDb(false, id);
            return Response.ok("Payment profile " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
