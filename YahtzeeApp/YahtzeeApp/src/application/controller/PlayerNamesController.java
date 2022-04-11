package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    void backBtnPressed(MouseEvent event) {
    	
    }

    @FXML
    void confirmNameBtnPressed(MouseEvent event) {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.mainAnchorPane.setStyle("-fx-background-color: #FF0000");
	}
	
}
