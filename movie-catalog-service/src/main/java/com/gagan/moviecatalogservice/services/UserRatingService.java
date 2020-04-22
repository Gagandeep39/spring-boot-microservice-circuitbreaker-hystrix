package com.gagan.moviecatalogservice.services;

import java.util.Arrays;

import com.gagan.moviecatalogservice.entity.Rating;
import com.gagan.moviecatalogservice.entity.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

    /**
     * Dependecy proviedd from bean in Main Class
     */
    @Autowired
    private RestTemplate template;
    
    /**
     * Method that makes a call to rating service
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
    // The values here a re different fo diferent usecases
        commandProperties = {
            // Tiemout after whch circuit must break i.e 2sec
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            // Number of reuests after which circuit breks i.e 5requests
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            // Percentage of request after which circit breaks i.e 50%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            // How long circuitbreaker sleeps before joining the circuit i.e 5 sec
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        }
    )
	public UserRating getUserRating(String userId) {
        return template.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
    }

    
    public UserRating getFallbackUserRating(@PathVariable("userId")String userId){
        UserRating rating = new UserRating();
        rating.setUserRatings(Arrays.asList(new Rating("0", 0)));
        return rating;
    }

}