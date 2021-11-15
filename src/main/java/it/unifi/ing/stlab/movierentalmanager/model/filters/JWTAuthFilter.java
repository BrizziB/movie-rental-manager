package it.unifi.ing.stlab.movierentalmanager.model.filters;

import it.unifi.ing.stlab.movierentalmanager.dao.CustomerDao;
import it.unifi.ing.stlab.movierentalmanager.dao.EmployeeDao;
import it.unifi.ing.stlab.movierentalmanager.model.entity.BaseEntity;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
@JWTAuthNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthFilter implements ContainerRequestFilter {

    @Inject EmployeeDao employeeDao;
    @Inject CustomerDao customerDao;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if( authorizationHeader == null || authorizationHeader.isEmpty() ) {
            // Without an AUTHORIZATION Header, a client is considered UNAUTHORIZED
            requestContext.abortWith(
                    Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("This Request is UNAUTHORIZED")
                            .type("text/plain")
                            .build()
            );
            return;
        }

        String username = null;
        if( authorizationHeader.startsWith("Bearer") || authorizationHeader.startsWith("BEARER") ) {
            // Authorization: Bearer base64credentials
            String base64Token = authorizationHeader.substring( "Bearer".length() ).trim();
            JwtUtil jwtUtil = new JwtUtil();
            username = jwtUtil.extractUsername(base64Token);

            WebUser webUser = null;

            webUser = employeeDao.retrieveWebUserByUsername(username) != null ?
                    employeeDao.retrieveWebUserByUsername(username) :
                    customerDao.retrieveWebUserByUsername(username);

            if( webUser == null ) {
                requestContext.abortWith(
                        Response
                                .status(Response.Status.UNAUTHORIZED)
                                .entity("This Request is UNAUTHORIZED")
                                .type("text/plain")
                                .build()
                );
                return;
            }

            if( !jwtUtil.validateToken(base64Token, webUser) ) {
                requestContext.abortWith(
                        Response
                                .status(Response.Status.UNAUTHORIZED)
                                .entity("This token is INVALID")
                                .type("text/plain")
                                .build()
                );
                return;
            }

            requestContext.setSecurityContext( new JWTSecurityContext(username, requestContext) );
        }
    }

    public class JWTSecurityContext implements SecurityContext {

        private String principalUsername;
        private ContainerRequestContext requestContext;

        public JWTSecurityContext(String principalUsername, ContainerRequestContext requestContext) {
            this.principalUsername = principalUsername;
            this.requestContext = requestContext;
        }

        @Override
        public Principal getUserPrincipal() {
            return new Principal() {
                @Override
                public String getName() {
                    return principalUsername;
                }
            };
        }

        @Override
        public boolean isUserInRole(String role) {
            BaseEntity entity;
            entity = employeeDao.findByUsername(principalUsername) != null ?
                    employeeDao.findByUsername(principalUsername) :
                    customerDao.findByUsername(principalUsername);
            if(entity instanceof Employee)
                return ((Employee) entity).getRole().name().equals(role);
            else
                return false; // otherwise, implement Customer roles
        }

        @Override
        public boolean isSecure() {
            return requestContext.getSecurityContext().isSecure();
        }

        @Override
        public String getAuthenticationScheme() {
            // DIGEST if is encrypted
            // https://stackoverflow.com/questions/9534602/what-is-the-difference-between-digest-and-basic-authentication
            return SecurityContext.BASIC_AUTH;
        }
    }
}
