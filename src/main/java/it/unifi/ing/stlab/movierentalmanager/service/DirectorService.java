package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.DirectorController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("directors")
public class DirectorService {

    @Inject private DirectorController directorController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveDirectorById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( directorController.getDirectorById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/directors-names")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveDirectorsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( directorController.getDirectorsByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/movies-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveDirectorByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( directorController.getDirectorByMovieId(id) )
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
    public Response retrieveAllDirectors() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( directorController.getAllDirectors(0, 25) )
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
    @RolesAllowed("BACK_OFFICE")
    public Response addNewDirector(String json) {
        try {
            directorController.addDirectorToDb(json);
            return Response.status(Response.Status.OK).entity("New director added").build();
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
    public Response updateDirector(String json, @QueryParam("id") Long id) {
        try {
            directorController.updateDirectorOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Director " + id + " correctly updated").build();
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
    public Response disableDirector(@QueryParam("id") Long id) {
        try {
            directorController.disableDirectorOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Director " + id + " correctly disabled").build();
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
    public Response restoreDirector(@QueryParam("id") Long id) {
        try {
            directorController.disableDirectorOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Director " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
