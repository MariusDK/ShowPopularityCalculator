package controller;

import domain.*;
import provider.MovieProvider;

import java.util.*;

public class MovieController {

    private MovieProvider provider;
    //aici se face scheletul algoritmului
    //nr_tree = reprezinta numarul copacilor
    //aici formam cele nr_tree submultimi pe care apoi le trimitem pe rand construiri arborelui

    private List<String> RandomTree_Algoritm(Movie movie)
    {
        List<Movie> movies = provider.getMovies(); ///test
        int nr_tree=100;
        int first = 0;
        int subset_size = movies.size()/nr_tree;
        int last = subset_size;
        for (int i=0;i<nr_tree;i++) {

            List<String> attributes = getRandomAtributes();
            if (i == nr_tree - 1) {
                List<Movie> subSetMovies = getSubSet(first, movies.size(), movies);
            }
            else {
                List<Movie> subSetMovies = getSubSet(first, last, movies);
                first = last;
                last = last + subset_size;
            }
        }
        return null;
    }
    //aici primim lista de atribute random
    private List<String> getRandomAtributes()
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
