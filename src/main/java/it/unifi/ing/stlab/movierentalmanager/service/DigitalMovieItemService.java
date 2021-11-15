package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.DigitalMovieItemController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("digital-movie-item")
public class DigitalMovieItemService {

    @Inject private DigitalMovieItemController digitalMovieItemController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveDigitalMovieItemById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/movies-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveDigitalMovieItemsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/movies-titles")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrieveDigitalMovieItemsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemsByMovieTitle(title) )
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
    public Response retrieveAllDigitalMovieItems() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( digitalMovieItemController.getAllDigitalMovieItems(0, 25) )
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
    public Response addNewDigitalMovieItem(String json) {
        try {
            digitalMovieItemController.addDigitalMovieItemToDb(json);
            return Response.status(Response.Status.OK).entity("New digital movie item added").build();
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
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response updateDigitalMovieItem(String json, @QueryParam("id") Long id) {
        try {
            digitalMovieItemController.updateDigitalMovieItemOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Digital movie item " + id + " correctly updated").build();
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
    public Response disableDigitalMovieItem(@QueryParam("id") Long id) {
        try {
            digitalMovieItemController.disableDigitalMovieItemOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Digital movie item " + id + " correctly disabled").build();
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
    public Response restoreDigitalMovieItem(@QueryParam("id") Long id) {
        try {
            digitalMovieItemController.disableDigitalMovieItemOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Digital movie item " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
