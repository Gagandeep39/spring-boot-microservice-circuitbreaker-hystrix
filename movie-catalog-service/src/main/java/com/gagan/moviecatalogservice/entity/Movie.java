/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-04-21 01:10:22
 * @modify date 2020-04-21 01:10:22
 * @desc [description]
 */
package com.gagan.moviecatalogservice.entity;

public class Movie {

    private String movieId;
    private String name;


    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public Movie() {
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
            " movieId='" + getMovieId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }


}