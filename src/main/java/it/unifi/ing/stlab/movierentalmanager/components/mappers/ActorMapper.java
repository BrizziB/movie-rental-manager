package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteActorDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteCharacterDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.CharacterDao;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Actor;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Character;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ActorMapper {

    @Inject private MovieMapper movieMapper;
    @Inject private MovieDao movieDao;
    @Inject private CharacterMapper characterMapper;
    @Inject private CharacterDao characterDao;

    public LiteActorDto convert(Actor a) {
        if(a == null)
            throw new MapperConversionException("The actor is NULL");

        LiteActorDto dto = new LiteActorDto();

        dto.setName(a.getName());
        dto.setBirthDate(a.getBirthDate());
        dto.setCountry(a.getCountry());
        dto.setStageName(a.getStageName());
        serializeMovies(dto, a.getMovies());
        serializeCharacters(dto, a.getCharacters());

        return dto;
    }

    public void transfer(LiteActorDto dto, Actor a) {
        if(dto == null)
            throw new MapperTransferException("The actor DTO is NULL");
        if(a == null)
            throw new MapperTransferException("The actor Entity is NULL");

        if(dto.getName() != null)
            a.setName(dto.getName());
        if(dto.getBirthDate() != null)
            a.setBirthDate(dto.getBirthDate());
        if(dto.getCountry() != null)
            a.setCountry(dto.getCountry());
        if(dto.getStageName() != null)
            a.setStageName(dto.getStageName());
        if(dto.getMovies() != null)
            deSerializeMovies(a, dto.getMovies());
        if(dto.getCharacters() != null)
            deSerializeCharacters(a, dto.getCharacters());
    }

    private void serializeMovies(LiteActorDto dto, List<Movie> movies) {
        if(movies != null || movies.size() > 0)
            for (Movie m : movies)
                dto.getMovies().add( movieMapper.convert(m) );
    }

    private void serializeCharacters(LiteActorDto dto, List<Character> characters) {
        if(characters != null || characters.size() > 0)
            for (Character c : characters)
                dto.getCharacters().add( characterMapper.convert(c) );
    }

    private void deSerializeMovies(Actor a, List<LiteMovieDto> liteMovies) {
        a.getMovies().clear();

        if(liteMovies != null || liteMovies.size() > 0)
            for (LiteMovieDto liteMovie : liteMovies) {
                if ( movieDao.retrieveMoviesByTitle( liteMovie.getTitle() ).size() != 0 )
                    System.out.println("Movies with similar names do exist in database. Do you want to check them out?");
                else {
                    Movie movie = ModelFactory.initMovie();
                    movieMapper.transfer(liteMovie, movie);
                    movieDao.add(movie);
                    a.getMovies().add(movie);
                }
            }
    }

    private void deSerializeCharacters(Actor a, List<LiteCharacterDto> liteCharacters) {
        a.getCharacters().clear();

        if(liteCharacters != null || liteCharacters.size() > 0)
            for (LiteCharacterDto liteCharacter : liteCharacters) {
                if ( characterDao.retrieveCharactersByName( liteCharacter.getName() ).size() != 0 )
                    System.out.println("Characters with similar names do exist in database. Do you want to check them out?");
                else {
                    Character character = ModelFactory.initCharacter();
                    characterMapper.transfer(liteCharacter, character);
                    characterDao.add(character);
                    a.getCharacters().add(character);
                }
            }
    }

}
