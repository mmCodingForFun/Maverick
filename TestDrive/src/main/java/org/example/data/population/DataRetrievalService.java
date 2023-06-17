package org.example.data.population;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataRetrievalService {

    @Autowired
    MovieComponent service;

    List<MovieHttpResponse> movies;
    List<MovieHttpResponse> moviesById;

    public DataRetrievalService() {
        this.movies = new ArrayList<>();
        List<String> keywords = List.of("Avatar", "Tiger", "Bear", "Crane", "Ninja", "Football", "Panda", "Horse", "Dog", "Wolf", "Rabbit", "Hare", "Snake", "Dragon", "Bear");
        for (String s : keywords) {
            movies.addAll(service.getMoviesByKeyword(s));
        }
        for (MovieHttpResponse m : movies) {
            moviesById.add(service.getMovieById(m.getImdbId()));
        }
    }

    public List<MovieHttpResponse> getMovies() {
        return this.movies;
    }
}
