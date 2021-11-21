package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.PaymentProfileController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("payment-profiles")
public class PaymentProfileService {

    @Inject private PaymentProfileController paymentProfileController;

    @GET
    @Path("/{id}}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrievePaymentProfileById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( paymentProfileController.getPaymentProfileById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/customer-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrievePaymentProfilesByCustomerId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( paymentProfileController.getPaymentProfilesByCustomerId(id) )
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
    public Response retrieveAllPaymentProfiles(@QueryParam("from") int idx_start,
                                               @QueryParam("to") int idx_end,
                                               @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( paymentProfileController.getAllPaymentProfiles(idx_start, idx_end-idx_start, orderBy) )
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
    public Response addNewPaymentProfile(String json) {
        try {
            paymentProfileController.addPaymentProfileToDb(json);
            return Response.status(Response.Status.OK).entity("New payment profile added").build();
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
    public Response updatePaymentProfile(String json, @QueryParam("id") Long customerID) {
        try {
            paymentProfileController.updatePaymentProfileOnDb(json, customerID);
            return Response.status(Response.Status.OK).entity("Payment profile " + customerID + " correctly updated").build();
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
    public Response disablePaymentProfile(@QueryParam("id") Long id) {
        try {
            paymentProfileController.disablePaymentProfileOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Payment profile " + id + " correctly disabled").build();
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
    public Response restorePaymentProfile(@QueryParam("id") Long id) {
        try {
            paymentProfileController.disablePaymentProfileOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Payment profile " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
