/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:01:32
 * @modify date 2020-04-21 01:01:32
 * @desc [description]
 */
package com.gagan.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.gagan.moviecatalogservice.entity.CatalogItem;
import com.gagan.moviecatalogservice.entity.Movie;
import com.gagan.moviecatalogservice.entity.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        // Synchronous methos
        // UserRating userRating = template.getForObject("http://localhost:2002/ratingsdata/users/" + userId, UserRating.class);
        //  After regstering, we replace it with service client
        UserRating userRating = template.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);

        // List<Rating> ratings = Arrays.asList(
        //     new Rating("1234", 4),
        //     new Rating("5678", 5)
        // );
        
        return userRating.getUserRatings().stream().map(rating -> {
            // Synchronous method
            Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            // synchrounous, but can be converted to Asynchronous by removing the block
            // webClientBuilder.build()
            //                 .get()  // Type of request methid 
            //                 .uri("http://localhost:2001/movies/" + rating.getMovieId())
            //                 .retrieve()
            //                 .bodyToMono(Movie.class)    // Convert thre returned data into instace of Movie Class, it will be recieved after sometime, similar to Observable<Movie>
            //                 .block();   // Similar to subscribe, also block further execution until a null/actual response is recieved

            return new CatalogItem(movie.getName(), "description", rating.getRating());
        }).collect(Collectors.toList());
    }

}