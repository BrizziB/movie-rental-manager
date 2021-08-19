package it.unifi.ing.stlab.movierentalmanager.model;

import javax.persistence.Embeddable;

// Se si vuole criptare la tabella delle credenziali, WebUser deve essere un'entità

@Embeddable
public class WebUser {

    private String username;
    private String email;
    private String password;

    public WebUser() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
