package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MovieController;
import it.unifi.ing.stlab.movierentalmanager.model.movies.CrewRole;

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
    public Response retrieveMovieById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMovieById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movie-title")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/actor-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByActor(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByActorId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/director-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByDirector(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByDirectorId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

//    @GET
//    @Path("/list/cinematographer-id/{id}")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public Response retrieveMoviesByCinematographer(@PathParam("id") Long id) {
//        Gson gson = new Gson();
//        try {
//            return Response.ok(
//                    gson.toJson( movieController.getMoviesByCinematographerId(id) )
//            ).build();
//        } catch(Exception e) {
//            e.printStackTrace();
//            return Response.notAcceptable(null).build();
//        }
//    }
//
//    @GET
//    @Path("/list/writer-id/{id}")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public Response retrieveMoviesByWriter(@PathParam("id") Long id) {
//        Gson gson = new Gson();
//        try {
//            return Response.ok(
//                    gson.toJson( movieController.getMoviesByWriterId(id) )
//            ).build();
//        } catch(Exception e) {
//            e.printStackTrace();
//            return Response.notAcceptable(null).build();
//        }
//    }

    @GET
    @Path("/list/crew-id-role")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByCrewMember(@QueryParam("id") Long id, @QueryParam("role") CrewRole role) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByCrewMemberIdAndRole(id, role) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/top-rated")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveTopRatedMovies(@QueryParam("limit") Integer limit) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getTopRatedMovies(limit) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllMovies() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getAllMovies(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }


    @GET
    @Path("/list/multipleFilters")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response multipleFilters(@QueryParam(value = "") List<String> json) {
        return Response.ok("").build();
    }

    // CRUD methods

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response addNewMovie(String json) {
        try {
            movieController.addMovieToDb(json);
            return Response.ok("New movie added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateMovie(String json, @QueryParam("id") Long id) {
        //TODO aggiungere 'id' anche nel body
        try {
            movieController.updateMovieOnDb(json, id);
            return Response.ok("Movie " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableMovie(@QueryParam("id") Long id) {
        try {
            movieController.disableMovieOnDb(true, id);
            return Response.ok("Movie " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreMovie(@QueryParam("id") Long id) {
        try {
            movieController.disableMovieOnDb(false, id);
            return Response.ok("Movie " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
