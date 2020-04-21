/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 02:43:07
 * @modify date 2020-04-21 02:43:07
 * @desc [description]
 */
package com.gagan.moviecatalogservice.entity;

import java.util.List;

public class UserRating {

    private List<Rating> userRatings;


    public List<Rating> getUserRatings() {
        return this.userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }


}