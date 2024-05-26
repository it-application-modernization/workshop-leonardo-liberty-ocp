package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/")
public class TerzoMicroservizioResource {


    private static final StringBuilder endpoint1 = new StringBuilder();
    private static final StringBuilder endpoint2 = new StringBuilder();
    private static final StringBuilder endpoint3 = new StringBuilder();
    private static final StringBuilder endpoint4 = new StringBuilder();

    private static final String[] URLs = {
            endpoint1.append(System.getenv("primo-microservizio-endpoint")).append("/uno").toString(),
            endpoint2.append(System.getenv("primo-microservizio-endpoint")).append("/due").toString(),
            endpoint3.append(System.getenv("secondo-microservizio-endpoint")).append("/tre").toString(),
            endpoint4.append(System.getenv("secondo-microservizio-endpoint")).append("/quattro").toString()
    };

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getAggregatedResults() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><h1>Risultati delle chiamate</h1><ul>");

        for (String urlStr : URLs) {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            Scanner scanner = new Scanner(is);
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();
            sb.append("<li>").append(response).append("</li>");
        }

        sb.append("</ul></body></html>");
        return sb.toString();
    }
}
