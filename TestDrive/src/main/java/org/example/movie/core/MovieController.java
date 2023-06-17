package org.example.movie.core;

import org.example.Movie;
import org.example.mongodb.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MongoDao dao;

    public MovieController() {
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieByImbdId(@PathVariable("id") String id) {
        return dao.findByImbdId(id);
    };

    //Not enough time to complete the rest of this


}
