package org.example;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/secondo")
public class SecondoMicroservizioResource {

    @GET
    @Path("/tre")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getUnoMessage() {
        return Json.createObjectBuilder()
                .add("message", "Sono il microservizio secondo /3")
                .build();
    }

    @GET
    @Path("/quattro")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDueMessage() {
        return Json.createObjectBuilder()
                .add("message", "Sono il microservizio secondo /4")
                .build();
    }
}
