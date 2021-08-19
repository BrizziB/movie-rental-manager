package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.ProductionCompany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductionCompanyDao {

    @PersistenceContext
    private EntityManager em;

    public void addProductionCompany(ProductionCompany pc) {
        em.persist(pc);
    }

    public ProductionCompany findProductionCompanyById(Long id) {
        return em.find(ProductionCompany.class, id);
    }

    public List<ProductionCompany> findAllProductionCompanies() {
        TypedQuery<ProductionCompany> query = em.createQuery("FROM ProductionCompany", ProductionCompany.class);
        query.setFirstResult(0);
        query.setMaxResults(20);
        return query.getResultList();
    }

    public void updateProductionCompany(ProductionCompany pc) {
        ProductionCompany oldProductionCompany = em.find(ProductionCompany.class, pc.getId());
        oldProductionCompany.setName(pc.getName());
        oldProductionCompany.setCountry(pc.getCountry());
        oldProductionCompany.setFoundationDate(pc.getFoundationDate());
        oldProductionCompany.setWebSiteURL(pc.getWebSiteURL());
        oldProductionCompany.setMovies(pc.getMovies());
    }

    public void saveProductionCompany(ProductionCompany pc) {
        if(pc.getId() != null)
            em.merge(pc);
        else
            em.persist(pc);
    }

    public int deleteProductionCompanyById(Long id) {
        Query query = em.createQuery("DELETE FROM ProductionCompany pc WHERE pc.id = :id")
                        .setParameter("id", id);
        return query.executeUpdate();
    }

}
