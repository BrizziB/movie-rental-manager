package it.unifi.ing.stlab.movierentalmanager.service;

import com.google.gson.Gson;
import it.unifi.ing.stlab.movierentalmanager.components.controllers.CrewMemberController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("crew-members")
public class CrewMemberService {

    @Inject private CrewMemberController crewMemberController;

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveCrewMemberById(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( crewMemberController.getCrewMemberById(id) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/crew-members-names")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveCrewMembersByName(@QueryParam("name") String name) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( crewMemberController.getCrewMembersByName(name) )
            ).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/movie-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveCrewMembersByMovie(@PathParam("id") Long id) {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( crewMemberController.getCrewMembersByMovieId(id) )
            ).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @GET
    @Path("/list/all")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retrieveAllCrewMembers() {
        Gson gson = new Gson();
        try {
            return Response.ok(
                    gson.toJson( crewMemberController.getAllCrewMembers(0, 25) )
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
    public Response addNewCrewMember(String json) {
        try {
            crewMemberController.addCrewMemberToDb(json);
            return Response.ok("New crew member added").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public Response updateCrewMember(String json, @QueryParam("id") Long id) {
        try {
            crewMemberController.updateCrewMemberOnDb(json, id);
            return Response.ok("Crew member " + id + " correctly updated").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/disable")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response disableCrewMember(@QueryParam("id") Long id) {
        try {
            crewMemberController.disableCrewMemberOnDb(true, id);
            return Response.ok("Crew member " + id + " correctly disabled").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

    @POST
    @Path("/restore")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response restoreCrewMember(@QueryParam("id") Long id) {
        try {
            crewMemberController.disableCrewMemberOnDb(false, id);
            return Response.ok("Crew member " + id + " correctly restored").build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.notAcceptable(null).build();
        }
    }

}
