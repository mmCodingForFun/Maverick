package org.example.data.population;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieComponent {

    @Value("${movieUrl}")
    private String movieUrlString;

    @Value("${singleRowUrl}")
    private String singleRowUrlString;

    @Autowired
    private CloseableHttpClient client;

    @Autowired
    private ResponseHandler<String> responseHandler;

    @Autowired
    public MovieComponent() {
    }

    protected List<MovieHttpResponse> getMoviesByKeyword(String keyword) {
        //This stuff should be configurable in some kind of Factory I'm guessing,
        // to generate HTTP Gets with correct configuration, as the connection isn't actually
        // active until we do client.execute( .. ) but I didn't get that far
        HttpGet get = new HttpGet(movieUrlString.replace("REPLACE_ME", keyword));

        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");

        String response = null;
        try {
            response = client.execute(get, responseHandler);
        } catch (Exception e) {
            System.out.println("Error executing GET request: " + e.getMessage());
        }

        return parseMovieListJson(response);

    }

    protected MovieHttpResponse getMovieById(String id) {
        HttpGet get = new HttpGet(movieUrlString.replace("REPLACE_ME", id));
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");
        String response = null;
        try {
            response = client.execute(get, responseHandler);
        } catch (Exception e) {
            System.out.println("Error executing GET request: " + e.getMessage());
        }

        return parseMovieJson(response);

    }

    private List<MovieHttpResponse> parseMovieListJson(String jsonResponse) {
        List<MovieHttpResponse> movies = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            movies = mapper.readValue(jsonResponse, new TypeReference<List<MovieHttpResponse>>() {
            });
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    private MovieHttpResponse parseMovieJson(String jsonResponse) {
        MovieHttpResponse movie = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            movie = mapper.readValue(jsonResponse, MovieHttpResponse.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }

}
