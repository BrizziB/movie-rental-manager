package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.dao.EmployeeDao;
import it.unifi.ing.stlab.movierentalmanager.model.filters.*;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login-employee")
public class EmployeeLoginService implements UserAuthenticator {

    @Inject private EmployeeDao employeeDao;
    private Gson gson;
    private JwtUtil jwtTokenUtil;

    private void authenticate(String inputUser, String inputPass) throws BadCredentialsException {
        Long customerID = employeeDao.retrieveEmployeeIDByUsername( inputUser )
                                     .orElseThrow( () -> new BadCredentialsException("") );

        String customerPassword = employeeDao.findById(customerID)
                .get()
                .getWebUser()
                .getPassword();

        if( !customerPassword.equals(inputPass) )
            throw new BadCredentialsException("");
    }

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAuthenticationToken(String json) throws Exception {
        gson = new Gson();
        AuthenticationRequest authenticationRequest = gson.fromJson(json, AuthenticationRequest.class);

        try {
            authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword() );
        } catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);

        }

        jwtTokenUtil = new JwtUtil();
        final String jwt = jwtTokenUtil.generateToken( authenticationRequest.getUsername() );
        return Response.status(Response.Status.OK).entity( new AuthenticationResponse(jwt).getJwt() ).build();

    }

}
