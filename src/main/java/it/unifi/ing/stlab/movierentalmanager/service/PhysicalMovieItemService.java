package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.PhysicalMovieItemController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("physical-movie-items")
public class PhysicalMovieItemService {

    @Inject private PhysicalMovieItemController physicalMovieItemController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"FRONT_OFFICE", "BACK_OFFICE"})
    public Response retrievePhysicalMovieItemById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemById(id) )
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
    public Response retrievePhysicalMovieItemsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemsByMovieId(id) )
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
    public Response retrievePhysicalMovieItemsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( physicalMovieItemController.getPhysicalMovieItemsByMovieTitle(title) )
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
    public Response retrieveAllPhysicalMovieItems(@QueryParam("from") int idx_start,
                                                  @QueryParam("to") int idx_end,
                                                  @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( physicalMovieItemController.getAllPhysicalMovieItems(idx_start, idx_end-idx_start, orderBy) )
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
    public Response addNewPhysicalMovieItem(String json) {
        try {
            physicalMovieItemController.addPhysicalMovieItemToDb(json);
            return Response.status(Response.Status.OK).entity("New physical movie item added").build();
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
    public Response updatePhysicalMovieItem(String json, @QueryParam("id") Long id) {
        try {
            physicalMovieItemController.updatePhysicalMovieItemOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Physical movie item " + id + " correctly updated").build();
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
    public Response disablePhysicalMovieItem(@QueryParam("id") Long id) {
        try {
            physicalMovieItemController.disablePhysicalMovieItemOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Physical movie item " + id + " correctly disabled").build();
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
    public Response restorePhysicalMovieItem(@QueryParam("id") Long id) {
        try {
            physicalMovieItemController.disablePhysicalMovieItemOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Physical movie item " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}