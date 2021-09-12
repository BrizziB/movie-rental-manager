package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MonthlyRecordController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("monthly-records")
public class MonthlyRecordService {

    @Inject private MonthlyRecordController monthlyRecordController;

    @GET
    @Path("/searchById")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordById(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getMonthlyRecordById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByName")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByMovieId")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByMovieId(@QueryParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByMovieTitle")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/searchByDate")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsBetweenDates(start, end) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllMonthlyRecords() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( monthlyRecordController.getAllMonthlyRecords(0, 25) )
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
    public Response updateMonthlyRecord(String json, @QueryParam("id") Long id) {
        try {
            monthlyRecordController.updateMonthlyRecordOnDb(json, id);
            return Response.ok("Monthly record " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableMonthlyRecord(@QueryParam("id") Long id) {
        try {
            monthlyRecordController.disableMonthlyRecordOnDb(true, id);
            return Response.ok("Monthly record " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreMonthlyRecord(@QueryParam("id") Long id) {
        try {
            monthlyRecordController.disableMonthlyRecordOnDb(false, id);
            return Response.ok("Monthly record " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
