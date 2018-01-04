package domain;

import com.oracle.jrockit.jfr.Producer;

import java.util.Date;
import java.util.List;

public class Movie {
    private int movie_id;
    private String director_name;
    private List<Genre> genres;
    private List<domain.Producer> producers;
    private List<Actor> actors;
    private String title;
    private String release_year;
    private float rating;
    private int budget;
    private String popularity;

    public Movie(int movie_id, String name_director, List<Genre> genres, List<domain.Producer> producers, List<Actor> actors, String title, String release_year, float rating, int budget, String popularity) {
        this.movie_id = movie_id;
        this.director_name = name_director;
        this.genres = genres;
        this.producers = producers;
        this.actors = actors;
        this.title = title;
        this.release_year = release_year;
        this.rating = rating;
        this.budget = budget;
        this.popularity = popularity;
    }

    public Movie() {
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String name_director) {
        this.director_name = name_director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<domain.Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<domain.Producer> producers) {
        this.producers = producers;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}
