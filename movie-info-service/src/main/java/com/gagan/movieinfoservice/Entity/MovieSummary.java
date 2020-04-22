package com.gagan.movieinfoservice.Entity;

public class MovieSummary {

    private String title;
    private String overview;
    private double popularity;


    public MovieSummary() {
    }


    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", overview='" + getOverview() + "'" +
            ", popularity='" + getPopularity() + "'" +
            "}";
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public MovieSummary(String title, String overview, double popularity) {
        this.title = title;
        this.overview = overview;
        this.popularity = popularity;
    }


}