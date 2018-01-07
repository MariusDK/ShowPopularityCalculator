package provider;

import database.DatabaseConnection;
import domain.Actor;
import domain.Genre;
import domain.Movie;
import domain.Producer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieProvider {

    public MovieProvider() {
    }

    public List<Movie> getMovies()
    {
        List<Movie> movies = new ArrayList<>();
        try {
            String querry = "SELECT movie_id,director_name,title,release_year,rating,budget,popularity FROM movies INNER JOIN  directors on movies.director_id = directors.director_id ";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(querry);
            while (resultSet.next())
            {
                int movie_id = resultSet.getInt("movie_id");
                String director_name = resultSet.getString("director_name");
                String title = resultSet.getString("title");
                float rating = resultSet.getFloat("rating");
                int budget = resultSet.getInt("budget");
                String popularity = resultSet.getString("popularity");
                String release_year = resultSet.getString("release_year");
                List<Actor> actors = getActorsForMovie(movie_id);
                List<Genre> genres = getGenresForMovie(movie_id);
                List<Producer> producers = getProducersForMivie(movie_id);

                Movie m =new Movie(movie_id,director_name,genres,producers,actors,title,release_year,rating,budget,popularity);
                movies.add(m);
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movies;
    }
    public List<Actor> getActorsForMovie(int movie_id)
    {

        List<Actor> actors = new ArrayList<>();
        try{
            String querry = "SELECT  actor_id, actor_name, nationality, birth_date " +
                            "FROM actors INNER JOIN movies_actors on actors.actor_id = movies_actors.actor_id " +
                            "WHERE movies_actors.movie_id ='"+movie_id+"'";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(querry);
            while(resultSet.next())
            {
                int actor_id = resultSet.getInt("actor_id");
                String actor_name = resultSet.getString("actor_name");
                String nationality = resultSet.getString("nationality");
                String birth = resultSet.getString("birth");
                Actor actor =new Actor();
                actor.setActorID(actor_id);
                actor.setActor_name(actor_name);
                actor.setNationality(nationality);
                actor.setBirth(birth);
                actors.add(actor);
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return actors;
    }
    public List<Genre> getGenresForMovie(int movie_id)
    {
        List<Genre> genres = new ArrayList<>();
        try{
            String querry = "SELECT  genre_id, genre_name FROM genres INNER JOIN movies_genres on genres.genre_id = movies_genres.genre_id WHERE movies_genres.movie_id ='"+movie_id+"'";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(querry);
            while(resultSet.next())
            {
                int genre_id = resultSet.getInt("genre_id");
                String genre_name = resultSet.getString("genre_name");
                Genre genre = new Genre();
                genre.setGenreID(genre_id);
                genre.setGenre_name(genre_name);
                genres.add(genre);
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return genres;
    }
    public List<Producer> getProducersForMivie(int movie_id)
    {

        List<Producer> producers = new ArrayList<>();
        try{
            String querry = "SELECT  producer_id, producer_name, nationality, birth_date FROM producers INNER JOIN movies_producers on producers.producer_id = movies_producers.producers_id WHERE movies_producers.movie_id ='"+movie_id+"'";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(querry);
            while(resultSet.next())
            {
                int producer_id = resultSet.getInt("producer_id");
                String producer_name = resultSet.getString("producer_name");
                String nationality = resultSet.getString("nationality");
                String birth_date = resultSet.getString("birth_date");
                Producer producer = new Producer();
                producer.setProducer_id(producer_id);
                producer.setProducer_name(producer_name);
                producer.setNationality(nationality);
                producer.setBirth_date(birth_date);

                producers.add(producer);
            }
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return producers;


    }
}
