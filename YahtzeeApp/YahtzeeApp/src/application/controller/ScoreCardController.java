package application.controller;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Hashtable;

import application.model.Player;
import application.model.ScoreCard;
import javafx.geometry.*;

/**
 * This class handles the score card pop-up during gameplay
 * 
 * @author Jeffrey Cordes (vkn217)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */

public class ScoreCardController {
	/**
	 * Displays the score card pop-up for the given player
	 * 
	 * @param p
	 */
	public static void display (Player p) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // block interaction with other windows
		window.setTitle("Score Card");
		window.setMinWidth(400);
		window.setMinHeight(580);
		
		Label nameLabel = new Label();
		nameLabel.setText(p.getPlayerName() + "'s Score Card");
		
		ScoreCard scoreCard = p.getScoreCard(); // create instance of model class to access backend
		Hashtable<String, Integer> scoreCardMap = scoreCard.getScoreCard();
		ArrayList<String> rows = new ArrayList<>();
		
		// Create array to only get the relevant items from score card
		int [] keyIdxs = {0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14}; 
		for (int i = 0; i < keyIdxs.length; i++) {
			String comboName = scoreCard.getKeys()[keyIdxs[i]];
			int comboScore = scoreCardMap.get(comboName);
			if (comboScore == -1) 
				rows.add(comboName + "\t\t");
			else {
				rows.add(comboName + "\t\t" + comboScore);
			}
		}
		
		// Create and populate the list view
		ListView <String> invList = new ListView<>();
		invList.getItems().addAll(rows);
		
		// Create the VBox
		VBox layout = new VBox(10);
		layout.getChildren().addAll(nameLabel, invList);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait(); // block interaction with caller window until this one is closed
	}
}
