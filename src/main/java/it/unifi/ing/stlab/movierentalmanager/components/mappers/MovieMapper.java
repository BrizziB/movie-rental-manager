package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.*;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.*;
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

    @Inject private MovieDao movieDao;
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
//        serializeCharacters(dto, m.getCharacters());
//        serializeCrew(dto, m.getCrew());
//        serializeCast(dto, m.getCast());
//        serializeProducers(dto, m.getProducers());
//        serializeDigitalItems(dto, m.getItems());
//        serializePhysicalItems(dto, m.getItems());

        return dto;
    }

    public Movie transfer(MovieDto dto, Movie m) {
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
        return m;
//        deSerializeDigitalItems(m, dto.getDigitalItems());
//        deSerializePhysicalItems(m, dto.getPhysicalItems());
    }

//    private void serializeCharacters(LiteMovieDto dto, List<Character> characters) {
//        if(characters != null && characters.size() > 0)
//            for (Character c : characters)
//                dto.getCharacters().add( characterMapper.convert(c) );
//    }
//
//    private void serializeCrew(LiteMovieDto dto, List<CrewMember> crew) {
//        if(crew != null && crew.size() > 0)
//            for (CrewMember c : crew)
//                dto.getCrew().add( crewMemberMapper.convert(c) );
//    }
//
//    private void serializeCast(LiteMovieDto dto, List<Actor> cast) {
//        if(cast != null && cast.size() > 0)
//            for (Actor a : cast)
//                dto.getCast().add( actorMapper.convert(a) );
//    }
//
//    private void serializeProducers(LiteMovieDto dto, List<ProductionCompany> producers) {
//        if(producers != null && producers.size() > 0)
//            for (ProductionCompany p : producers)
//                dto.getProducers().add(productionCompanyMapper.convert(p) );
//    }
//
//    private void serializeDigitalItems(LiteMovieDto dto, List<MovieItem> movieItems) {
//        if(movieItems != null && movieItems.size() > 0)
//            for (MovieItem mi : movieItems)
//                if(mi instanceof DigitalMovieItem)
//                    dto.getDigitalItems().add(digitalMovieItemMapper.convert( (DigitalMovieItem) mi) );
//    }
//
//    private void serializePhysicalItems(LiteMovieDto dto, List<MovieItem> movieItems) {
//        if(movieItems != null && movieItems.size() > 0)
//            for (MovieItem mi : movieItems)
//                if(mi instanceof PhysicalMovieItem)
//                    dto.getPhysicalItems().add(physicalMovieItemMapper.convert( (PhysicalMovieItem) mi) );
//    }

    private void deSerializeCharacters(Movie m, List<CharacterDto> characterDtos) {
        m.getCharacters().clear();

        if(characterDtos != null && characterDtos.size() > 0)
            for (CharacterDto c : characterDtos) {
                if ( characterDao.retrieveCharactersByName( c.getName() ).size() != 0 )
                    System.out.println("Characters with similar names do exist in database. Do you want to check them out?");
                else {
                    Character character = ModelFactory.initCharacter();
                    characterMapper.transfer(c, character);
                    characterDao.add(character);
                    m.getCharacters().add(character);
                }
            }
    }

    private void deSerializeCrew(Movie m, List<CrewMemberDto> crewMemberDtos) {
        m.getCrew().clear();

        if(crewMemberDtos != null && crewMemberDtos.size() > 0)
            for (CrewMemberDto cm : crewMemberDtos) {
                if ( crewMemberDao.retrieveCrewMembersByNameAndRole( cm.getName(), cm.getRole() ).size() != 0 )
                    System.out.println("Crew member with similar names and roles do exist in database. Do you want to check them out?");
                else {
                    CrewMember crewMember = ModelFactory.initCrewMember();
                    crewMemberMapper.transfer(cm, crewMember);
                    crewMemberDao.add(crewMember);
                    m.getCrew().add(crewMember);
                }
            }
    }

    private void deSerializeCast(Movie m, List<ActorDto> actorDtos) {
        m.getCast().clear();

        if(actorDtos != null && actorDtos.size() > 0)
            for (ActorDto a : actorDtos) {
                if (actorDao.retrieveActorsByName(a.getName()).size() != 0 )
                    System.out.println("Actors with similar names do exist in database. Do you want to check them out?");
                else {
                    Actor actor = ModelFactory.initActor();
                    actorMapper.transfer(a, actor);
                    actorDao.add(actor);
                    m.getCast().add(actor);
                }
            }
    }

    private void deSerializeProducers(Movie m, List<ProductionCompanyDto> productionCompanyDtos) {
        m.getProducers().clear();

        if(productionCompanyDtos != null && productionCompanyDtos.size() > 0)
            for (ProductionCompanyDto pc : productionCompanyDtos) {
                if ( productionCompanyDao.retrieveProductionCompaniesByName( pc.getName() ).size() != 0)
                    System.out.println("Producers with similar names do exist in database. Do you want to check them out?");
                else {
                    ProductionCompany productionCompany = ModelFactory.initProductionCompany();
                    productionCompanyMapper.transfer(pc, productionCompany);
                    productionCompanyDao.add(productionCompany);
                    m.getProducers().add(productionCompany);
                }
            }
    }

//    private void deSerializeDigitalItems(Movie m, List<DigitalMovieItemDto> digitalMovieItemDtos) {
//        m.getItems().clear();
//
//        if(digitalMovieItemDtos != null && digitalMovieItemDtos.size() > 0)
//            for (DigitalMovieItemDto dmi : digitalMovieItemDtos) {
//                if ( digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle( dmi.getMovie().getTitle() ).size() != 0)
//                    System.out.println("Digital movie items of movies with similar titles do exist in database. Do you want to check them out?");
//                else {
//                    DigitalMovieItem digitalMovieItem = ModelFactory.initDigitalMovieItem();
//                    digitalMovieItemMapper.transfer(dmi, digitalMovieItem);
//                    digitalMovieItemDao.add(digitalMovieItem);
//                    m.getItems().add(digitalMovieItem);
//                }
//            }
//    }
//
//    private void deSerializePhysicalItems(Movie m, List<PhysicalMovieItemDto> physicalMovieItemDtos) {
//        m.getItems().clear();
//
//        if(physicalMovieItemDtos != null && physicalMovieItemDtos.size() > 0)
//            for (PhysicalMovieItemDto pmi : physicalMovieItemDtos) {
//                if ( digitalMovieItemDao.retrieveDigitalMovieItemsByMovieTitle( pmi.getMovie().getTitle() ).size() != 0)
//                    System.out.println("Physical movie items of movies with similar titles do exist in database. Do you want to check them out?");
//                else {
//                    PhysicalMovieItem physicalMovieItem = ModelFactory.initPhysicalMovieItem();
//                    physicalMovieItemMapper.transfer(pmi, physicalMovieItem);
//                    physicalMovieItemDao.add(physicalMovieItem);
//                    m.getItems().add(physicalMovieItem);
//                }
//            }
//    }

}
