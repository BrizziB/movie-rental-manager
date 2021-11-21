package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.ProductionCompanyController;
import it.unifi.ing.stlab.movierentalmanager.model.filters.JWTAuthNeeded;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("production-companies")
public class ProductionCompanyService {

    @Inject private ProductionCompanyController productionCompanyController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTAuthNeeded
    @RolesAllowed("BACK_OFFICE")
    public Response retrieveProductionCompanyById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( productionCompanyController.getProductionCompanyById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/producer-name")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveProductionCompaniesByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( productionCompanyController.getProductionCompaniesByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/movie-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveProductionCompaniesByMovie(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( productionCompanyController.getProductionCompaniesByMovieId(id) )
            ).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllProductionCompaniesByName(@QueryParam("from") int idx_start,
                                                         @QueryParam("to") int idx_end,
                                                         @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( productionCompanyController.getAllProductionCompanies(idx_start, idx_end-idx_start, orderBy) )
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
    @RolesAllowed("BACK_OFFICE")
    public Response addNewProductionCompany(String json) {
        try {
            productionCompanyController.addProductionCompanyToDb(json);
            return Response.status(Response.Status.OK).entity("New production company added").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response updateProductionCompany(String json, @QueryParam("id") Long id) {
        try {
            productionCompanyController.updateProductionCompanyOnDb(json, id);
            return Response.status(Response.Status.OK).entity("ProductionCompany " + id + " correctly updated").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response disableProductionCompany(@QueryParam("id") Long id) {
        try {
            productionCompanyController.disableProductionCompanyOnDb(true, id);
            return Response.status(Response.Status.OK).entity("ProductionCompany " + id + " correctly disabled").build();
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
    @RolesAllowed("BACK_OFFICE")
    public Response restoreProductionCompany(@QueryParam("id") Long id) {
        try {
            productionCompanyController.disableProductionCompanyOnDb(false, id);
            return Response.status(Response.Status.OK).entity("ProductionCompany " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
