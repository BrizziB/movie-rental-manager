package it.unifi.ing.stlab.movierentalmanager.components.beans;

import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.dao.MonthlyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.dao.WeeklyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.dao.YearlyRecordDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
@Startup
public class PurchaseRecordsBean {

    @PersistenceContext
    EntityManager em;

    @Inject WeeklyRecordDao weeklyRecordDao;
    @Inject MonthlyRecordDao monthlyRecordDao;
    @Inject YearlyRecordDao yearlyRecordDao;

    @Schedule(dayOfWeek = "Sun", hour = "23", minute = "59", second = "59")
    public void updateWeeklyRecords() {
        TypedQuery<Movie> query = em.createQuery("FROM Movie m", Movie.class);
        for(Movie m : query.getResultList()) {
            WeeklyRecord record = ModelFactory.initWeeklyRecord(
                    m, m.getThisWeekPurchases(), m.getTotalPurchases()
            );
            m.resetThisWeekPurchases();
            weeklyRecordDao.add(record);
        }
    }

    @Schedule(dayOfMonth = "Last", hour = "23", minute = "59", second = "59")
    public void updateMonthlyRecords() {
        TypedQuery<Movie> query = em.createQuery("FROM Movie m", Movie.class);
        for(Movie m : query.getResultList()) {
            MonthlyRecord record = ModelFactory.initMonthlyRecord(
                    m, m.getThisMonthPurchases(), m.getTotalPurchases()
            );
            m.resetThisMonthPurchases();
            monthlyRecordDao.add(record);
        }
    }

    @Schedule(month = "12", dayOfMonth = "31", hour = "23", minute = "59", second = "59")
    public void updateYearlyRecords() {
        TypedQuery<Movie> query = em.createQuery("FROM Movie m", Movie.class);
        for(Movie m : query.getResultList()) {
            YearlyRecord record = ModelFactory.initYearlyRecord(
                    m, m.getThisYearPurchases(), m.getTotalPurchases()
            );
            m.resetThisYearPurchases();
            yearlyRecordDao.add(record);
        }
    }

}
