package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.EmployeeController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
public class EmployeeService {

    @Inject
    private EmployeeController employeeController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"BACK_OFFICE", "ADMIN"})
    public Response retrieveEmployeeById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( employeeController.getEmployeeById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/employees-names")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"BACK_OFFICE", "ADMIN"})
    public Response retrieveEmployeeByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( employeeController.getEmployeesByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed({"BACK_OFFICE", "ADMIN"})
    public Response retrieveAllEmployees() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( employeeController.getAllEmployees(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed("ADMIN")
    public Response addNewEmployee(String json) {
        try {
            employeeController.addEmployeeToDb(json);
            return Response.status(Response.Status.OK).entity("New employee added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed("ADMIN")
    public Response updateEmployee(String json, @QueryParam("id") Long id) {
        try {
            employeeController.updateEmployeeOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Employee " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed("ADMIN")
    public Response disableEmployee(@QueryParam("id") Long id) {
        try {
            employeeController.disableEmployeeOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Employee " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTAuthNeeded
    @RolesAllowed("ADMIN")
    public Response restoreEmployee(@QueryParam("id") Long id) {
        try {
            employeeController.disableEmployeeOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Employee " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}