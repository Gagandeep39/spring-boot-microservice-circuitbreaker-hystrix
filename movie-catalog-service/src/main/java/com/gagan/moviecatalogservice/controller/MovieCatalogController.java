/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:01:32
 * @modify date 2020-04-21 01:01:32
 * @desc [description]
 */
package com.gagan.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gagan.moviecatalogservice.entity.CatalogItem;
import com.gagan.moviecatalogservice.entity.Movie;
import com.gagan.moviecatalogservice.entity.Rating;
import com.gagan.moviecatalogservice.entity.UserRating;
import com.gagan.moviecatalogservice.services.MovieInfoService;
import com.gagan.moviecatalogservice.services.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private MovieInfoService movieInfoService;
    @Autowired
    private UserRatingService userRatingService;
    /**
     * Successor to RestTemplate
     * RestTemplate ill be deprecated n later version 
     * WebClient Requires Spring reactive dependency
     */
    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * Method that makes a call from 2 microservices
     * @param userId 
     * @return
     */
    @GetMapping("/{userId}")
    // @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        // Synchronous methos
        // UserRating userRating = template.getForObject("http://localhost:2002/ratingsdata/users/" + userId, UserRating.class);
        //  After regstering, we replace it with service client
        UserRating userRating = userRatingService.getUserRating(userId);

        return userRating.getUserRatings().stream().map(rating -> {
            return movieInfoService.getCatalogItem(rating);
        }).collect(Collectors.toList());
    }
    // Initial fallback method 
    // public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
    //     System.out.println("Fallback method: MovieInfo");
    //     return Arrays.asList(new CatalogItem("No Movie", "", 0));
    // }
   


}


            // synchrounous, but can be converted to Asynchronous by removing the block
            // webClientBuilder.build()
            //                 .get()  // Type of request methid 
            //                 .uri("http://localhost:2001/movies/" + rating.getMovieId())
            //                 .retrieve()
            //                 .bodyToMono(Movie.class)    // Convert thre returned data into instace of Movie Class, it will be recieved after sometime, similar to Observable<Movie>
            //                 .block();   // Similar to subscribe, also block further execution until a null/actual response is recieved
