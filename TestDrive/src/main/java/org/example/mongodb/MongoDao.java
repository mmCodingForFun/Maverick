package org.example.mongodb;

import org.example.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
//Should implement a custom interface, MongoRepository, but I don't have time to implement all those functions
public class MongoDao {

    @Autowired
    MongoTemplate template;

    public Movie insert(Movie entity){
        return template.insert(entity);
    }

    public List<Movie> insert(List<Movie> entities) {
        return template.insert(entities);
    }

    public Boolean delete(Movie entity){
        return template.remove(entity).wasAcknowledged();
    }

    public Movie update(Movie entity){
        //only works if ImbdId is registered as an ID
        return template.save(entity);
    }

    public Movie findByImbdId(String imdbId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));
        return template.findOne(query, Movie.class);
    }

    public List<Movie> findByTitle(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return template.find(query, Movie.class);
    }

    public List<Movie> findByActors(List<String> actors){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").in(actors));
        return template.find(query, Movie.class);
    }

    public List<Movie> findByGenre(String genre){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is(genre));
        return template.find(query, Movie.class);
    }

    public List<Movie> findByRating(Double rating){
        Query query = new Query();
        query.addCriteria(Criteria.where("rating").gte(rating));
        return template.find(query, Movie.class);
    }

    public List<Movie> findByYear(Integer year){
        Query query = new Query();
        query.addCriteria(Criteria.where("year").is(year);
    }



}