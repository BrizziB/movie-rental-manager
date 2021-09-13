package it.unifi.ing.stlab.movierentalmanager.components.litedto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LiteProductionCompanyDto implements Serializable {

    private String name;
    private String country;
    private Date foundationDate;
    private String webSiteURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

}
