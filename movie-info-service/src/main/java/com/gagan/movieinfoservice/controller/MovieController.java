/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:10:25
 * @modify date 2020-04-21 01:10:25
 * @desc [description]
 */
package com.gagan.movieinfoservice.controller;

import com.gagan.movieinfoservice.Entity.Movie;
import com.gagan.movieinfoservice.Entity.MovieSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private RestTemplate template;

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        MovieSummary summary = template.getForObject("https://api.themoviedb.org/3/movie/" + movieId +"?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieId, summary.getTitle(), summary.getOverview());
    }

}