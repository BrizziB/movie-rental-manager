package it.unifi.ing.stlab.movierentalmanager.model.users;

import javax.persistence.Embeddable;

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
        PasswordHash passwordHash = new PasswordHash();
        this.password = passwordHash.createPasswordKey(password );
    }

    public boolean isValidPassword(String password) {
        PasswordHash passwordHash = new PasswordHash();
        return passwordHash.createPasswordKey(password).equals(this.password);
    }

}
