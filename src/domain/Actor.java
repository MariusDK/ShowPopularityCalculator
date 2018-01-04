package domain;

import java.util.Date;
import java.util.List;

public class Actor {
    private int actor_id;
    private String actor_name;
    private String nationality;
    private String birth_date;
    private List<Movie> movies;

    public Actor(int actorID, String actor_name, String nationality, String birth, List<Movie> movies) {
        this.actor_id = actorID;
        this.actor_name = actor_name;
        this.nationality = nationality;
        this.birth_date = birth;
        this.movies = movies;
    }

    public Actor() {
    }

    public int getActorID() {
        return actor_id;
    }

    public void setActorID(int actorID) {
        this.actor_id = actorID;
    }

    public String getActor_name() {
        return actor_name;
    }

    public void setActor_name(String actor_name) {
        this.actor_name = actor_name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirth() {
        return birth_date;
    }

    public void setBirth(String birth) {
        this.birth_date = birth;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
