package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.*;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.*;
import it.unifi.ing.stlab.movierentalmanager.dao.ActorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.CrewMemberDao;
import it.unifi.ing.stlab.movierentalmanager.dao.DirectorDao;
import it.unifi.ing.stlab.movierentalmanager.dao.ProductionCompanyDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {

    private MovieMapper movieMapper = new MovieMapper();

    private DirectorMapper directorMapper = new DirectorMapper();
    private CharacterMapper characterMapper = new CharacterMapper();
    private CrewMemberMapper crewMemberMapper = new CrewMemberMapper();
    private ActorMapper actorMapper = new ActorMapper();
    private ProductionCompanyMapper productionCompanyMapper = new ProductionCompanyMapper();
    private DigitalMovieItemMapper digitalMovieItemMapper = new DigitalMovieItemMapper();
    private PhysicalMovieItemMapper physicalMovieItemMapper = new PhysicalMovieItemMapper();

    private static EntityManagerFactory emFactory;
    private EntityManager em;

    private DirectorDao directorDao = new DirectorDao();
    private ActorDao actorDao = new ActorDao();
    private CrewMemberDao crewMemberDao = new CrewMemberDao();
    private ProductionCompanyDao productionCompanyDao = new ProductionCompanyDao();

    private Movie movie;
    private MovieDto movieDto;
    private LiteMovieDto liteMovieDto;

    private List<Actor> actors;
    private List<CrewMember> crewMembers;
    private List<ProductionCompany> productionCompanies;

    private List<ActorDto> actorsDtos;
    private List<CrewMemberDto> crewMembersDtos;
    private List<ProductionCompanyDto> productionCompaniesDtos;

    @BeforeAll
    public static void initHeavy() {
        System.out.println("Creazione EMF");
        emFactory = Persistence.createEntityManagerFactory("test");
    }

    @BeforeEach
    void setUp() throws IllegalAccessException {

        actors = new ArrayList<>();
        crewMembers = new ArrayList<>();
        productionCompanies = new ArrayList<>();

        actorsDtos = new ArrayList<>();
        crewMembersDtos = new ArrayList<>();
        productionCompaniesDtos = new ArrayList<>();

        FieldUtils.writeField(movieMapper, "directorMapper", directorMapper, true);
        FieldUtils.writeField(movieMapper, "characterMapper", characterMapper, true);
        FieldUtils.writeField(movieMapper, "crewMemberMapper", crewMemberMapper, true);
        FieldUtils.writeField(movieMapper, "actorMapper", actorMapper, true);
        FieldUtils.writeField(movieMapper, "productionCompanyMapper", productionCompanyMapper, true);
        FieldUtils.writeField(movieMapper, "digitalMovieItemMapper", digitalMovieItemMapper, true);
        FieldUtils.writeField(movieMapper, "physicalMovieItemMapper", physicalMovieItemMapper, true);

        FieldUtils.writeField(movieMapper, "directorDao", directorDao, true);
        FieldUtils.writeField(movieMapper, "actorDao", actorDao, true);
        FieldUtils.writeField(movieMapper, "crewMemberDao", crewMemberDao, true);
        FieldUtils.writeField(movieMapper, "productionCompanyDao", productionCompanyDao, true);

        em = emFactory.createEntityManager();
        FieldUtils.writeField(directorDao, "em", em, true);
        FieldUtils.writeField(actorDao, "em", em, true);
        FieldUtils.writeField(crewMemberDao, "em", em, true);
        FieldUtils.writeField(productionCompanyDao, "em", em, true);

        initMovie();

    }

    protected void initMovie() throws IllegalAccessException {
        // MOVIE OBJECT
        movie = ModelFactory.initMovie();
        movie.setTitle("Movie from INIT");

        Director d = ModelFactory.initDirector();
        d.setName("Quentin Tarantino");
        d.setBirthDate( new Date(1963-03-27) );
        d.setCountry("USA");

        CrewMember cm1 = ModelFactory.initCrewMember();
        cm1.setName("Andrzej Sekula");
        cm1.setRole(CrewRole.CINEMATOGRAPHER);
        CrewMember cm2 = ModelFactory.initCrewMember();
        cm1.setName("Sally Menke");
        cm1.setRole(CrewRole.EDITOR);
        crewMembers.add(cm1);
        crewMembers.add(cm2);

        Actor a1 = ModelFactory.initActor();
        a1.setName("Samuel L. Jackson");
        Actor a2 = ModelFactory.initActor();
        a2.setName("John Travolta");
        Actor a3 = ModelFactory.initActor();
        a3.setName("Uma Thurman");
        Actor a4 = ModelFactory.initActor();
        a4.setName("Bruce Willis");
        Actor a5 = ModelFactory.initActor();
        a5.setName("Ving Rhames");
        actors.add(a1);
        actors.add(a2);
        actors.add(a3);
        actors.add(a4);
        actors.add(a5);

        ProductionCompany pc1 = ModelFactory.initProductionCompany();
        pc1.setName("Miramax");
        ProductionCompany pc2 = ModelFactory.initProductionCompany();
        pc2.setName("Jersey Films");
        ProductionCompany pc3 = ModelFactory.initProductionCompany();
        pc3.setName("A Band Apart");
        productionCompanies.add(pc1);
        productionCompanies.add(pc2);
        productionCompanies.add(pc3);

        movie = ModelFactory.initMovie();
        movie.setTitle("Pulp Fiction");
        movie.setYear("1994");
        movie.setLength(154);
        movie.setPlot("A burger-loving hit man, his philosophical partner, a drug-addled gangster’s moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time");
        movie.setCountry("USA");
        movie.setLanguage("English, Spanish, French");
        movie.setBudget(8500000);
        movie.setCriticRating(4.3);
        movie.setGenre(Genre.THRILLER);
        movie.setDirector(d);
        movie.setCast(actors);
        movie.setCrew(crewMembers);
        movie.setProducers(productionCompanies);

        // DTO OBJECT

        movieDto = new MovieDto();
        DirectorDto d1 = new DirectorDto();
        d1.setName("Quentin Tarantino");
        d1.setBirthDate( new Date(1963-03-27) );
        d1.setCountry("USA");

        CrewMemberDto cm3 = new CrewMemberDto();
        cm3.setName("Andrzej Sekula");
        cm3.setRole(CrewRole.CINEMATOGRAPHER);
        CrewMemberDto cm4 = new CrewMemberDto();
        cm4.setName("Sally Menke");
        cm4.setRole(CrewRole.EDITOR);
        crewMembersDtos.add(cm3);
        crewMembersDtos.add(cm4);

        ActorDto a6 = new ActorDto();
        a6.setName("Samuel L. Jackson");
        ActorDto a7 = new ActorDto();
        a7.setName("John Travolta");
        ActorDto a8 = new ActorDto();
        a8.setName("Uma Thurman");
        ActorDto a9 = new ActorDto();
        a9.setName("Bruce Willis");
        ActorDto a10 = new ActorDto();
        a10.setName("Ving Rhames");
        actorsDtos.add(a6);
        actorsDtos.add(a7);
        actorsDtos.add(a8);
        actorsDtos.add(a9);
        actorsDtos.add(a10);

        ProductionCompanyDto pc4 = new ProductionCompanyDto();
        pc4.setName("Miramax");
        ProductionCompanyDto pc5 = new ProductionCompanyDto();
        pc5.setName("Jersey Films");
        ProductionCompanyDto pc6 = new ProductionCompanyDto();
        pc6.setName("A Band Apart");
        productionCompaniesDtos.add(pc4);
        productionCompaniesDtos.add(pc5);
        productionCompaniesDtos.add(pc6);

        movieDto.setTitle("Pulp Fiction");
        movieDto.setYear("1994");
        movieDto.setLength(154);
        movieDto.setPlot("A burger-loving hit man, his philosophical partner, a drug-addled gangster’s moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time");
        movieDto.setCountry("USA");
        movieDto.setLanguage("English, Spanish, French");
        movieDto.setBudget(8500000);
        movieDto.setCriticRating(4.3);
        movieDto.setGenre(Genre.THRILLER);
        movieDto.setDirector(d1);
        movieDto.setCast(actorsDtos);
        movieDto.setCrew(crewMembersDtos);
        movieDto.setProducers(productionCompaniesDtos);

        // LITE DTO OBJECT

        liteMovieDto = new LiteMovieDto();
        LiteDirectorDto d2 = new LiteDirectorDto();
        d2.setName("Quentin Tarantino");
        d2.setBirthDate( new Date(1963-03-27) );
        d2.setCountry("USA");

        liteMovieDto.setTitle("Pulp Fiction");
        liteMovieDto.setYear("1994");
        liteMovieDto.setLength(154);
        liteMovieDto.setPlot("A burger-loving hit man, his philosophical partner, a drug-addled gangster’s moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time");
        liteMovieDto.setCountry("USA");
        liteMovieDto.setLanguage("English, Spanish, French");
        liteMovieDto.setBudget(8500000);
        liteMovieDto.setCriticRating(4.3);
        liteMovieDto.setGenre(Genre.THRILLER);
        liteMovieDto.setDirector(d2);

    }

    @AfterEach
    void tearDown() {
        em.clear();
        em.close();
    }

    @AfterAll
    public static void tearDownEM() {
        emFactory.close();
    }

    @Test
    void convert() {
        LiteMovieDto liteDtoFromConvert = movieMapper.convert(movie);
        assertEquals(liteMovieDto, liteDtoFromConvert);
    }

    @Test
    void transfer() {
        Movie movieFromTransfer = new Movie();
        movieFromTransfer = movieMapper.transfer(movieDto, movieFromTransfer);

        // Non uso assertEquals direttamente sugli oggetti Movie perché questo andrebbe
        // a controllare gli UUID, cosa che in questo frangente non interessa

        assertEquals(movie.getTitle(), movieFromTransfer.getTitle());
        assertEquals(movie.getYear(), movieFromTransfer.getYear());
        assertEquals(movie.getLength(), movieFromTransfer.getLength());
        assertEquals(movie.getPlot(), movieFromTransfer.getPlot());
        assertEquals(movie.getCountry(), movieFromTransfer.getCountry());
        assertEquals(movie.getLanguage(), movieFromTransfer.getLanguage());
        assertEquals(movie.getBudget(), movieFromTransfer.getBudget());
        assertEquals(movie.getCriticRating(), movieFromTransfer.getCriticRating());
        assertEquals(movie.getGenre(), movieFromTransfer.getGenre());
        assertEquals(movie.getRating(), movieFromTransfer.getRating());

        Director dirTransf1 = movie.getDirector();
        Director dirTransf2 = movieFromTransfer.getDirector();
        assertEquals(dirTransf1.getName(), dirTransf2.getName());
        assertEquals(dirTransf1.getBirthDate(), dirTransf2.getBirthDate());
        assertEquals(dirTransf1.getCountry(), dirTransf2.getCountry());

        // si prosegue in maniera simile per gli oggetti degli arraylist di Characters, CrewMember, Cast, ecc...

    }
}