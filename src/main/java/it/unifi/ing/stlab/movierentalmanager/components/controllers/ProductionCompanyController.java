package it.unifi.ing.stlab.movierentalmanager.components.controllers;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.dto.ProductionCompanyDto;
import it.unifi.ing.stlab.movierentalmanager.components.litedto.LiteProductionCompanyDto;
import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.components.mappers.ProductionCompanyMapper;
import it.unifi.ing.stlab.movierentalmanager.dao.MovieDao;
import it.unifi.ing.stlab.movierentalmanager.dao.ProductionCompanyDao;
import it.unifi.ing.stlab.movierentalmanager.model.movies.Movie;
import it.unifi.ing.stlab.movierentalmanager.model.movies.ProductionCompany;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProductionCompanyController {

    @Inject private ProductionCompanyDao productionCompanyDao;
    @Inject private ProductionCompanyMapper productionCompanyMapper;
    @Inject private MovieDao movieDao;
    private Gson gson;

    public LiteProductionCompanyDto getProductionCompanyById(Long id) {
        ProductionCompany productionCompany = productionCompanyDao.findById(id)
                                                                  .orElseThrow(
                                                                          () -> new IllegalArgumentException("Production company not found")
                                                                  );
        return productionCompanyMapper.convert(productionCompany);
    }

    public List<LiteProductionCompanyDto> getProductionCompaniesByName(String name) {
        return productionCompanyDao.retrieveProductionCompaniesByName(name)
                                   .stream()
                                   .map(productionCompanyMapper::convert)
                                   .collect(Collectors.toList());
    }

    public List<LiteProductionCompanyDto> getProductionCompaniesByMovieId(Long id) {
        Movie movie = movieDao.fetchMovieWithProductionCompanies(id);
        return movie.getProducers()
                .stream()
                .map(productionCompanyMapper::convert)
                .collect(Collectors.toList());
    }

    public List<LiteProductionCompanyDto> getAllProductionCompanies(Integer offset, Integer limit, List<String> orderBy) {
        return productionCompanyDao.findAll(offset, limit, orderBy)
                .stream()
                .map(productionCompanyMapper::convert)
                .collect(Collectors.toList());
    }

    public void addProductionCompanyToDb(String json) {
        gson = new Gson();
        ProductionCompanyDto dto = gson.fromJson(json, ProductionCompanyDto.class);
        ProductionCompany productionCompany = ModelFactory.initProductionCompany();
        productionCompanyMapper.transfer(dto, productionCompany);
        productionCompanyDao.add(productionCompany);
    }

    public void updateProductionCompanyOnDb(String json, Long id) {
        gson = new Gson();
        ProductionCompanyDto dto = gson.fromJson(json, ProductionCompanyDto.class);
        ProductionCompany oldProductionCompany = productionCompanyDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any production company on database")
        );
        productionCompanyMapper.transfer(dto, oldProductionCompany);
        productionCompanyDao.updateProductionCompany(oldProductionCompany);
    }

    public void disableProductionCompanyOnDb(boolean disabled, Long id) {
        ProductionCompany productionCompany = productionCompanyDao.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID not corresponding to any production company on database")
        );
        productionCompany.setDisabled(disabled);
        productionCompanyDao.save(productionCompany);
    }

}
