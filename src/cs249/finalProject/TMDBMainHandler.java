package cs249.finalProject;

import java.io.IOException;

public class TMDBMainHandler {
	TMDBConfiguration tmdb;
	public TMDBMainHandler() {
		tmdb = new TMDBConfiguration();
		System.out.println(tmdb.getBase_URL());
	
		// TODO Auto-generated constructor stub
	}
	public String returnBackDropImageUrl(String image) {
		return tmdb.getImage_Base_URL()+tmdb.getBackdrop()+image;
	}
	public String returnLogoImageUrl(String image) {
		return tmdb.getImage_Base_URL()+tmdb.getLogo()+image;
	}
	public String returnPosterImageUrl(String image) {
		return tmdb.getImage_Base_URL()+tmdb.getPoster()+image;
	}
	public String returnProfileImageUrl(String image) {
		return tmdb.getImage_Base_URL()+tmdb.getProfile()+image;

	}public String returnStillImageUrl(String image) {
		return tmdb.getImage_Base_URL()+tmdb.getStill()+image;
	}

	
	
	
	//Movie methods
	
	public String searchMovies(String title, int year,String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title, String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title, int year) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchMovies(String title ) {

		System.out.println(title);
		String searchURL = tmdb.getBase_URL()+"search/movie?api_key="+tmdb.getApiKey()+"&language="+tmdb.getLanguage()+"&query="+title+"&page=1&include_adult=false";
		HttpConnectionHandler handle = new HttpConnectionHandler();
		String response = null;
		try {
			response = handle.getInformation(searchURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(response);
		
		
		return response;

		
	}
	public String getMovie(int id) {
		System.out.println(id);
		String searchURL = tmdb.getBase_URL()+"movie/"+id+"?api_key="+tmdb.getApiKey()+"&language="+tmdb.getLanguage();
		HttpConnectionHandler handle = new HttpConnectionHandler();
		String response = null;
		try {
			response = handle.getInformation(searchURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(response);


		return response;
		
	}
	
	//TVMethods
	
	public String searchTVShows(String title, int year,String page) {
		String Result = "null";



		return Result;
		
	}
	public String searchTVShows(String title, String page) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchTVShows(String title, int year) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	public String searchTVShows(String title ) {
		StringBuilder Result = new StringBuilder();
		
		
		
		return Result.toString();
		
	}
	public String getTV(int id) {
		String Result = "null";
		
		
		
		return Result;
		
	}
	

	
	
	

}
