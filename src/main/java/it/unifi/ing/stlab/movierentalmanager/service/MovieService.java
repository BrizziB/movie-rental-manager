package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MovieController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;
import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewRole;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movies")
public class MovieService {

    @Inject private MovieController movieController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveMovieById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getMovieById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/movie-title")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getMoviesByTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/actor-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByActor(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getMoviesByActorId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/director-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByDirector(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getMoviesByDirectorId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

//    @GET
//    @Path("/list/cinematographer-id/{id}")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public Response retrieveMoviesByCinematographer(@PathParam("id") Long id) {
//        Gson gson = new Gson();
//        try {
//            return Response.status(Response.Status.OK).entity(
//                    gson.toJson( movieController.getMoviesByCinematographerId(id) )
//            ).build();
//        } catch(Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.NOT_ACCEPTABLE)
//                    .entity(null)
//                    .build();
//        }
//    }
//
//    @GET
//    @Path("/list/writer-id/{id}")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public Response retrieveMoviesByWriter(@PathParam("id") Long id) {
//        Gson gson = new Gson();
//        try {
//            return Response.status(Response.Status.OK).entity(
//                    gson.toJson( movieController.getMoviesByWriterId(id) )
//            ).build();
//        } catch(Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.NOT_ACCEPTABLE)
//                    .entity(null)
//                    .build();
//        }
//    }

    @GET
    @Path("/list/crew-id-role")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByCrewMember(@QueryParam("id") Long id, @QueryParam("role") CrewRole role) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getMoviesByCrewMemberIdAndRole(id, role) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/top-rated")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveTopRatedMovies(@QueryParam("limit") Integer limit) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getTopRatedMovies(limit) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllMovies(@QueryParam("from") int idx_start,
                                      @QueryParam("to") int idx_end,
                                      @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( movieController.getAllMovies(idx_start, idx_end-idx_start, orderBy) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }


    @GET
    @Path("/list/multipleFilters")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response multipleFilters(@QueryParam(value = "") List<String> json) {
        return Response.status(Response.Status.OK).entity("").build();
    }

    // CRUD methods

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response addNewMovie(String json) {
        try {
            movieController.addMovieToDb(json);
            return Response.status(Response.Status.OK).entity("New movie added").build();
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
    public Response updateMovie(String json, @QueryParam("id") Long id) {
        //TODO aggiungere 'id' anche nel body
        try {
            movieController.updateMovieOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Movie " + id + " correctly updated").build();
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
    public Response disableMovie(@QueryParam("id") Long id) {
        try {
            movieController.disableMovieOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Movie " + id + " correctly disabled").build();
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
    public Response restoreMovie(@QueryParam("id") Long id) {
        try {
            movieController.disableMovieOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Movie " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
