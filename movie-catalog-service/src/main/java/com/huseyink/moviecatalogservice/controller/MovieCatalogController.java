package com.huseyink.moviecatalogservice.controller;

import com.huseyink.moviecatalogservice.domain.CatalogItem;
import com.huseyink.moviecatalogservice.domain.Movie;
import com.huseyink.moviecatalogservice.domain.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/catalog" )
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping( "/{userId}" )
    public List<CatalogItem> getCatalog(@PathVariable( "userId" ) String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

            return ratings.getUserRating().stream().map(rating -> {
                // For each movie ID, call info service and get details
                Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                // Put them all together
                return new CatalogItem(movie.getName(), "Desc", rating.getRating());
            })
                    .collect(Collectors.toList());




    }

    @GetMapping( "/data" )
    public String sayHello() {
        return "test";
    }

}
