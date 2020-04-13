package com.huseyink.ratingsdataservice.controller;

import com.huseyink.ratingsdataservice.domain.Rating;
import com.huseyink.ratingsdataservice.domain.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataController {


    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)
    {
        return new Rating(movieId,4);
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId")String userId)
    {
        List<Rating> ratings =  Arrays.asList(
                new Rating("1", 9),
                new Rating("2",4),
                new Rating("3", 7),
                new Rating("4",1)
        );

        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
