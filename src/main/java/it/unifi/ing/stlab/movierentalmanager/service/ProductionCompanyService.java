package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.ProductionCompanyController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("production-companies")
public class ProductionCompanyService {

    @Inject private ProductionCompanyController productionCompanyController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveProductionCompanyById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( productionCompanyController.getProductionCompanyById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/producer-name")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveProductionCompaniesByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( productionCompanyController.getProductionCompaniesByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movie-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveProductionCompaniesByMovie(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( productionCompanyController.getProductionCompaniesByMovieId(id) )
            ).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllProductionCompaniesByName() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( productionCompanyController.getAllProductionCompanies(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response addNewProductionCompany(String json) {
        try {
            productionCompanyController.addProductionCompanyToDb(json);
            return Response.ok("New production company added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateProductionCompany(String json, @QueryParam("id") Long id) {
        try {
            productionCompanyController.updateProductionCompanyOnDb(json, id);
            return Response.ok("ProductionCompany " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableProductionCompany(@QueryParam("id") Long id) {
        try {
            productionCompanyController.disableProductionCompanyOnDb(true, id);
            return Response.ok("ProductionCompany " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreProductionCompany(@QueryParam("id") Long id) {
        try {
            productionCompanyController.disableProductionCompanyOnDb(false, id);
            return Response.ok("ProductionCompany " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
