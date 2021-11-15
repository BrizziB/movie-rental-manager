package it.unifi.ing.stlab.movierentalmanager.model.filters;

import javax.ws.rs.core.Response;

public interface UserAuthenticator {

    public Response createAuthenticationToken(String json) throws Exception;

}
