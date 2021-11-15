package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.WeeklyRecordController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("weekly-records")
public class WeeklyRecordService {

    @Inject private WeeklyRecordController weeklyRecordController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveWeeklyRecordById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getWeeklyRecordById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/weeklies-names")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveWeeklyRecordsByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getWeeklyRecordsByName(name) )
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
    public Response retrieveWeeklyRecordsByMovieId(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getWeeklyRecordsByMovieId(id) )
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
    public Response retrieveWeeklyRecordsByMovieTitle(@QueryParam("title") String title) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getWeeklyRecordsByMovieTitle(title) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

    @GET
    @Path("/list/weeklies-dates")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveWeeklyRecordsByDate(@QueryParam("start") Date start, @QueryParam("end") Date end) {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getWeeklyRecordsBetweenDates(start, end) )
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
    public Response retrieveAllWeeklyRecords() {
        Gson gson = new Gson();
        try {
            return Response.status(Response.Status.OK).entity(
                    gson.toJson( weeklyRecordController.getAllWeeklyRecords(0, 25) )
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
    public Response updateWeeklyRecord(String json, @QueryParam("id") Long id) {
        try {
            weeklyRecordController.updateWeeklyRecordOnDb(json, id);
            return Response.status(Response.Status.OK).entity("Weekly record " + id + " correctly updated").build();
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
    public Response disableWeeklyRecord(@QueryParam("id") Long id) {
        try {
            weeklyRecordController.disableWeeklyRecordOnDb(true, id);
            return Response.status(Response.Status.OK).entity("Weekly record " + id + " correctly disabled").build();
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
    public Response restoreWeeklyRecord(@QueryParam("id") Long id) {
        try {
            weeklyRecordController.disableWeeklyRecordOnDb(false, id);
            return Response.status(Response.Status.OK).entity("Weekly record " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(null)
                    .build();
        }
    }

}
