package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "production_companies")
public class ProductionCompany extends BaseEntity{

    private String name;
    private String country;
    private Date foundationDate;
    private String webSiteURL;

    public ProductionCompany(){

    }


}
