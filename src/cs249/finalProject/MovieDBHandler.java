package cs249.finalProject;



import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieDBHandler implements Serializable {
    private ArrayList<Movie> Movies;

    public MovieDBHandler() {
        System.out.println("Created");

    }

  /*        Movies = new ArrayList<Movie>();


   */
    public void listMovies() {
        Iterator<Movie> iterator = Movies.iterator();
        while (iterator.hasNext()) {
            Movie mov = iterator.next();
            System.out.println(mov.getTitle());
        }
    }

    public void addMovie(Movie movie) {
    }
    public ArrayList<Movie> getMovies(){
        return Movies;
    }

    public void addMovie(JsonObject movie) {
        System.out.println(movie.get("title"));
        Movie movieOBJ = new Movie();
        movieOBJ.setId(Integer.parseInt(movie.get("id").toString().replace("\"", "")));
        movieOBJ.setTitle(movie.get("title").toString().replace("\"", ""));
        movieOBJ.setDescription(movie.get("overview").toString().replace("\"", ""));
        Movies.add(movieOBJ);


    }

    public void removeMovie(Integer id) {
    }

    public Movie getMovie(Integer id) {
        Movie mov = new Movie();
        System.out.println(id);
        return (mov);
    }




}
