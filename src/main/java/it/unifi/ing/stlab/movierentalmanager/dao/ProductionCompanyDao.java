package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.ProductionCompany;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ProductionCompanyDao extends BaseDao<ProductionCompany> {

    public ProductionCompanyDao() {
        super(ProductionCompany.class);
    }

    @Transactional
    public void updateProductionCompany(ProductionCompany pc) {
        ProductionCompany oldProductionCompany = getEm().find(ProductionCompany.class, pc.getId());
        oldProductionCompany.setName(pc.getName());
        oldProductionCompany.setCountry(pc.getCountry());
        oldProductionCompany.setFoundationDate(pc.getFoundationDate());
        oldProductionCompany.setWebSiteURL(pc.getWebSiteURL());
        oldProductionCompany.setMovies(pc.getMovies());
    }

    public List<ProductionCompany> retrieveProductionCompaniesByName(String name) {
        TypedQuery<ProductionCompany> query = getEm().createQuery(
                "FROM ProductionCompany pc WHERE pc.name LIKE CONCAT('%', :name, '%')",
                ProductionCompany.class
        ).setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    public ProductionCompany fetchProductionCompanyWithMovies(Long id) {
        TypedQuery<ProductionCompany> query = getEm().createQuery(
                "FROM ProductionCompany pc JOIN FETCH pc.movies WHERE pc.id = :id",
                ProductionCompany.class
        ).setParameter("id", id);
        return query.getSingleResult();
    }

}
