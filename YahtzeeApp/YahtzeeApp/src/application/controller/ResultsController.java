package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class is the controller for the main menu
 * 
 * @author Jeffrey Cordes (vkn217), Sabrina Ramon (mfq836)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class ResultsController implements Initializable {
	@FXML
    private AnchorPane mainAnchorPane;
	
	@FXML
    private Button backToMainBtn;
    
    @FXML
    private Label congratsLabel;
	
    @FXML
    /**
     * handler for the main menu button
     * @param event
     */
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
    
    
	@Override
	/**
	 * initialize method
	 * @param arg0
	 * @param arg1
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainAnchorPane.setStyle("-fx-background-color: #FF0000");
		congratsLabel.setText("Congratulations, " + Match.getWinner().getPlayerName() + "!");
	}

}
