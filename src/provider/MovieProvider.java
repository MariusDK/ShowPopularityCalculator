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
//            String querry = "SELECT DISTINCT m.movie_id,\n" +
//                    "       d.director_name,\n" +
//                    "       m.title,\n" +
//                    "       g.genre_name,\n" +
//                    "       m.budget,\n" +
//                    "       m.popularity,\n" +
//                    "       m.rating,\n" +
//                    "       m.release_year,\n" +
//                    "       p.producer_name\n" +
//                    "       a.actor_name\n" +
//                    "FROM movies m\n" +
//                    "INNER JOIN directors d\n" +
//                    "ON m.director_id = d.director_id\n" +
//                    "INNER JOIN movies_actors ma\n" +
//                    "ON ma.movie_id = m.movie_id\n" +
//                    "INNER JOIN actors a\n" +
//                    "ON a.actor_id = ma.actor_id\n" +
//                    "INNER JOIN movies_genres mg\n" +
//                    "ON mg.movie_id = m.movie_id\n" +
//                    "INNER JOIN genres g\n" +
//                    "ON mg.genre_id = g.genre_id\n" +
//                    "INNER JOIN movies_producers mp\n" +
//                    "ON mp.movie_id = m.movie_id\n" +
//                    "INNER JOIN producers p\n" +
//                    "ON mp.producer_id = p.producer_id";
            String querry = "SELECT m.movie_id,d.director_name,m.title,m.release_year,m.rating,m.budget,m.popularity FROM movies m INNER JOIN  directors d ON m.director_id = d.director_id;";
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
                List<Producer> producers = getProducersForMovie(movie_id);

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
            String querry = "SELECT  a.actor_id, a.actor_name, a.nationality, a.birth_date " +
                            "FROM actors a INNER JOIN movies_actors ma on a.actor_id = ma.actor_id " +
                            "WHERE ma.movie_id ='"+movie_id+"'";
            ResultSet resultSet = DatabaseConnection.getStatement().executeQuery(querry);
            while(resultSet.next())
            {
                int actor_id = resultSet.getInt("actor_id");
                String actor_name = resultSet.getString("actor_name");
                String nationality = resultSet.getString("nationality");
                String birth = resultSet.getString("birth_date");
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
            String querry = "SELECT  g.genre_id, g.genre_name FROM genres g INNER JOIN movies_genres mg on g.genre_id = mg.genre_id WHERE mg.movie_id ='"+movie_id+"'";
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
    public List<Producer> getProducersForMovie(int movie_id)
    {

        List<Producer> producers = new ArrayList<>();
        try{
            String querry = "SELECT  p.producer_id, p.producer_name, p.nationality, p.birth_date FROM producers p INNER JOIN movies_producers mp on p.producer_id = mp.producer_id WHERE mp.movie_id ='"+movie_id+"'";
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
