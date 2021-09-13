package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.YearlyRecordController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("yearly-records")
public class YearlyRecordService {

    @Inject
    private YearlyRecordController yearlyRecordController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveYearlyRecordById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getYearlyRecordById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/yearlies-names")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveYearlyRecordsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getYearlyRecordsByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movie-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveYearlyRecordsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getYearlyRecordsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movies-titles")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveYearlyRecordsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getYearlyRecordsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/yearlies-dates")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveYearlyRecordsByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getYearlyRecordsBetweenDates(start, end) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllYearlyRecords() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( yearlyRecordController.getAllYearlyRecords(0, 25) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    // ADD

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateYearlyRecord(String json, @QueryParam("id") Long id) {
        try {
            yearlyRecordController.updateYearlyRecordOnDb(json, id);
            return Response.ok("Yearly record " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableYearlyRecord(@QueryParam("id") Long id) {
        try {
            yearlyRecordController.disableYearlyRecordOnDb(true, id);
            return Response.ok("Yearly record " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreYearlyRecord(@QueryParam("id") Long id) {
        try {
            yearlyRecordController.disableYearlyRecordOnDb(false, id);
            return Response.ok("Yearly record " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
