package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.movies.*;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDaoTest {

    private static EntityManagerFactory emFactory;
    private EntityManager em;

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
    public void setup() throws IllegalAccessException {
        System.out.println("Creazione EM");
        em = emFactory.createEntityManager();

        actors = new ArrayList<>();
        crewMembers = new ArrayList<>();
        productionCompanies = new ArrayList<>();

        em.getTransaction().begin();
        initMovie();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    @AfterAll
    public static void tearDownEM() {
        emFactory.close();
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


    @Test
    void add() throws IllegalAccessException {
        Movie movieToPersist = ModelFactory.initMovie();
        movieToPersist.setTitle("Movie from ADD");
        movieDao.add(movieToPersist);

        Movie manuallyRetrievedMovie = em.createQuery("FROM Movie WHERE uuid = :uuid", Movie.class)
                                         .setParameter("uuid", movieToPersist.getUuid())
                                         .getSingleResult();

        Assertions.assertEquals(movieToPersist, manuallyRetrievedMovie);
    }

    @Test
    void findById() {
        Movie result = movieDao.findById(movie.getId()).get();
        assertEquals(movie, result);
    }

    @Test
    void save() {
        Movie movieToPersist = ModelFactory.initMovie();
        movieToPersist.setTitle("Movie from ADD");
        movieDao.add(movieToPersist);

        Movie manuallyRetrievedMovie = em.createQuery("FROM Movie WHERE uuid = :uuid", Movie.class)
                .setParameter("uuid", movieToPersist.getUuid())
                .getSingleResult();

        Assertions.assertEquals(movieToPersist, manuallyRetrievedMovie);
    }

    @Test()
    void deleteById() {
        assertThrows(NoSuchElementException.class, () -> {
            movieDao.deleteById( movie.getId() );
            Movie result = movieDao.findById( movie.getId() ).get();
        });
    }

    @Test
    void update() {
        Movie updated = movieDao.findById( movie.getId() ).get();
        updated.setTitle("Pulp Fiction 2 - Revenge");

        movieDao.update(updated);

        Movie manuallyRetrievedMovie = em.createQuery("FROM Movie WHERE uuid = :uuid", Movie.class)
                .setParameter("uuid", updated.getUuid())
                .getSingleResult();

        Assertions.assertEquals(updated, manuallyRetrievedMovie);
    }

}