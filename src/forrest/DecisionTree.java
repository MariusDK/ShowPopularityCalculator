package forrest;

import controller.MovieController;
import domain.Actor;
import domain.Genre;
import domain.Movie;
import domain.Producer;

import java.util.*;

public class DecisionTree {

    private Nod radacina;

    public DecisionTree(Nod radacina) {
        this.radacina = radacina;
    }

    public DecisionTree() { }

    private String createTree(List<Movie> movies,Movie movie,String[] atributes)
    {
        double targetEntropy = general_entropy(movies);
        String bestAtribute = best_infoGain_atribute(movies,movie,atributes);
        radacina.setValue(bestAtribute);

        return null;

    }
    //Entropia generala, adica verificam in datele noastra cate date sunt high,medium sau low si calculam entropia
    //Facuta pentru caz general (cum se calculează)
    public double general_entropy(List<Movie> movies)
    {
        int high = 0;
        int medium = 0;
        int low = 0;
        double entropy = 0;
        for (Movie movie:movies)
        {
            if (movie.getPopularity().equals("High"))
            {
                high++;
            }
            if (movie.getPopularity().equals("Medium"))
            {
                medium++;
            }
            if (movie.getPopularity().equals("Low"))
            {
                low++;
            }
        }
        // calculam probabilitatile

        double p_high = ((double)high)/(high+medium+low);
        double p_medium = ((double)medium)/(high+medium+low);
        double p_low = ((double)low)/(high+medium+low);
        entropy = -p_high*log2(p_high)-p_medium*log2(p_medium)-p_low*log2(p_low);// -suma(probilitate*log(probabilitate))
        return entropy;
    }
    //entropia pentru categoria gen
    public double specific_genre_entropy(List<Movie> movies,List<Genre> genres)
    {
        List<Movie> newMovieList_yes = new ArrayList<>();
        List<Movie> newMovieList_no = new ArrayList<>();
        for (Movie movie:movies)
        {
            boolean ok = true;
            for (Genre genre:genres)
            {
                if (!(movie.getGenres().contains(genre)))
                {
                    ok = false;
                }
            }
            if (ok == true)
            {
                newMovieList_yes.add(movie);
            }
            else
            {
                newMovieList_no.add(movie);
            }
        }
        return calculate_atribute_entropy(newMovieList_no,movies.size());
    }
    //entropia pentru categoria actor
    public double specific_actor_entropy(List<Movie> movies,List<Actor> actors)
    {
        List<Movie> newMovieList_yes = new ArrayList<>();
        List<Movie> newMovieList_no = new ArrayList<>();
        for (Movie movie:movies)
        {
            boolean ok =false;
            for (Actor actor:actors)
            {
                if ((movie.getActors().contains(actor)))
                {
                    newMovieList_yes.add(movie);
                    ok = true;
                    break;
                }
            }
            if (ok == false) {
                newMovieList_no.add(movie);
            }
        }
        return calculate_atribute_entropy(newMovieList_no,movies.size());
    }
    //entropia pentru categoria producator
    public double specific_producer_entropy(List<Movie> movies,List<Producer> producers)
    {
        List<Movie> newMovieList_yes = new ArrayList<>();
        List<Movie> newMovieList_no = new ArrayList<>();
        for (Movie movie:movies)
        {
            boolean ok = false;
            for (Producer producer:producers)
            {
                if ((movie.getProducers().contains(producer)))
                {
                    ok = true;
                    break;
                }
            }
            if (ok == true)
            {
                newMovieList_yes.add(movie);
            }
            else
            {
                newMovieList_no.add(movie);
            }
        }
        return calculate_atribute_entropy(newMovieList_no,movies.size());
    }
    //entropia pentru categoria director
    public double specific_director_entropy(List<Movie> movies,String director_name)
    {
        List<Movie> newMovieList_yes = new ArrayList<>();
        List<Movie> newMovieList_no = new ArrayList<>();
        for (Movie movie:movies)
        {
            boolean ok = true;
            if (!(movie.getDirector_name().equals(director_name)))
            {
                newMovieList_no.add(movie);
            }
            else
            {
                newMovieList_yes.add(movie);
            }
        }
        return calculate_atribute_entropy(newMovieList_no,movies.size());
    }

    //entropia pentru categoria buget
    public double specific_budget_entropy(List<Movie> movies,int budget)
    {
        List<Movie> newMovieList_yes = new ArrayList<>();
        List<Movie> newMovieList_no = new ArrayList<>();
        int maxBuget=0,minBuget=0;
        if ((budget>100000)&&(budget<=500000))
        {
            minBuget = 100000;
            maxBuget = 500000;///categoria LOW BUDGET
        }
        if ((budget>50000)&&(budget<=750000))
        {
            minBuget = 500000;
            maxBuget = 750000;///categoria MEDIUM
        }
        if ((budget>750000)&&(budget<=1000000))
        {
            minBuget = 750000;
            maxBuget = 1000000;///categoria HIGH
        }
        for (Movie movie:movies)
        {
            if ((minBuget<movie.getBudget())&&(maxBuget>=movie.getBudget()))
            {
                newMovieList_yes.add(movie);
            }
            else
            {
                newMovieList_no.add(movie);
            }
        }
        return calculate_atribute_entropy(newMovieList_no,movies.size());
    }
    //calculare logaritm
    public double log2(double n)
    {
        return (Math.log(n)/Math.log(2));
    }
    //calcul entropie folosit in entropiile specifice ale atributelor
    public double calculate_atribute_entropy(List<Movie> movies_not,int movies_size)
    {
        double entropy = ((double)movies_not.size()/movies_size)*general_entropy(movies_not);
        return entropy;
    }
    //calculul celei mai bune atribute (folosind entropiile)
    public String best_infoGain_atribute(List<Movie> movies,Movie movie,String[] atributes)
    {
        TreeMap<Double,String> list= new TreeMap<>(Collections.reverseOrder());
        for (int i=0;i<atributes.length;i++)
        {
            if (atributes[i].equals("actors"))
            {
                double info_gain = general_entropy(movies)-specific_actor_entropy(movies,movie.getActors());
                list.put(info_gain,atributes[i]);
            }
            if (atributes[i].equals("budget"))
            {
                double info_gain = general_entropy(movies)-specific_budget_entropy(movies,movie.getBudget());
                list.put(info_gain,atributes[i]);
            }
            if (atributes[i].equals("director"))
            {
                double info_gain = general_entropy(movies)-specific_director_entropy(movies,movie.getDirector_name());
                list.put(info_gain,atributes[i]);
            }
            if (atributes[i].equals("genres"))
            {
                double info_gain = general_entropy(movies)-specific_genre_entropy(movies,movie.getGenres());
                list.put(info_gain,atributes[i]);
            }
            if (atributes[i].equals("producers"))
            {
                double info_gain = general_entropy(movies)-specific_producer_entropy(movies,movie.getProducers());
                list.put(info_gain,atributes[i]);
            }

        }
        String Value = null;
        Map.Entry<Double, String> entry = list.firstEntry();
        return entry.getValue();
    }

}
