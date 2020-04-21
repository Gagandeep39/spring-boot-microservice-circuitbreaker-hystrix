/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:17:56
 * @modify date 2020-04-21 01:17:56
 * @desc [description]
 */
package com.gagan.moviecatalogservice.entity;

public class Rating {

    private String movieId;
    private int rating;


    public Rating() {
    }

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
            " movieId='" + getMovieId() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }


}