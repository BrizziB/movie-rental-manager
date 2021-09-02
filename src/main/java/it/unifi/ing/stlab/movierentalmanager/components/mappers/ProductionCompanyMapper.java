package it.unifi.ing.stlab.movierentalmanager.components.mappers;

import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteMovieDto;
import it.unifi.ing.stlab.movierentalmanager.components.dto.LiteProductionCompanyDto;
import it.unifi.ing.stlab.movierentalmanager.model.ProductionCompany;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ProductionCompanyMapper {

    public LiteProductionCompanyDto convert(ProductionCompany pc) {
        if(pc == null)
            throw new MapperConversionException("The production company is NULL");

        LiteProductionCompanyDto dto = new LiteProductionCompanyDto();

        dto.setName(pc.getName());
        dto.setCountry(pc.getCountry());
        dto.setFoundationDate(pc.getFoundationDate());
        dto.setWebSiteURL(pc.getWebSiteURL());

        return dto;
    }

    public void transfer(LiteProductionCompanyDto dto, ProductionCompany pc) {
        if(dto == null)
            throw new MapperTransferException("The production company DTO is NULL");
        if(pc == null)
            throw new MapperTransferException("The production company Entity is NULL");

        if(dto.getName() != null)
            pc.setName(dto.getName());
        if(dto.getCountry() != null)
            pc.setCountry(dto.getCountry());
        if(dto.getFoundationDate() != null)
            pc.setFoundationDate(dto.getFoundationDate());
        if(dto.getWebSiteURL() != null)
            pc.setWebSiteURL(dto.getWebSiteURL());
    }

}
