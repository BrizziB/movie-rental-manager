package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.ActorController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("actors")
public class ActorService {

    @Inject private ActorController actorController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveActorById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status( Response.Status.OK )
                           .entity( gson.toJson(actorController.getActorById(id)) )
                           .build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/actors-name")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveActorsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status( Response.Status.OK )
                           .entity( gson.toJson( actorController.getActorsByName(name)) )
                           .build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveCastByMovie(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status( Response.Status.OK )
                           .entity( gson.toJson( actorController.getCastByMovieId(id)) )
                           .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllActors(@QueryParam("from") int idx_start,
                                      @QueryParam("to") int idx_end,
                                      @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status( Response.Status.OK )
                           .entity( gson.toJson(actorController.getAllActors(idx_start, idx_end-idx_start, orderBy)) )
                           .build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response addNewActor(String json) {
        try {
            actorController.addActorToDb(json);
            return Response.status(Response.Status.OK).entity("New actor added").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response updateActor(String json, @QueryParam("id") Long id) {
        try {
            actorController.updateActorOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Actor " + id + " correctly updated").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response disableActor(@QueryParam("id") Long id) {
        try {
            actorController.disableActorOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Actor " + id + " correctly disabled").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response restoreActor(@QueryParam("id") Long id) {
        try {
            actorController.disableActorOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Actor " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
