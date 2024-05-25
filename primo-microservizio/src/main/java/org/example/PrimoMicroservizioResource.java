package org.example;

import java.util.Properties;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/primo")
public class PrimoMicroservizioResource {

    @GET
    @Path("/uno")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getUnoMessage() {
        return Json.createObjectBuilder()
                .add("message", "Sono il microservizio primo /uno")
                .build();
    }

    @GET
    @Path("/due")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDueMessage() {
        return Json.createObjectBuilder()
                .add("message", "Sono il microservizio primo /due")
                .build();
    }
}
