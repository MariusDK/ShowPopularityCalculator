package ui;

import controller.MovieController;
import domain.Actor;
import domain.Genre;
import domain.Movie;
import domain.Producer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovieUI implements Initializable{
    private MovieController controller;
    @FXML private Label movie_name;
    @FXML private Label director_name;
    @FXML private Label genres;
    @FXML private Label producers;
    @FXML private Label actors;
    @FXML private Label title;
    @FXML private Label release_year;
    @FXML private Label rating;
    @FXML private Label budget;
    @FXML private Label popularity;

    @FXML private TextField movie_text;
    @FXML private TextField director_text;
    @FXML private TextField genres_text;
    @FXML private TextField producers_text;
    @FXML private TextField actors_text;
    @FXML private TextField release_text;
    @FXML private TextField budget_text;
    @FXML private TextField rating_text;
    @FXML private Button predict;



    public MovieUI() {
        this.controller = new MovieController();

    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        initData();
    }

    public void initData() {

            predict.setOnAction(event -> predictPopularity());

    }


    private void predictPopularity() {
        Movie movie = new Movie();
        String[] actors = actors_text.getText().split(",");
        List<Actor> actorList = new ArrayList<>();
        for (String actor:actors)
        {
            Actor actor1 = new Actor();
            actor1.setActor_name(actor);
            actorList.add(actor1);
        }

        movie.setActors(actorList);
        int budgetVal = Integer.parseInt(budget_text.getText());
        movie.setBudget(budgetVal);

        movie.setDirector_name(director_text.getText());

        String[] genres = genres_text.getText().split(",");
        List<Genre> genresList = new ArrayList<>();
        for (String genre:genres)
        {
            Genre genre1 = new Genre();
            genre1.setGenre_name(genre);
            genresList.add(genre1);
        }
        movie.setGenres(genresList);
        Float ratingVal = Float.parseFloat(rating_text.getText());
        movie.setRating(ratingVal);

        movie.setRelease_year(release_text.getText());

        movie.setTitle(movie_text.getText());

        String[] producers = producers_text.getText().split(",");
        List<Producer> producersList = new ArrayList<>();
        for (String producer: producers)
        {
            Producer producer1 = new Producer();
            producer1.setProducer_name(producer);
            producersList.add(producer1);
        }
        movie.setProducers(producersList);



        controller.getPredictionForMovie(movie);

        popularity.setText(controller.getPredictionForMovie(movie));
    }

}
