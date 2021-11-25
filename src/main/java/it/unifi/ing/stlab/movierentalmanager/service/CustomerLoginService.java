package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.model.filters.*;
import it.unifi.ing.stlab.movierentalmanager.model.users.PasswordHash;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login-customer")
public class CustomerLoginService implements UserAuthenticator {

    @Inject private CustomerDao customerDao;
    private Gson gson;
    private JwtUtil jwtTokenUtil;

    private boolean isValidPassword(String plainPass, String hashedPass) {
        PasswordHash passwordHash = new PasswordHash();
        return passwordHash.createPasswordKey(plainPass).equals(hashedPass);
    }

    private void authenticate(String userIn, String passIn) throws BadCredentialsException {
        Long customerID = customerDao.retrieveCustomerIDByUsername( userIn )
                                     .orElseThrow( () -> new BadCredentialsException("Your credentials are incorrect") );

        String customerHashedPass = customerDao.findById(customerID)
                                         .get()
                                         .getWebUser()
                                         .getPassword();

        if( !isValidPassword(passIn, customerHashedPass) )
            throw new BadCredentialsException("Your credentials are incorrect");
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
