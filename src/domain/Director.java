package domain;

import java.util.Date;
import java.util.List;

public class Director {
    private int director_id;
    private String director_name;
    private String nationality;
    private String birth_date;
    private List<Movie> movies;

    public Director(int director_id, String director_name, String nationality, String birth, List<Movie> movies) {
        this.director_id = director_id;
        this.director_name = director_name;
        this.nationality = nationality;
        this.birth_date = birth;
        this.movies = movies;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
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
