package cs249.finalProject;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TMDBTester2 extends Application {

	static TMDBMainHandler tmdbHandler;
	static MovieDBHandler MovieDB;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 tmdbHandler = new TMDBMainHandler();
		try {
			FileInputStream filein = new FileInputStream("file.dat");
			ObjectInputStream objectInputStream = new ObjectInputStream(filein);
			Object holder = objectInputStream.readObject();
			if(holder.getClass() == MovieDBHandler.class){
				MovieDB = (MovieDBHandler) holder;

			}
			else{
				MovieDB = new MovieDBHandler();
			}
			objectInputStream.close();
			filein.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("File not found so creating the object");
			MovieDB = new MovieDBHandler();
		}

		launch(args);

	}
	@Override
	   public void start(Stage primaryStage) {
		//main pane
		BorderPane pane = new BorderPane();

		//result pane
		GridPane resultGP = new GridPane();
		resultGP.setVgap(10);
		//holds the results
		ScrollPane sp = new ScrollPane(resultGP);


		// creating the inputs
		Button viewList = new Button("View List");
		ViewMovieListActionHandler handeler = new ViewMovieListActionHandler();
		viewList.setOnAction(handeler);

		Label searchLabel = new Label("Movie Title to Search: ");
		TextField searchBar = new TextField();
		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(event -> {
			resultGP.getChildren().clear();
			String results = tmdbHandler.searchMovies(searchBar.getText());
			System.out.println(results);
			JsonParser jp = new JsonParser();


			JsonObject je = (JsonObject) jp.parse(results);


			JsonArray result = je.getAsJsonArray("results");

			int row =0;
			for(JsonElement o:result){
				//holder for the movie
				BorderPane movie = new BorderPane();
				//information holder
				GridPane movieInfo = new GridPane();

				movieInfo.setHgap(10);
				movieInfo.setVgap(10);

				ImageView poster;

				if(o.getAsJsonObject().get("poster_path").isJsonNull()){
					poster = new ImageView("none.jpg");
					poster.setFitWidth(342.0);
				}else{
					poster = new ImageView(tmdbHandler.returnPosterImageUrl(o.getAsJsonObject().get("poster_path").toString().replace("\"", "")));
					poster.setFitWidth(342.0);
				}
				movieInfo.add(new Text("Title:"),1,0);
				movieInfo.add(new Text(o.getAsJsonObject().get("title").toString().replace("\"", "")),2,1);
				movieInfo.add(new Text("Description:"),1,2);
				TextArea descrpition = new TextArea(o.getAsJsonObject().get("overview").toString().replace("\"", ""));
				descrpition.setWrapText(true);
				descrpition.setMaxWidth(500);
				descrpition.setEditable(false);
				movieInfo.add(descrpition,2,3);
				Text alertInfo = new Text();
				movieInfo.add(alertInfo,3,5);

				Button addToList = new Button("Add");
				addToList.setId(o.getAsJsonObject().get("id").toString().replace("\"", ""));
				addToList.setOnAction( event1 -> {
					int id = Integer.parseInt(((Control)event1.getSource()).getId());

					System.out.println(((Control)event1.getSource()).getId());
					String MovieInfo = tmdbHandler.getMovie(id);
					System.out.println(MovieInfo);
					JsonObject movieInfoJson = (JsonObject) jp.parse(MovieInfo);
					alertInfo.setText("adding");


					MovieDB.addMovie(movieInfoJson);
					alertInfo.setText("added");
					Thread ts = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(6000);
								Platform.runLater(() -> {
									alertInfo.setText("");
								});
								System.out.println("hidding");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
					ts.start();



				});

				movie.setLeft(poster);
				movie.setCenter(movieInfo);
				movie.setRight(addToList);
				resultGP.add(movie,0,row);


				row++;




			}

		});

		//creating the search
		HBox input = new HBox(5);
		input.getChildren().add(searchLabel);
		input.getChildren().add(searchBar);
		input.getChildren().add(searchBtn);
		input.getChildren().add(viewList);

		pane.setTop(input);
		pane.setCenter(sp);










		Scene scene = new Scene(pane, 500, 400);

	   
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("The Mayhew Movie Database");
	        //primaryStage.getIcons().add(new Image("Icon.png"));
	        primaryStage.setMaximized(true);
		    primaryStage.show();
	    }

	@Override
	public void stop() throws Exception {
		super.stop();

		System.out.println("Stage is closing");
		// Save file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("file.dat");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(MovieDB);
			objectOut.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private class ViewMovieListActionHandler  implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			MovieDB.listMovies();
			ArrayList<Movie> movies = MovieDB.getMovies();
			int row = 0;
			GridPane resultGP = new GridPane();
			resultGP.setVgap(10);
			//holds the results
			ScrollPane sp = new ScrollPane(resultGP);

			Iterator<Movie> iterator = movies.iterator();
			while (iterator.hasNext()) {
				Movie mov = iterator.next();
				BorderPane movie = new BorderPane();
				//information holder
				GridPane movieInfo = new GridPane();

				movieInfo.setHgap(10);
				movieInfo.setVgap(10);

				ImageView poster;
				poster = new ImageView("none.jpg");
				poster.setFitWidth(342.0);
				movieInfo.add(new Text("Title:"),1,0);
				movieInfo.add(new Text(mov.getTitle()),2,1);
				movieInfo.add(new Text("Description:"),1,2);
				TextArea descrpition = new TextArea(mov.getDescription());
				descrpition.setWrapText(true);
				descrpition.setMaxWidth(500);
				descrpition.setEditable(false);
				movieInfo.add(descrpition,2,3);

				movie.setLeft(poster);
				movie.setCenter(movieInfo);
				resultGP.add(movie,0,row);


				row++;





				System.out.println(mov.getTitle());
			}





			Scene scene = new Scene(sp, 500, 400);
			Stage secondStage = new Stage();
			secondStage.setScene(scene);
			secondStage.show();
		}
	}
}
