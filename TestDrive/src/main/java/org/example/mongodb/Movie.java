package org.example;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("movie")
public class Movie {

    private String imdbID;
    private String title;
    private List<String> actors;
    private String genre;
    private Double Rating;
    private Integer Year;


}
