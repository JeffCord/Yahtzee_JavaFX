package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * This application mimics the classic tabletop game, Yahtzee
 * 
 * @author Nicholas Ray (gmm408), Charlie Alvarado (dna084), Sabrina Ramon (mfq836), 
 * Lauryn Hernandez (bsa858), Jeffrey Cordes (vkn217)
 *
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/Main.fxml").toURI().toURL();
			AnchorPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Yahtzee");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
