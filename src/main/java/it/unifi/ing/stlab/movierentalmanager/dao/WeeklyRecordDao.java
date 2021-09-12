package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.WeeklyRecord;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
public class WeeklyRecordDao extends BaseDao<WeeklyRecord> {

    public WeeklyRecordDao() {
        super(WeeklyRecord.class);
    }

    @Transactional
    public void update(WeeklyRecord wr) {
        WeeklyRecord oldWeeklyRecord = getEm().find( WeeklyRecord.class, wr.getId() );
        oldWeeklyRecord.setMovie( wr.getMovie() );
        oldWeeklyRecord.setThisWeekPurchases( wr.getThisWeekPurchases() );
        oldWeeklyRecord.setCurrentTotalPurchases( wr.getCurrentTotalPurchases() );
    }

    public List<WeeklyRecord> retrieveWeeklyRecordsByName(String name) {
        TypedQuery<WeeklyRecord> query = getEm().createQuery(
                "FROM WeeklyRecord wr WHERE wr.name LIKE CONCAT('%', :name, '%')",
                WeeklyRecord.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    public List<WeeklyRecord> retrieveWeeklyRecordsByMovieId(Long id) {
        TypedQuery<WeeklyRecord> query = getEm().createQuery(
                "FROM WeeklyRecord wr WHERE wr.movie.id = :id",
                WeeklyRecord.class
        ).setParameter("id", id);
        return query.getResultList();
    }

    public List<WeeklyRecord> retrieveWeeklyRecordsByMovieTitle(String title) {
        TypedQuery<WeeklyRecord> query = getEm().createQuery(
                "FROM WeeklyRecord wr WHERE wr.movie.title LIKE CONCAT('%', :title, '%')",
                WeeklyRecord.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    public List<WeeklyRecord> retrieveWeeklyRecordsBetweenDates(Date start, Date end) {
        TypedQuery<WeeklyRecord> query = getEm().createQuery(
                        "FROM WeeklyRecord wr WHERE wr.creationTime > :start AND wr.creationTime < :end",
                        WeeklyRecord.class
                ).setParameter("start", start)
                .setParameter("end", end);
        return query.getResultList();
    }

}
