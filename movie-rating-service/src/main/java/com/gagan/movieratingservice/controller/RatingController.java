/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:18:11
 * @modify date 2020-04-21 01:18:11
 * @desc [description]
 */
package com.gagan.movieratingservice.controller;

import java.util.Arrays;
import java.util.List;

import com.gagan.movieratingservice.entity.Rating;
import com.gagan.movieratingservice.entity.UserRating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @RequestMapping(value="/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping(value="users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 4),
            new Rating("5678", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);
        return userRating;
    }
    

}