package com.github.macgarcia.backend.uol.regra;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macgarcia
 */
public class ChamadaExternaVingadores implements ChamadaExterna {

    private final String pathJson = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";

    @Override
    public String getDados() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(pathJson))
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return response.body();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ChamadaExternaVingadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
