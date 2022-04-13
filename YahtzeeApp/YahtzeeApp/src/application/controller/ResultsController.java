package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResultsController {
	@FXML
    private AnchorPane mainAnchorPane;
	
	@FXML
    private Button backToMainBtn;

    @FXML
    private Button playAgainBtn;
	
    @FXML
    void backToMainBtnPressed(ActionEvent event) {
    	try {
	    	URL url = new File("src/Main.fxml").toURI().toURL(); // get the fxml file
			mainAnchorPane = FXMLLoader.load(url); // load the new pane
	    	Scene scene = new Scene(mainAnchorPane); // set the new scene
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // set the new stage
	    	window.setScene(scene); // place new stage on new stage
	    	window.show(); // display the new stage
    	} catch (IOException e) {
    		System.out.println("ERROR: could not find Main.fxml file");
    	}
    }

    @FXML
    void playAgainBtnPressed(ActionEvent event) {
    	try {
	    	URL url = new File("src/Gameplay.fxml").toURI().toURL(); // get the fxml file
			mainAnchorPane = FXMLLoader.load(url); // load the new pane
	    	Scene scene = new Scene(mainAnchorPane); // set the new scene
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // set the new stage
	    	window.setScene(scene); // place new stage on new stage
	    	window.show(); // display the new stage
    	} catch (IOException e) {
    		System.out.println("ERROR: could not find Gameplay.fxml file");
    	}
    }

}
