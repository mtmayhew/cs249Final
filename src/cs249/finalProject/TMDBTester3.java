package cs249.finalProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TMDBTester3 extends Application {

	String searchTitle;
	static TMDBMainHandler tmdbHandler;
	Button searchMovie;
	Button searchTV;
	Scene movieBrowse;
	Scene TVBrowse;
	Scene searchBar;




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tmdbHandler = new TMDBMainHandler();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//scenes
		BorderPane border = new BorderPane();
		Scene search1 = new Scene(border, 500, 400);


		BorderPane border2 = new BorderPane();
		Scene library = new Scene(border2, 500, 400);

		BorderPane border3 = new BorderPane();
		searchBar  = new Scene(border3, 500, 400);




		// make search starting screen
		primaryStage.setScene(search1);
		primaryStage.setTitle("Videography database");




		// bottom box with a button to go to the library
		HBox hbox1 = new HBox();
		hbox1.setPadding(new Insets(15, 12, 15, 200));
		hbox1.setSpacing(100);   // Gap between nodes
		hbox1.setStyle("-fx-background-color: #5F9EA0;");

		Button buttonL = new Button("To Library");
		buttonL.setOnAction(e -> primaryStage.setScene(library));
		buttonL.setPrefSize(100, 15);

		hbox1.getChildren().add(buttonL);




		// top box for search
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(15, 12, 15, 100));
		hbox2.setStyle("-fx-background-color: #5F9EA0;");

		Text database = new Text("Look up items to add to your list");
		database.setFont(Font.font("Ariel" , FontWeight.BOLD, 20));

		hbox2.getChildren().add(database);




		// search picker for movies
		VBox vbox1 =  new VBox();
		vbox1.setPadding(new Insets(50));
		vbox1.setSpacing(8);

		Text search = new Text("Search for Movies by:");
		search.setFont(Font.font("Ariel" , FontWeight.SEMI_BOLD, 14));

		ChoiceBox<String> choiceBoxM = new ChoiceBox<>();
		choiceBoxM.getItems().add("ID");
		choiceBoxM.getItems().add("Title");
		choiceBoxM.getItems().add("Title & Year");
		choiceBoxM.getItems().add("Title & Page");
		choiceBoxM.getItems().add("Title,Page & Year");

		searchMovie = new Button("Search Movies");
		searchMovie.setOnAction(e -> getChoiceM(choiceBoxM, primaryStage));

		vbox1.getChildren().addAll(search, choiceBoxM, searchMovie);




		//search picker for TV
		VBox vbox2 =  new VBox();
		vbox2.setPadding(new Insets(50));
		vbox2.setSpacing(8);

		Text search2 = new Text("Search for TV Shows by:");
		search2.setFont(Font.font("Ariel" , FontWeight.SEMI_BOLD, 14));

		ChoiceBox<String> choiceBoxT = new ChoiceBox<>();
		choiceBoxT.getItems().add("ID");
		choiceBoxT.getItems().add("Title");
		choiceBoxT.getItems().add("Title & Year");
		choiceBoxT.getItems().add("Title & Page");
		choiceBoxT.getItems().add("Title,Page & Year");


		searchTV = new Button("Search TV Shows");
		searchTV.setOnAction(e -> getChoiceT(choiceBoxT,primaryStage));

		vbox2.getChildren().addAll(search2, choiceBoxT,searchTV);





		// bottom bar for search scene
		HBox hbox5 = new HBox();
		hbox5.setPadding(new Insets(15, 12, 15, 110));
		hbox5.setSpacing(100);   // Gap between nodes
		hbox5.setStyle("-fx-background-color: #5F9EA0;");

		Button buttonS1 = new Button("To Search");
		buttonS1.setOnAction(e -> primaryStage.setScene(search1));
		buttonS1.setPrefSize(100, 15);

		Button buttonL1 = new Button("To Library");
		buttonL1.setOnAction(e -> primaryStage.setScene(library));
		buttonL1.setPrefSize(100, 15);

		hbox5.getChildren().addAll(buttonS1, buttonL1);



		// top box for search
		HBox hbox6 = new HBox();
		hbox6.setPadding(new Insets(15, 12, 15, 130));
		hbox6.setStyle("-fx-background-color: #5F9EA0;");

		Text searchtxt = new Text(searchTitle);
		searchtxt.setFont(Font.font("Ariel" , FontWeight.BOLD, 30));

		hbox6.getChildren().add(searchtxt);




		// bottom bar for library scene
		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(15, 12, 15, 220));
		hbox3.setSpacing(100);   // Gap between nodes
		hbox3.setStyle("-fx-background-color: #5F9EA0;");

		Button buttonS = new Button("To Search");
		buttonS.setOnAction(e -> primaryStage.setScene(search1));
		buttonS.setPrefSize(100, 15);

		hbox3.getChildren().add(buttonS);



		// top box for search
		HBox hbox4 = new HBox();
		hbox4.setPadding(new Insets(15, 12, 15, 180));
		hbox4.setStyle("-fx-background-color: #5F9EA0;");

		Text libTxt = new Text("My Library");
		libTxt.setFont(Font.font("Ariel" , FontWeight.BOLD, 30));

		hbox4.getChildren().add(libTxt);

		//center search bar for search
		Label searchb = new Label("testing");
		TextField bar = new TextField();

		HBox hbox7 = new HBox();
		hbox7.getChildren().addAll(searchb, bar);
		hbox7.setSpacing(10);


		//add all things to their proper border for search scene
		border.setTop(hbox2);
		border.setBottom(hbox1);
		border.setLeft(vbox1);
		border.setRight(vbox2);

		//library setup
		border2.setTop(hbox4);
		border2.setBottom(hbox3);

		//search bar setup movie id
		border3.setTop(hbox6);
		border3.setBottom(hbox5);
		border3.setCenter(hbox7);


		primaryStage.show();
	}


	//to get the value of the choicebox for movies and go to the next scene
	public void getChoiceM(ChoiceBox<String> choiceBoxM,Stage primaryStage )
	{
		String typeOfSearch = choiceBoxM.getValue();

		if (typeOfSearch == "ID")
		{
			searchTitle = "Search for Movies by ID";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title")
		{
			searchTitle = "Search for Movies by Title";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title & Year")
		{
			searchTitle = "Search for Movies by Title and Year";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title & Page")
		{
			searchTitle = "Search for Movies by Title and Page";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title,Page & Year")
		{
			searchTitle = "Search for Movies by Title,Page and Year";
			primaryStage.setScene(searchBar);
		}
		else
			{
				System.out.print("Please pick a search method.\n");
			}

	}


	//to get the value of the choicebox for TV and go to the next scene
	public void getChoiceT(ChoiceBox<String> choiceBoxT,Stage primaryStage )
	{
		String typeOfSearch = choiceBoxT.getValue();

		if (typeOfSearch == "ID")
		{
			searchTitle = "Search for TV Shows by ID";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title")
		{
			searchTitle = "Search for TV Shows by Title";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title & Year")
		{
			searchTitle = "Search for TV Shows by Title and Year";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title & Page")
		{
			searchTitle = "Search for TV Shows by Title and Page";
			primaryStage.setScene(searchBar);
		}
		else if (typeOfSearch == "Title,Page & Year")
		{
			searchTitle = "Search for TV Shows by Title, Page and Year";
			primaryStage.setScene(searchBar);
		}
		else
		{
			System.out.print("Please pick a search method.\n");
		}

	}
}
