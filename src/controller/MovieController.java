package controller;

import domain.*;
import forrest.DecisionTree;
import provider.MovieProvider;

import java.util.*;

public class MovieController {

    private MovieProvider provider;

    public MovieController() { }

    //aici se face scheletul algoritmului
    //nr_tree = reprezinta numarul copacilor
    //aici formam cele nr_tree submultimi pe care apoi le trimitem pe rand construiri arborelui

    public String getPredictionForMovie(Movie movie) {
        List<String> predictionList = RandomTree_Algoritm(movie);
        int low = 0;
        int high = 0;
        int medium = 0;
        Map<Integer,String> predictionMap = new HashMap<>();
        for(int i = 0 ; i < predictionList.size(); i++) {
            if (predictionList.get(i) == "Low") low++;
            if (predictionList.get(i) == "High") high++;
            if (predictionList.get(i) == "Medium") medium++;
        }
        predictionMap.put(low,"Low");
        predictionMap.put(high,"High");
        predictionMap.put(medium,"Medium");
        int max = Math.max(Math.max(low,high),medium);
        return predictionMap.get(max);
    }

    public List<String> RandomTree_Algoritm(Movie movie)
    {
        List<Movie> movies = provider.getMovies(); ///test
        List<String> predictionList = new ArrayList<>();
        int nr_tree=100;
        int first = 0;
        int subset_size = movies.size()/nr_tree;
        int last = subset_size;
        for (int i=0;i<nr_tree;i++) {

            List<String> attributes = getRandomAtributes();
            if (i == nr_tree - 1) {
                List<Movie> subSetMovies = getSubSet(first, movies.size(), movies);
                DecisionTree tree = new DecisionTree();
                String prediction = tree.createTreeAndPredict(subSetMovies, movie, attributes);
                predictionList.add(prediction);
            }
            else {
                List<Movie> subSetMovies = getSubSet(first, last, movies);
                DecisionTree tree = new DecisionTree();
                String prediction = tree.createTreeAndPredict(subSetMovies, movie, attributes);
                predictionList.add(prediction);
                first = last;
                last = last + subset_size;
            }
        }
        return predictionList;
    }

    //aici primim lista de atribute random
    public List<String> getRandomAtributes()
    {
        String[] attributes = {"actors","budget","director","genres","producers"};
        Random rand = new Random();
        int n = rand.nextInt(attributes.length-1)+1;
        int k=1;
        List<String> attributesChosen = new ArrayList<>();

        while (k<=n)
        {
            String randomAttribute = attributes[rand.nextInt(attributes.length)];

            if (!attributesChosen.contains(randomAttribute))
            {
                k++;
                attributesChosen.add(randomAttribute);
            }
        }
        return attributesChosen;
    }

    //prin aceasta functie primim pe rand submultimile
    public List<Movie> getSubSet(int first,int last,List<Movie> movies)
    {
        List<Movie> subSet_movies = new ArrayList<>();
        for (int i = first;i<last;i++)
        {
            subSet_movies.add(movies.get(i));
        }
        return subSet_movies;
    }

}
