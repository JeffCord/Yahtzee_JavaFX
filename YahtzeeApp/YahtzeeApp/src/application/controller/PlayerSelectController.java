package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class PlayerSelectController implements Initializable {
	
	static final int MAX_NUM_OF_PLAYERS = 6; // just a temp amount
    
	@FXML
    private AnchorPane mainAnchorPane;
    
    @FXML
    private Button confirmBtn;
    
    @FXML
    private Button backToMainBtn;

    @FXML
    private ChoiceBox<Integer> playerNumChoiceBox;
    
    @FXML
    void backToMainBtnPressed(ActionEvent event) {
    	System.out.println("Back to Main pressed");
    	try {
	    	URL url = new File("src/Main.fxml").toURI().toURL(); // get the crossword fxml file
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
    void confirmBtnPressed(ActionEvent event) {
    	System.out.println("Confirmed number of players");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mainAnchorPane.setStyle("-fx-background-color: #FF0000");
		for (int i = 1; i <= MAX_NUM_OF_PLAYERS; i++) {
			this.playerNumChoiceBox.getItems().add(i);
		}
		
	}
	
}
