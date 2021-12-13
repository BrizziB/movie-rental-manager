package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unifi.ing.stlab.movierentalmanager.components.dto.MovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.MovieMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Impongo l'ordine dei test perché non ho facile accesso al database per individuare gli
ID dei Movie memorizzati e voglio eseguire prima add() di update() */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieControllerTest {

    private static EntityManagerFactory emFactory;
    private EntityManager em;

    private MovieController movieController;
    private MovieMapper movieMapper;
    private MovieDao movieDao;
    private Movie movie;

    private List<Actor> actors;
    private List<CrewMember> crewMembers;
    private List<ProductionCompany> productionCompanies;

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

        movieController = new MovieController();
        movieMapper = new MovieMapper();
        movieDao = new MovieDao();

        FieldUtils.writeField(movieController, "movieDao", movieDao, true);

        em = emFactory.createEntityManager();
        FieldUtils.writeField(movieDao, "em", em, true);
        em.getTransaction().begin();

        initMovie();

//        FieldUtils.writeField(movieController, "movieMapper", movieMapper, true);

    }

    protected void initMovie() throws IllegalAccessException {
        movie = ModelFactory.initMovie();
        movie.setTitle("Movie from INIT");

        Director d = ModelFactory.initDirector();
        d.setName("Quentin Tarantino");
        d.setBirthDate( new Date(1963-03-27) );
        d.setCountry("USA");
        em.persist(d);

        CrewMember cm1 = ModelFactory.initCrewMember();
        cm1.setName("Andrzej Sekula");
        cm1.setRole(CrewRole.CINEMATOGRAPHER);
        CrewMember cm2 = ModelFactory.initCrewMember();
        cm1.setName("Sally Menke");
        cm1.setRole(CrewRole.EDITOR);
        crewMembers.add(cm1);
        crewMembers.add(cm2);
        em.persist(cm1);
        em.persist(cm2);

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
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.persist(a5);

        ProductionCompany pc1 = ModelFactory.initProductionCompany();
        pc1.setName("Miramax");
        ProductionCompany pc2 = ModelFactory.initProductionCompany();
        pc2.setName("Jersey Films");
        ProductionCompany pc3 = ModelFactory.initProductionCompany();
        pc3.setName("A Band Apart");
        productionCompanies.add(pc1);
        productionCompanies.add(pc2);
        productionCompanies.add(pc3);
        em.persist(pc1);
        em.persist(pc2);
        em.persist(pc3);

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
        em.persist(movie);

        movieDao = new MovieDao();
        FieldUtils.writeField(movieDao, "em", em, true);
    }


    @AfterEach
    void tearDown() {
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    @Test
    @Order(1)
    void getMovieById() {
        // Questo metodo del controller fa uso di un metodo del DAO e uno del mapper, già testati precedentemente
    }

    @Test
    @Order(2)
    void addMovieToDb() {
        com.google.gson.JsonObject movieJson = new JsonObject();
        movieJson.addProperty("title", "Pulp Fiction");
        movieJson.addProperty("year", 1994);
        movieJson.addProperty("length", 154);

        MovieDto dtoFromJson = new Gson().fromJson(movieJson, MovieDto.class);
        Movie movieFromJson = new Gson().fromJson(movieJson, Movie.class);

        movieController.addMovieToDb( new Gson().toJson(movieJson) );
        Movie manuallyRetrievedMovie = movieDao.findById( (long)12 ).get();

        assertEquals(movieFromJson.getTitle(), manuallyRetrievedMovie.getTitle());
        assertEquals(movieFromJson.getYear(), manuallyRetrievedMovie.getYear());
        assertEquals(movieFromJson.getLength(), manuallyRetrievedMovie.getLength());

        // si prosegue in maniera simile per Director, gli altri attributi e per gli oggetti degli arraylist di Characters, CrewMember, Cast, ecc...
    }

    @Test
    @Order(3)
    void updateMovieOnDb() {
        com.google.gson.JsonObject movieJson = new JsonObject();
        movieJson.addProperty("title", "Pulp Fiction 3 - Last Chapter");
        movieJson.addProperty("year", 2030);
        movieJson.addProperty("length", 178);

        Movie movieFromJson = new Gson().fromJson(movieJson, Movie.class);

        movieController.updateMovieOnDb( new Gson().toJson(movieJson), (long)12 );
        Movie manuallyRetrievedMovie = movieDao.findById( (long)12 ).get();

        assertEquals(movieFromJson.getTitle(), manuallyRetrievedMovie.getTitle());
        assertEquals(movieFromJson.getYear(), manuallyRetrievedMovie.getYear());
        assertEquals(movieFromJson.getLength(), manuallyRetrievedMovie.getLength());
    }

    @Test
    @Order(4)
    void disableMovieOnDb() {
        movieController.disableMovieOnDb(true, movie.getId());
        assertTrue( movieDao.findById(movie.getId()).get().isDisabled() );
    }

}