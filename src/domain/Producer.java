package domain;

import java.util.List;

public class Producer {

    private int producer_id;
    private String producer_name;
    private String nationality;
    private String birth_date;
    private List<Movie> movies;

    public Producer(int producer_id, String producer_name, String nationality, String birth_date, List<Movie> movies) {
        this.producer_id = producer_id;
        this.producer_name = producer_name;
        this.nationality = nationality;
        this.birth_date = birth_date;
        this.movies = movies;
    }

    public Producer() {
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public String getProducer_name() {
        return producer_name;
    }

    public void setProducer_name(String producer_name) {
        this.producer_name = producer_name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
