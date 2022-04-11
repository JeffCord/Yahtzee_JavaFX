package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayerNamesController implements Initializable {
	
	@FXML
    private AnchorPane mainAnchorPane;
	
    @FXML
    private Button backBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label promptLabel;

    private int curPlayerNumber = 1;
    
    @FXML
    private Button startGameBtn;

    @FXML
    void startGameBtnPressed(MouseEvent event) {
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
    
    @FXML
    void backBtnPressed(MouseEvent event) {
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
    void confirmNameBtnPressed(MouseEvent event) {
    	// if name is valid
    	
    	this.incremementPlayerNumber();
    }
    
    public void incremementPlayerNumber() {
    	this.curPlayerNumber++;
    	this.setPromptLabelText();
    }

    public void setPromptLabelText() {
    	this.promptLabel.setText("Player " + this.curPlayerNumber + ", please enter your name");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mainAnchorPane.setStyle("-fx-background-color: #FF0000");
		setPromptLabelText();
		this.startGameBtn.setVisible(false);
	}
	
}