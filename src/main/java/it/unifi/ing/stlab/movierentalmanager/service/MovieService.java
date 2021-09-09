package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MovieController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movies")
public class MovieService {

    @Inject private MovieController movieController;

    @GET
    @Path("/searchById")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMovieById(@QueryParam("id") Long id) {
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
    @Path("/list/searchByTitle")
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
    @Path("/list/actor")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByActor(@QueryParam("id") Long id) {
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
    @Path("/list/director")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByDirector(@QueryParam("id") Long id) {
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

    @GET
    @Path("/list/cinematographer")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByCinematographer(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByCinematographerId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/writer")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByWriter(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( movieController.getMoviesByWriterId(id) )
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

    // TODO più filtri in un solo metodo
    @GET
    @Path("/multipleFilters")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response multipleFilters(List<String> json) {
        return Response.ok("").build();
    }

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
