package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;


public class MainController implements Initializable {

	@FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Button LoadGameBtn;

    @FXML
    private Button NewGameBtn;

    @FXML
    private Button QuitBtn;

    @FXML
    void loadBtnPressed(ActionEvent event) {
    	System.out.println("Load");
    }

    @FXML
    void newGameBtnPressed(ActionEvent event) {
    	System.out.println("New");
    }

    @FXML
    void quitBtnPressed(ActionEvent event) {
    	System.out.println("Quit");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.MainAnchorPane.setStyle("-fx-background-color: #FF0000");
		
	}

}
