package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteActorDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCharacterDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.ActorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Character;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class CharacterMapper {

    @Inject private ActorDao actorDao;
    @Inject private ActorMapper actorMapper;
    @Inject private MovieDao movieDao;
    @Inject private MovieMapper movieMapper;

    public LiteCharacterDto convert(Character c) {
        if (c == null) {
            throw new MapperConversionException("The character is NULL");
        }

        LiteCharacterDto dto = new LiteCharacterDto();

        dto.setName(c.getName());
        dto.setActor(actorMapper.convert(c.getActor()));
        serializeActors(dto, c.getActors());
        serializeMovies(dto, c.getMovies());

        return dto;
    }

    public void transfer(LiteCharacterDto dto, Character c) {
        if(dto == null)
            throw new MapperTransferException("The character DTO is NULL");
        if(c == null)
            throw new MapperTransferException("The character Entity is NULL");

        if(dto.getName() != null)
            c.setName(dto.getName());
        if(dto.getActor() != null) {
            if( actorDao.retrieveActorsByName( dto.getActor().getName() ).size() != 0 )
                System.out.println("Actors with similar names do exist in database. Do you want to check them out?");
            else {
                Actor actor = ModelFactory.initActor();
                actorMapper.transfer(dto.getActor(), actor);
                actorDao.add(actor);
                c.setActor(actor);
            }
        }
        if(dto.getActors() != null)
            deSerializeActors(c, dto.getActors());
        if(dto.getMovies() != null)
            deSerializeMovies(c, dto.getMovies());
    }

    private void serializeActors(LiteCharacterDto dto, List<Actor> actors) {
        if(actors != null && actors.size() > 0)
            for (Actor a : actors)
                dto.getActors().add( actorMapper.convert(a) );
    }

    private void serializeMovies(LiteCharacterDto dto, List<Movie> movies) {
        if(movies != null && movies.size() > 0)
            for (Movie m : movies)
                dto.getMovies().add( movieMapper.convert(m) );
    }

    private void deSerializeActors(Character c, List<LiteActorDto> liteActors) {
        c.getActors().clear();

        if(liteActors != null && liteActors.size() > 0)
            for (LiteActorDto liteActor : liteActors) {
                if ( actorDao.retrieveActorsByName( liteActor.getName() ).size() != 0 )
                    System.out.println("Actors with similar names do exist in database. Do you want to check them out?");
                else {
                    Actor actor = ModelFactory.initActor();
                    actorMapper.transfer(liteActor, actor);
                    actorDao.add(actor);
                    c.getActors().add(actor);
                }
            }
    }

    private void deSerializeMovies(Character c, List<LiteMovieDto> liteMovies) {
        c.getMovies().clear();

        if(liteMovies != null && liteMovies.size() > 0)
            for (LiteMovieDto liteMovie : liteMovies) {
                if ( movieDao.retrieveMoviesByTitle( liteMovie.getTitle() ).size() != 0 )
                    System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
                else {
                    Movie movie = ModelFactory.initMovie();
                    movieMapper.transfer(liteMovie, movie);
                    movieDao.add(movie);
                    c.getMovies().add(movie);
                }
            }
    }

}
