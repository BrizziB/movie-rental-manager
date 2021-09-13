package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.DigitalMovieItemController;

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
    public Response retrieveDigitalMovieItemById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movies-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveDigitalMovieItemsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movies-titles")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveDigitalMovieItemsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( digitalMovieItemController.getDigitalMovieItemsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllDigitalMovieItems() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( digitalMovieItemController.getAllDigitalMovieItems(0, 25) )
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
    public Response addNewDigitalMovieItem(String json) {
        try {
            digitalMovieItemController.addDigitalMovieItemToDb(json);
            return Response.ok("New digital movie item added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateDigitalMovieItem(String json, @QueryParam("id") Long id) {
        try {
            digitalMovieItemController.updateDigitalMovieItemOnDb(json, id);
            return Response.ok("Digital movie item " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableDigitalMovieItem(@QueryParam("id") Long id) {
        try {
            digitalMovieItemController.disableDigitalMovieItemOnDb(true, id);
            return Response.ok("Digital movie item " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreDigitalMovieItem(@QueryParam("id") Long id) {
        try {
            digitalMovieItemController.disableDigitalMovieItemOnDb(false, id);
            return Response.ok("Digital movie item " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
