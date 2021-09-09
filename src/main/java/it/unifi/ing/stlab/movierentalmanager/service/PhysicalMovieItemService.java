package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.PhysicalMovieItemController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("physical-movie-items")
public class PhysicalMovieItemService {

    @Inject private PhysicalMovieItemController physicalMovieItemController;

    @GET
    @Path("/searchById")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrievePhysicalMovieItemById(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByMovieId")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrievePhysicalMovieItemsByMovieId(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByMovieTitle")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrievePhysicalMovieItemsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllPhysicalMovieItems() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( physicalMovieItemController.getAllPhysicalMovieItems(0, 25) )
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
    public Response addNewPhysicalMovieItem(String json) {
        try {
            physicalMovieItemController.addPhysicalMovieItemToDb(json);
            return Response.ok("New physical movie item added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updatePhysicalMovieItem(String json, @QueryParam("id") Long id) {
        try {
            physicalMovieItemController.updatePhysicalMovieItemOnDb(json, id);
            return Response.ok("Physical movie item " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disablePhysicalMovieItem(@QueryParam("id") Long id) {
        try {
            physicalMovieItemController.disablePhysicalMovieItemOnDb(true, id);
            return Response.ok("Physical movie item " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restorePhysicalMovieItem(@QueryParam("id") Long id) {
        try {
            physicalMovieItemController.disablePhysicalMovieItemOnDb(false, id);
            return Response.ok("Physical movie item " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}