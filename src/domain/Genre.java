package domain;

import java.util.List;

public class Genre {
    private int genreID;
    private String genre_name;
    private List<Movie> movies;

    public Genre(int genreID, String genre_name, List<Movie> movies) {
        this.genreID = genreID;
        this.genre_name = genre_name;
        this.movies = movies;
    }

    public Genre() {
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
