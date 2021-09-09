package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.*;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.*;
import it.unifi.ing.stlab.movierentalmanager.model.items.DigitalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.items.MovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.items.PhysicalMovieItem;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Character;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class MovieMapper {

    @Inject private DirectorMapper directorMapper;
    @Inject private CharacterMapper characterMapper;
    @Inject private CrewMemberMapper crewMemberMapper;
    @Inject private ActorMapper actorMapper;
    @Inject private ProductionCompanyMapper productionCompanyMapper;
    @Inject private DigitalMovieItemMapper digitalMovieItemMapper;
    @Inject private PhysicalMovieItemMapper physicalMovieItemMapper;

    @Inject private DirectorDao directorDao;
    @Inject private CharacterDao characterDao;
    @Inject private CrewMemberDao crewMemberDao;
    @Inject private ActorDao actorDao;
    @Inject private ProductionCompanyDao productionCompanyDao;
    @Inject private DigitalMovieItemDao digitalMovieItemDao;
    @Inject private PhysicalMovieItemDao physicalMovieItemDao;

    public LiteMovieDto convert(Movie m) {
        if(m == null)
            throw new MapperConversionException("The movie is NULL");

        LiteMovieDto dto = new LiteMovieDto();

        dto.setTitle(m.getTitle());
        dto.setYear(m.getYear());
        dto.setLength(m.getLength());
        dto.setPlot(m.getPlot());
        dto.setCountry(m.getCountry());
        dto.setLanguage(m.getLanguage());
        dto.setBudget(m.getBudget());
        dto.setCriticRating(m.getCriticRating());
        dto.setDirector( directorMapper.convert(m.getDirector()) );
        dto.setGenre(m.getGenre());
        dto.setRating(m.getRating());
        serializeCharacters(dto, m.getCharacters());
        serializeCrew(dto, m.getCrew());
        serializeCast(dto, m.getCast());
        serializeProducers(dto, m.getProducers());
        serializeDigitalItems(dto, m.getItems());
        serializePhysicalItems(dto, m.getItems());

        return dto;
    }

    public void transfer(LiteMovieDto dto, Movie m) {
        if(dto == null)
            throw new MapperTransferException("The movie DTO is NULL");
        if(m == null)
            throw new MapperTransferException("The movie Entity is NULL");

        if(dto.getTitle() != null)
            m.setTitle(dto.getTitle());
        if(dto.getYear() != null)
            m.setYear(dto.getYear());
        if(dto.getLength() != null)
            m.setLength(dto.getLength());
        if(dto.getPlot() != null)
            m.setPlot(dto.getPlot());
        if(dto.getCountry() != null)
            m.setCountry(dto.getCountry());
        if(dto.getLanguage() != null)
            m.setLanguage(dto.getLanguage());
        if(dto.getBudget() != null)
            m.setBudget(dto.getBudget());
        if(dto.getCriticRating() != null)
            m.setCriticRating(dto.getCriticRating());
        if(dto.getGenre() != null)
            m.setGenre(dto.getGenre());
        if(dto.getRating() != null)
            m.setRating(dto.getRating());
        if(dto.getDirector() != null) {
            // TODO a concrete solution to this, also in Character, DMI, PMI, Order and PaymentProfile mappers
            if ( directorDao.retrieveDirectorsByName(dto.getDirector().getName()).size() != 0) {
                System.out.println("Directors with similar names do exist in database. Do you want to check them out?");
            }
            else {
                Director director = ModelFactory.initDirector();
                directorMapper.transfer(dto.getDirector(), director);
                directorDao.add(director);
                m.setDirector(director);
            }
        }
        deSerializeCharacters(m, dto.getCharacters());
        deSerializeCrew(m, dto.getCrew());
        deSerializeCast(m, dto.getCast());
        deSerializeProducers(m, dto.getProducers());
        deSerializeDigitalItems(m, dto.getDigitalItems());
        deSerializePhysicalItems(m, dto.getPhysicalItems());
    }

    private void serializeCharacters(LiteMovieDto dto, List<Character> characters) {
        if(characters != null && characters.size() > 0)
            for (Character c : characters)
                dto.getCharacters().add( characterMapper.convert(c) );
    }

    private void serializeCrew(LiteMovieDto dto, List<CrewMember> crew) {
        if(crew != null && crew.size() > 0)
            for (CrewMember c : crew)
                dto.getCrew().add( crewMemberMapper.convert(c) );
    }

    private void serializeCast(LiteMovieDto dto, List<Actor> cast) {
        if(cast != null && cast.size() > 0)
            for (Actor a : cast)
                dto.getCast().add( actorMapper.convert(a) );
    }

    private void serializeProducers(LiteMovieDto dto, List<ProductionCompany> producers) {
        if(producers != null && producers.size() > 0)
            for (ProductionCompany p : producers)
                dto.getProducers().add(productionCompanyMapper.convert(p) );
    }

    private void serializeDigitalItems(LiteMovieDto dto, List<MovieItem> movieItems) {
        if(movieItems != null && movieItems.size() > 0)
            for (MovieItem mi : movieItems)
                if(mi instanceof DigitalMovieItem)
                    dto.getDigitalItems().add(digitalMovieItemMapper.convert( (DigitalMovieItem) mi) );
    }

    private void serializePhysicalItems(LiteMovieDto dto, List<MovieItem> movieItems) {
        if(movieItems != null && movieItems.size() > 0)
            for (MovieItem mi : movieItems)
                if(mi instanceof PhysicalMovieItem)
                    dto.getPhysicalItems().add(physicalMovieItemMapper.convert( (PhysicalMovieItem) mi) );
    }

    private void deSerializeCharacters(Movie m, List<LiteCharacterDto> liteCharacters) {
        m.getCharacters().clear();

        if(liteCharacters != null && liteCharacters.size() > 0)
            for (LiteCharacterDto liteCharacter : liteCharacters) {
                if ( characterDao.retrieveCharactersByName( liteCharacter.getName() ).size() != 0 )
                    System.out.println("Characters with similar names do exist in database. Do you want to check them out?");
                else {
                    Character character = ModelFactory.initCharacter();
                    characterMapper.transfer(liteCharacter, character);
                    characterDao.add(character);
                    m.getCharacters().add(character);
                }
            }
    }

    private void deSerializeCrew(Movie m, List<LiteCrewMemberDto> liteCrewMembers) {
        m.getCrew().clear();

        if(liteCrewMembers != null && liteCrewMembers.size() > 0)
            for (LiteCrewMemberDto liteCM : liteCrewMembers) {
                if ( crewMemberDao.retrieveCrewMembersByNameAndRole( liteCM.getName(), liteCM.getRole() ).size() != 0 )
                    System.out.println("Crew member with similar names and roles do exist in database. Do you want to check them out?");
                else {
                    CrewMember crewMember = ModelFactory.initCrewMember();
                    crewMemberMapper.transfer(liteCM, crewMember);
                    crewMemberDao.add(crewMember);
                    m.getCrew().add(crewMember);
                }
            }
    }

    private void deSerializeCast(Movie m, List<LiteActorDto> liteActors) {
        m.getCast().clear();

        if(liteActors != null && liteActors.size() > 0)
            for (LiteActorDto liteActor : liteActors) {
                if (actorDao.retrieveActorsByName(liteActor.getName()).size() != 0 )
                    System.out.println("Actors with similar names do exist in database. Do you want to check them out?");
                else {
                    Actor actor = ModelFactory.initActor();
                    actorMapper.transfer(liteActor, actor);
                    actorDao.add(actor);
                    m.getCast().add(actor);
                }
            }
    }

    private void deSerializeProducers(Movie m, List<LiteProductionCompanyDto> liteProducers) {
        m.getProducers().clear();

        if(liteProducers != null && liteProducers.size() > 0)
            for (LiteProductionCompanyDto liteProducer : liteProducers) {
                if ( productionCompanyDao.retrieveProductionCompaniesByName( liteProducer.getName() ).size() != 0)
                    System.out.println("Producers with similar names do exist in database. Do you want to check them out?");
                else {
                    ProductionCompany pc = ModelFactory.initProductionCompany();
                    productionCompanyMapper.transfer(liteProducer, pc);
                    productionCompanyDao.add(pc);
                    m.getProducers().add(pc);
                }
            }
    }

    private void deSerializeDigitalItems(Movie m, List<LiteDigitalMovieItemDto> liteDigitalItems) {
        m.getItems().clear();

        if(liteDigitalItems != null && liteDigitalItems.size() > 0)
            for (LiteDigitalMovieItemDto liteDMI : liteDigitalItems) {
                if ( digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle( liteDMI.getMovie().getTitle() ).size() != 0)
                    System.out.println("Digital movie items of movies with similar titles do exist in database. Do you want to check them out?");
                else {
                    DigitalMovieItem dmi = ModelFactory.initDigitalMovieItem();
                    digitalMovieItemMapper.transfer(liteDMI, dmi);
                    digitalMovieItemDao.add(dmi);
                    m.getItems().add(dmi);
                }
            }
    }

    private void deSerializePhysicalItems(Movie m, List<LitePhysicalMovieItemDto> litePhysicalItems) {
        m.getItems().clear();

        if(litePhysicalItems != null && litePhysicalItems.size() > 0)
            for (LitePhysicalMovieItemDto litePMI : litePhysicalItems) {
                if ( digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle( litePMI.getMovie().getTitle() ).size() != 0)
                    System.out.println("Physical movie items of movies with similar titles do exist in database. Do you want to check them out?");
                else {
                    PhysicalMovieItem pmi = ModelFactory.initPhysicalMovieItem();
                    physicalMovieItemMapper.transfer(litePMI, pmi);
                    physicalMovieItemDao.add(pmi);
                    m.getItems().add(pmi);
                }
            }
    }

}
