package application.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class handles the yahtzee bonus pop-up
 * 
 * @author Jeffrey Cordes (vkn217)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */

public class YahtzeeBonusController {
	/**
	 * displays the yahtzee bonus pop-up menu
	 * 
	 * @param acesBttn
	 * @param twosBttn
	 * @param threesBttn
	 * @param foursBttn
	 * @param fivesBttn
	 * @param sixesBttn
	 * @param threeBttn
	 * @param fourBttn
	 * @param smallBttn
	 * @param largeBttn
	 * @param fullBttn
	 * @param chanceBttn
	 */
	public static void display(Button acesBttn, Button twosBttn, Button threesBttn, Button foursBttn, Button fivesBttn,
			Button sixesBttn, Button threeBttn, Button fourBttn, Button smallBttn, Button largeBttn, Button fullBttn, Button chanceBttn) {
		Stage popup = new Stage();
		VBox options = new VBox(15);
		Scene scene = new Scene(options);
		popup.initModality(Modality.APPLICATION_MODAL);
		options.setAlignment(Pos.CENTER);
		popup.setTitle("Yahtzee Bonus");
		popup.setMinWidth(400);
		popup.setMinHeight(600);
		options.setPadding(new Insets(20, 0, 20, 0));
		options.getChildren().addAll(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
				sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
		popup.setScene(scene);
	}
}
