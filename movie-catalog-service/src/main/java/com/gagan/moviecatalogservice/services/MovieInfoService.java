package com.gagan.moviecatalogservice.services;

import java.util.Arrays;
import java.util.List;

import com.gagan.moviecatalogservice.entity.CatalogItem;
import com.gagan.moviecatalogservice.entity.Movie;
import com.gagan.moviecatalogservice.entity.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * HYterix wont work when 2 circuot breakers a mplemented in a single class
 * THe problem is in the wa its implemented
 */
@Service
public class MovieInfoService {

    /**
     * Dependecy proviedd from bean in Main Class
     */
    @Autowired
    private RestTemplate template;


    /**
     * Mthid to make a call to movie info servicee 
     * Its fallback method returns a simple dummy data
     * @param rating
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating){
        Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

   
    public CatalogItem getFallbackCatalogItem(Rating rating){
        System.out.println("Fallback method: UserRating");
        return new CatalogItem("Movie not found", "", 0);
    }


}