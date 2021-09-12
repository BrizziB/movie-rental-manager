package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.statistics.MonthlyRecord;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
public class MonthlyRecordDao extends BaseDao<MonthlyRecord> {

    public MonthlyRecordDao() {
        super(MonthlyRecord.class);
    }

    @Transactional
    public void update(MonthlyRecord mr) {
        MonthlyRecord oldMonthlyRecord = getEm().find( MonthlyRecord.class, mr.getId() );
        oldMonthlyRecord.setMovie( mr.getMovie() );
        oldMonthlyRecord.setThisMonthPurchases( mr.getThisMonthPurchases() );
        oldMonthlyRecord.setCurrentTotalPurchases( mr.getCurrentTotalPurchases() );
    }

    public List<MonthlyRecord> retrieveMonthlyRecordsByName(String name) {
        TypedQuery<MonthlyRecord> query = getEm().createQuery(
                "FROM MonthlyRecord mr WHERE mr.name LIKE CONCAT('%', :name, '%')",
                MonthlyRecord.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    public List<MonthlyRecord> retrieveMonthlyRecordsByMovieId(Long id) {
        TypedQuery<MonthlyRecord> query = getEm().createQuery(
                "FROM MonthlyRecord mr WHERE mr.movie.id = :id",
                MonthlyRecord.class
        ).setParameter("id", id);
        return query.getResultList();
    }

    public List<MonthlyRecord> retrieveMonthlyRecordsByMovieTitle(String title) {
        TypedQuery<MonthlyRecord> query = getEm().createQuery(
                "FROM MonthlyRecord mr WHERE mr.movie.title LIKE CONCAT('%', :title, '%')",
                MonthlyRecord.class
        ).setParameter("title", title);
        return query.getResultList();
    }

    public List<MonthlyRecord> retrieveMonthlyRecordsBetweenDates(Date start, Date end) {
        TypedQuery<MonthlyRecord> query = getEm().createQuery(
                        "FROM MonthlyRecord mr WHERE mr.creationTime > :start AND mr.creationTime < :end",
                        MonthlyRecord.class
                ).setParameter("start", start)
                .setParameter("end", end);
        return query.getResultList();
    }

}
