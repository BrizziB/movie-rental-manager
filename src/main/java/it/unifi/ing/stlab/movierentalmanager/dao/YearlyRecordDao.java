package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;
import it.unifi.ing.stlab.movierentalmanager.model.statistics.YearlyRecord;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
public class YearlyRecordDao extends BaseDao<YearlyRecord> {

    public YearlyRecordDao() {
        super(YearlyRecord.class);
    }

    @Transactional
    public void update(YearlyRecord yr) {
        YearlyRecord oldYearlyRecord = getEm().find( YearlyRecord.class, yr.getId() );
        oldYearlyRecord.setMovie( yr.getMovie() );
        oldYearlyRecord.setThisYearPurchases( yr.getThisYearPurchases() );
        oldYearlyRecord.setCurrentTotalPurchases( yr.getCurrentTotalPurchases() );
    }

    public List<YearlyRecord> retrieveYearlyRecordsByName(String name) {
        TypedQuery<YearlyRecord> query = getEm().createQuery(
                "FROM YearlyRecord yr WHERE yr.name LIKE CONCAT('%', :name, '%')",
                YearlyRecord.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    public List<YearlyRecord> retrieveYearlyRecordsByMovieId(Long id) {
        TypedQuery<YearlyRecord> query = getEm().createQuery(
                "FROM YearlyRecord yr WHERE yr.movie.id = :id",
                YearlyRecord.class
        ).setParameter("id", id);
        return query.getResultList();
    }

    public List<YearlyRecord> retrieveYearlyRecordsByMovieTitle(String title) {
        TypedQuery<YearlyRecord> query = getEm().createQuery(
                "FROM YearlyRecord yr WHERE yr.movie.title LIKE CONCAT('%', :title, '%')",
                YearlyRecord.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    public List<YearlyRecord> retrieveYearlyRecordsBetweenDates(Date start, Date end) {
        TypedQuery<YearlyRecord> query = getEm().createQuery(
                        "FROM YearlyRecord yr WHERE yr.creationTime > :start AND yr.lastUpdateTime < :end",
                        YearlyRecord.class
                ).setParameter("start", start)
                .setParameter("end", end);
        return query.getResultList();
    }

}
