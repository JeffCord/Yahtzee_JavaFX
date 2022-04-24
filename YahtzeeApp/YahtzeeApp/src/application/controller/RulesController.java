package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class handles the How To Play menu
 * 
 * @author Lauryn Hernandez (bsa858)
 * 
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class RulesController {

    @FXML
    private Button homeBttn;
    
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    /**
     * handler for home button
     * @param event
     */
    void homeBttnPressed(MouseEvent event) {
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
}
