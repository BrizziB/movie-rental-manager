package it.unifi.ing.stlab.movierentalmanager.dao;

import it.unifi.ing.stlab.movierentalmanager.model.ProductionCompany;

import javax.transaction.Transactional;

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

}
