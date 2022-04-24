package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * This class is the controller for the main menu
 * 
 * @author Jeffrey Cordes (vkn217), Lauryn Hernandez (bsa858)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */

public class MainController implements Initializable {

	@FXML
    private Button howToPlayBttn;
    
	@FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Button loadGameBtn;

    @FXML
    private Button newGameBtn;

    @FXML
    private Button quitBtn;

    @FXML
    /** 
     * event handler for load game button
     * @param event
     */
    void loadBtnPressed(ActionEvent event) {
//    	System.out.println("Load");
    }

    @FXML
    /**
     * event handler for new game button
     * @param event
     */
    void newGameBtnPressed(ActionEvent event) {
    	try {
	    	URL url = new File("src/PlayerSelect.fxml").toURI().toURL(); // get the fxml file
			mainAnchorPane = FXMLLoader.load(url); // load the new pane
	    	Scene scene = new Scene(mainAnchorPane); // set the new scene
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // set the new stage
	    	window.setScene(scene); // place new stage on new stage
	    	window.show(); // display the new stage
    	} catch (IOException e) {
    		System.out.println("ERROR: could not find PlayerSelect.fxml file");
    	}
    }

    @FXML
    /**
     * handler for how to play button
     * @param event
     */
    void howToPlayBttnPressed(MouseEvent event) {
    	try {
	    	URL url = new File("src/Rules.fxml").toURI().toURL(); // get the fxml file
			mainAnchorPane = FXMLLoader.load(url); // load the new pane
	    	Scene scene = new Scene(mainAnchorPane); // set the new scene
	    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // set the new stage
	    	window.setScene(scene); // place new stage on new stage
	    	window.show(); // display the new stage
    	} catch (IOException e) {
    		System.out.println("ERROR: could not find Rules.fxml file");
    	}
    }
    
    @FXML
    /**
     * handler for quit button
     * @param event
     */
    void quitBtnPressed(ActionEvent event) {
    	System.out.println("Quit");
    	Platform.exit();
    }

	@Override
	/**
	 * initialize method
	 * @param arg0
	 * @param arg1
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mainAnchorPane.setStyle("-fx-background-color: #FF0000");
	}

}
