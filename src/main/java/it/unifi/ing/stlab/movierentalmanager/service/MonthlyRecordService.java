package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.MonthlyRecordController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("monthly-records")
public class MonthlyRecordService {

    @Inject private MonthlyRecordController monthlyRecordController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getMonthlyRecordById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/monthlies-names")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByName(name) )
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
    public Response retrieveMonthlyRecordsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByMovieId(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/movies-titles")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/monthlies-dates")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveMonthlyRecordsByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getMonthlyRecordsBetweenDates(start, end) )
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
    public Response retrieveAllMonthlyRecords(@QueryParam("from") int idx_start,
                                              @QueryParam("to") int idx_end,
                                              @QueryParam("orderBy") List<String> orderBy) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( monthlyRecordController.getAllMonthlyRecords(idx_start, idx_end-idx_start, orderBy) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
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
            return Response.status(Response.Status.OK).entity("Monthly record " + id + " correctly updated").build();
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
    public Response disableMonthlyRecord(@QueryParam("id") Long id) {
        try {
            monthlyRecordController.disableMonthlyRecordOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Monthly record " + id + " correctly disabled").build();
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
    public Response restoreMonthlyRecord(@QueryParam("id") Long id) {
        try {
            monthlyRecordController.disableMonthlyRecordOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Monthly record " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
