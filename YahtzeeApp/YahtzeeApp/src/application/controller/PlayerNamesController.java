package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.Player;
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
    
    @FXML
    private Label warningLabel;

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
    	String desiredName = this.nameTextField.getText().trim();
    	// check if name input is empty
    	if (desiredName.length() == 0) return;
    	// check if name is already taken
    	Player newPlayer = new Player(desiredName);
    	if (!GameplayController.match.addPlayer(newPlayer)) {
    		this.nameTextField.clear();
    		this.warningLabel.setVisible(true);
    		this.warningLabel.setText("The name " + desiredName + " is already taken. Please try another name.");
    		return;
    	}
    	System.out.println("Successfully made player with name " + desiredName);
    	
    	for (int i = 0; i < GameplayController.match.getPlayers().size(); i++) {
    		System.out.print(GameplayController.match.getPlayers().get(i).getPlayerName() + " ");
    	}
    	System.out.println();
    	
    	// add player
    	this.nameTextField.clear();
    	this.warningLabel.setVisible(false);
    	// System.out.println("DATA: " + this.curPlayerNumber + " " + GameplayController.numOfPlayers);
    	if (this.curPlayerNumber == GameplayController.numOfPlayers) {
    		this.confirmBtn.setVisible(false);
    		this.nameTextField.setVisible(false);
    		this.promptLabel.setVisible(false);
    		this.startGameBtn.setVisible(true);
    	} else {
    		// increment player number if not done adding player names
        	this.incremementPlayerNumber();
    	}
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
		setPromptLabelText();
		this.startGameBtn.setVisible(false);
		this.warningLabel.setVisible(false);
	}
	
}
