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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class TMDBTester extends Application {

	static TMDBMainHandler tmdbHandler;
	static MovieDBHandler MovieDB;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 tmdbHandler = new TMDBMainHandler();
		//System.out.println(tmdbHandler.getApiKey());
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





		BorderPane pane = new BorderPane();
	        String imageSource = tmdbHandler.returnProfileImageUrl("/gAXGlrS9RlNA1sxJqi9C8gVsnUB.jpg");
	        System.out.println(imageSource);
		GridPane resultGP = new GridPane();
		resultGP.setGridLinesVisible(true);
		Button viewList = new Button("View List");
		ViewMovieListActionHandler handeler = new ViewMovieListActionHandler();
		viewList.setOnAction(handeler);
		resultGP.setVgap(10);
		ScrollPane sp = new ScrollPane(resultGP);
	        ImageView imageView = new ImageView(new Image(imageSource));

		Label searchLabel = new Label("Movie Title to Search: ");
		TextField searchBar = new TextField();
		Button searchBtn = new Button("Search");
/*
Top search Bar
 */
		HBox input = new HBox(5);
		input.getChildren().add(searchLabel);
		input.getChildren().add(searchBar);
		input.getChildren().add(searchBtn);
		input.getChildren().add(viewList);
		pane.setTop(input);

		/*
		Setting up Bottom Status Bar
		 */
		HBox status = new HBox(5);
		Label apiLabel = new Label("Api Key Being used");
		TextField apiKey = new TextField(tmdbHandler.tmdb.getApiKey());
		apiKey.setOnAction(event -> {

			System.out.println("hello");
		});
		Label searchStatusLabel = new Label("Search Status: ");
		Text searchStatus = new Text("Idle");
		status.getChildren().add(apiLabel);
		status.getChildren().add(apiKey);
		status.getChildren().add(searchStatusLabel);
		status.getChildren().add(searchStatus);


		pane.setBottom(status);


		pane.setCenter(sp);










		searchBtn.setOnAction(event -> {
			searchStatus.setText("Searching");
			resultGP.getChildren().clear();
			String results = tmdbHandler.searchMovies(searchBar.getText());
			System.out.println(results);
			JsonParser jp = new JsonParser();


			JsonObject je = (JsonObject) jp.parse(results);


			JsonArray result = je.getAsJsonArray("results");

			int row =0;
			for(JsonElement o:result){
				BorderPane movie = new BorderPane();
				GridPane info = new GridPane();
				info.setHgap(10);
				info.setVgap(10);

				System.out.println(o.getAsJsonObject().get("title"));
				System.out.println(o.getAsJsonObject().get("poster_path").toString());
				ImageView poster;
				if(o.getAsJsonObject().get("poster_path").isJsonNull()){
					System.out.println("hello");
					poster =new ImageView("none.jpg");
					poster.setFitWidth(342.0);
				}
				else {
					poster = new ImageView(tmdbHandler.returnPosterImageUrl(o.getAsJsonObject().get("poster_path").toString().replace("\"", "")));
					poster.setFitWidth(342.0);
				}
				info.add(new Text("Title:"),1,0);
				info.add(new Text(o.getAsJsonObject().get("title").toString().replace("\"", "")),2,1);
				info.add(new Text("Description:"),1,2);
				TextArea descrpition = new TextArea(o.getAsJsonObject().get("overview").toString().replace("\"", ""));
				descrpition.setWrapText(true);
				descrpition.setMaxWidth(500);
				descrpition.setEditable(false);
				info.add(descrpition,2,3);
				Text alertInfo = new Text();
				info.add(alertInfo,3,5);

				Button addToList = new Button("Add");
				addToList.setId(o.getAsJsonObject().get("id").toString().replace("\"", ""));
				addToList.addEventHandler(MouseEvent.MOUSE_CLICKED,event1 -> {
					int id = Integer.parseInt(((Control)event1.getSource()).getId());

					System.out.println(((Control)event1.getSource()).getId());
					String movieInfo = tmdbHandler.getMovie(id);
					JsonObject movieInfoJson = (JsonObject) jp.parse(movieInfo);
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
				movie.setCenter(info);
				movie.setRight(addToList);
				resultGP.add(movie,0,row);
				row++;




			}


		});








	         
	        Scene scene = new Scene(pane, 500, 400);

	   
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("The Mayhew Movie Database");
	        //primaryStage.getIcons().add(new Image("Icon.png"));
		//primaryStage.setMaximized(true);
	        primaryStage.show();
	    }
	@Override
	public void stop(){
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


	private class ViewMovieListActionHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println(MovieDB.getMovie(1));
			MovieDB.listMovies();

			Stage secondStage = new Stage();
			secondStage.show();

		}
	}

}
