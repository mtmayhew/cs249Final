package cs249.finalProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

public class CommandlineInterface {
    static TMDBMainHandler tmdbHandler;
    public static void main(String[] args) throws IOException {
        tmdbHandler = new TMDBMainHandler();
        JsonParser jp = new JsonParser();
        HttpConnectionHandler handle = new HttpConnectionHandler();
        String response = handle.getInformation("\n" +
                "https://api.themoviedb.org/3/search/movie?api_key=cb7ca60452b19601157cc20664a7fe63&language=en-US&query=hobbit&page=1&include_adult=false");
        JsonElement je = jp.parse(response);
        System.out.println(response);
        JsonArray result = je.getAsJsonObject().getAsJsonArray("results");
        for(JsonElement o:result){
            System.out.println(o.getAsJsonObject().get("title"));

        }

    }
}
