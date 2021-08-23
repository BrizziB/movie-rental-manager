package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MovieController;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.model.Movie;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("movies")
public class MovieService {

    @Inject
    private MovieController movieController;

    // si può fare retrieveMoviesByFilter che prende lista di filtri e li applica tutti

    // service/movies/list/search?actor=1&year=2010
    @GET
    @Path("/list/search")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMoviesByActor(@QueryParam("actor") Long id) {
        Gson gson = new Gson(); // in realtà andrebbe iniettato
        try {
            List<Movie> movies = movieController.getMoviesByActor(id);
            List<LiteMovieDto> liteMovies = new ArrayList<LiteMovieDto>();
            for(Movie m : movies)
                liteMovies.add(LiteMovieDto.mapMovieToLiteMovie(m));
            Response resp = Response.ok(gson.toJson(liteMovies)).build();
            return resp;
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
