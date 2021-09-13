package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.ActorController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actors")
public class ActorService {

    @Inject private ActorController actorController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveActorById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( actorController.getActorById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/actors-name")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveActorsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( actorController.getActorsByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movies-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveCastByMovie(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( actorController.getCastByMovieId(id) )
            ).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllActors() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( actorController.getAllActors(0, 25) )
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
    public Response addNewActor(String json) {
        try {
            actorController.addActorToDb(json);
            return Response.ok("New actor added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateActor(String json, @QueryParam("id") Long id) {
        try {
            actorController.updateActorOnDb(json, id);
            return Response.ok("Actor " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableActor(@QueryParam("id") Long id) {
        try {
            actorController.disableActorOnDb(true, id);
            return Response.ok("Actor " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreActor(@QueryParam("id") Long id) {
        try {
            actorController.disableActorOnDb(false, id);
            return Response.ok("Actor " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
