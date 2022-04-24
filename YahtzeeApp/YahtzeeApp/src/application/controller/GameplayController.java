package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.ResourceBundle;

import application.model.Dice;
import application.model.GameplayModel;
import application.model.Match;
import application.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class handles the gameplay logic for yahtzee
 * 
 * @author Jeffrey Cordes (vkn217), Sabrina Ramon (mfq836)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */

public class GameplayController implements Initializable {
    @FXML
    private ImageView diceImageView5;

    @FXML
    private Button threeBttn;

    @FXML
    private ImageView diceImageView1;

    @FXML
    private Button smallBttn;

    @FXML
    private ImageView diceImageView2;

    @FXML
    private RadioButton dice1RerollRadio;

    @FXML
    private ToggleGroup dice5Action;

    @FXML
    private ImageView diceImageView3;

    @FXML
    private ImageView diceImageView4;

    @FXML
    private RadioButton dice4RerollRadio;

    @FXML
    private Text diceKeepText;

    @FXML
    private RadioButton dice4KeepRadio;

    @FXML
    private RadioButton dice1KeepRadio;

    @FXML
    private Button fourBttn;

    @FXML
    private Button largeBttn;

    @FXML
    private ToggleGroup dice2Action;

    @FXML
    private AnchorPane gamePlayPanel;

    @FXML
    private Text comboPointsText;

    @FXML
    private Text currentPlayerName;

    @FXML
    private Rectangle dicePanel;

    @FXML
    private Button acesBttn;

    @FXML
    private RadioButton dice2RerollRadio;

    @FXML
    private RadioButton dice3RerollRadio;

    @FXML
    private ToggleGroup dice4Action;

    @FXML
    private RadioButton dice3KeepRadio;

    @FXML
    private Button rollBttn;

    @FXML
    private Button checkScoreCardBttn;

    @FXML
    private Button fivesBttn;

    @FXML
    private RadioButton dice5KeepRadio;

    @FXML
    private Button goToNextBttn;

    @FXML
    private Button foursBttn;

    @FXML
    private RadioButton dice2KeepRadio;

    @FXML
    private ToggleGroup dice1Action;

    @FXML
    private Button sixesBttn;

    @FXML
    private Button yahtzeeBttn;

    @FXML
    private Button chanceBttn;

    @FXML
    private Button endRollPhaseBttn;

    @FXML
    private Button fullBttn;

    @FXML
    private RadioButton dice5RerollRadio;

    @FXML
    private ToggleGroup dice3Action;

    @FXML
    private Button saveExitBttn;

    @FXML
    private Button threesBttn;

    @FXML
    private Button twosBttn;
    
    final int TOTAL_NUM_OF_DICE = 5;
    
    final int MAX_ROLLS = 3;
    
    public static int numOfPlayers = 1;
	
	public static Match match;
	
	// the amount of rolls the current player has done (max of 3)
	private int numOfRolls = 0;
	
	private int [] rerollKeepMap = {0, 0, 0, 0, 0};
	
	// used for after a player is done with their 1-3 rolls;
	// this array is then populated with the player's final dice values for the current turn
	private int [] diceVals = new int[TOTAL_NUM_OF_DICE]; 
	
	private GameplayModel gModel;
	
	static public boolean inYahtzeeBonus = false;
	
	static public boolean gameOver = false;
	
    @FXML
    /**
     * handler for the score card button
     * @param event
     */
    void CheckScoreCardBttnPressed(ActionEvent event) {
    	ScoreCardController.display(match.getCurrentPlayer());
    }

    @FXML
    /**
     * handler for dice 1 button
     * @param event
     */
    void dice1ActionPressed(ActionEvent event) {

    }

    @FXML
    /**
     * handler for dice 2 button
     * @param event
     */
    void dice2ActionPressed(ActionEvent event) {

    }

    @FXML
    /**
     * handler for dice 3 button
     * @param event
     */
    void dice3ActionPressed(ActionEvent event) {

    }

    @FXML
    /**
     * handler for dice 4 button
     * @param event
     */
    void dice4ActionPressed(ActionEvent event) {

    }

    @FXML
    /**
     * handler for dice 5 button
     * @param event
     */
    void dice5ActionPressed(ActionEvent event) {

    }
    
    
    @FXML
    /**
     * Changes to the next player
     * @param event
     */
    void goToNextBttnPressed(ActionEvent event) {
    	this.changeToNextTurn();
    	// if game ends, switch to results menu
    	if (gameOver) {
    		gameOver = false;
			try {
		    	URL url = new File("src/Results.fxml").toURI().toURL(); // get the fxml file
				gamePlayPanel = FXMLLoader.load(url); // load the new pane
		    	Scene scene = new Scene(gamePlayPanel); // set the new scene
		    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); // set the new stage
		    	window.setScene(scene); // place new stage on new stage
		    	window.show(); // display the new stage
	    	} catch (IOException e) {
	    		System.out.println("ERROR: could not find Results.fxml file");
	    	}
    	}
    }

    @FXML
    /**
     * roll each die in the current player's dice cup
     * @param event
     */
    void RollBttnPressed(ActionEvent event) {
    	this.numOfRolls++; // increment the number of rolls the current player has done
    	Player p = match.getCurrentPlayer();
    	
    	
    	// keep the dice the user does NOT wish to reroll
    	if (this.dice1KeepRadio.isSelected()) {
    		this.rerollKeepMap[0] = 1;
    	} else {
    		this.rerollKeepMap[0] = 0;
    	}
    	if (this.dice2KeepRadio.isSelected()) {
    		this.rerollKeepMap[1] = 1;
    	} else {
    		this.rerollKeepMap[1] = 0;
    	}
    	if (this.dice3KeepRadio.isSelected()) {
    		this.rerollKeepMap[2] = 1;
    	} else {
    		this.rerollKeepMap[2] = 0;
    	}
    	if (this.dice4KeepRadio.isSelected()) {
    		this.rerollKeepMap[3] = 1;
    	} else  {
    		this.rerollKeepMap[3] = 0;
    	}
    	if (this.dice5KeepRadio.isSelected()) {
    		this.rerollKeepMap[4] = 1;
    	} else {
    		this.rerollKeepMap[4] = 0;
    	}
    	
    	this.endRollPhaseBttn.setDisable(false); // enable the end turn button after roll button is pressed
    	this.diceKeepText.setVisible(true);
    	this.showDiceImages();
    	
    	ArrayList<Dice> diceCup = p.getDiceCup(); // get the current player's dice cup (contains dice that will be rolled)
    	// roll each die in the cup
    	for (int i = 0; i < diceCup.size(); i++) {
    		// roll the current die in cup
    		if (this.rerollKeepMap[i] == 0) {
	    		Dice curDie = diceCup.get(i);
	    		curDie.rollDice();
    		}
    	}
    	
    	// update image views for dice
    	Dice [] allDice = p.getAllDice();
    	HashMap <Integer, String> diceImageFilesMap = this.gModel.getDiceMap();
    	System.out.println("DICE: " + Arrays.toString(allDice));
    	this.diceImageView1.setImage(new Image(diceImageFilesMap.get(allDice[0].getValue())));
    	this.diceImageView2.setImage(new Image(diceImageFilesMap.get(allDice[1].getValue())));
    	this.diceImageView3.setImage(new Image(diceImageFilesMap.get(allDice[2].getValue())));
    	this.diceImageView4.setImage(new Image(diceImageFilesMap.get(allDice[3].getValue())));
    	this.diceImageView5.setImage(new Image(diceImageFilesMap.get(allDice[4].getValue())));
    	
    	this.showAllRadioButtons();
    	
    	// if they run out of rolls, disable roll button and end the turn
    	if (this.numOfRolls == MAX_ROLLS) {
    		this.rollBttn.setDisable(true); 
    		this.endRollPhase();					
    	}
    }
    
    /**
     * show all radio buttons
     */
    void showAllRadioButtons() {
    	this.dice1KeepRadio.setVisible(true);
		this.dice1RerollRadio.setVisible(true);
		this.dice2KeepRadio.setVisible(true);
		this.dice2RerollRadio.setVisible(true);
		this.dice3KeepRadio.setVisible(true);
		this.dice3RerollRadio.setVisible(true);
		this.dice4KeepRadio.setVisible(true);
		this.dice4RerollRadio.setVisible(true);
		this.dice5KeepRadio.setVisible(true);
		this.dice5RerollRadio.setVisible(true);
    }
    
    /**
     * Disables the visibility of the radio buttons
     */
    void hideAllRadioButtons() {
    	this.dice1KeepRadio.setVisible(false);
		this.dice1RerollRadio.setVisible(false);
		this.dice2KeepRadio.setVisible(false);
		this.dice2RerollRadio.setVisible(false);
		this.dice3KeepRadio.setVisible(false);
		this.dice3RerollRadio.setVisible(false);
		this.dice4KeepRadio.setVisible(false);
		this.dice4RerollRadio.setVisible(false);
		this.dice5KeepRadio.setVisible(false);
		this.dice5RerollRadio.setVisible(false);
    }
    
    /**
     * Makes all the radio buttons deselected
     */
    void deselectAllRadioButtons() {
		this.dice1KeepRadio.setSelected(false);
		this.dice1RerollRadio.setSelected(false);
		this.dice2KeepRadio.setSelected(false);
		this.dice2RerollRadio.setSelected(false);
		this.dice3KeepRadio.setSelected(false);
		this.dice3RerollRadio.setSelected(false);
		this.dice4KeepRadio.setSelected(false);
		this.dice4RerollRadio.setSelected(false);
		this.dice5KeepRadio.setSelected(false);
		this.dice5RerollRadio.setSelected(false);
    }

    @FXML
    /**
     * Handler for save and exit button
     * @param event
     */
    void SaveExitBttnPressed(ActionEvent event) {

    }

    @FXML
    /**
     * Defines what should happen when the current player is done rolling their dice
     * @param event
     */
    void endRollPhaseBttnPressed(ActionEvent event) {
    	// if they decide to end their turn before their third roll, disable the roll button
    	this.endRollPhase();
    }
    
    /**
     * Defines what happens when the current player's roll phase ends,
     * either by pressing the "End Turn" button, or by rolling 3 times
     */
    public void endRollPhase() {
    	this.diceKeepText.setVisible(false); // hide dice keep text
    	this.endRollPhaseBttn.setDisable(true); // disable the end turn button
    	this.rollBttn.setDisable(true);  // disable the roll button
    	this.hideAllRadioButtons(); // hide all radio buttons
    	Player curPlayer = match.getCurrentPlayer();
    	System.out.println("End of " + curPlayer.getPlayerName() + "'s turn");
    	// gather what dice values the player ended up with for their turn
    	this.getCurrentPlayerDiceValues(curPlayer);
    	System.out.println("FINAL DICE VALS: " + Arrays.toString(diceVals));
    	// enable the buttons the player can use now that they have ended their turn
    	this.checkForAvailableComboSlots(curPlayer);
    	this.numOfRolls = 0;
    }
    
    /**
     * Turns on the buttons for the combos that the player can still use
     * @param scoreCard
     */
    private void checkForAvailableComboSlots(Player p) {
    	Hashtable<String, Integer> scoreCard = p.getScoreCard().getScoreCard();
    	// check which of the following combos on the given player's score card
    	// have already been used/scored
		if (scoreCard.get("Aces") == -1) {
			this.acesBttn.setDisable(false);
		}
		if (scoreCard.get("Twos") == -1) {
			this.twosBttn.setDisable(false);
		}
		if (scoreCard.get("Threes") == -1) {
			this.threesBttn.setDisable(false);
		}
		if (scoreCard.get("Fours") == -1) {
			this.foursBttn.setDisable(false);
		}
		if (scoreCard.get("Fives") == -1) {
			this.fivesBttn.setDisable(false);
		}
		if (scoreCard.get("Sixes") == -1) {
			this.sixesBttn.setDisable(false);
		}
		if (scoreCard.get("3-of-a-kind") == -1) {
			this.threeBttn.setDisable(false);
		}
		if (scoreCard.get("4-of-a-kind") == -1) {
			this.fourBttn.setDisable(false);
		}
		if (scoreCard.get("FullHouse") == -1) {
			this.fullBttn.setDisable(false);
		}
		if (scoreCard.get("SmallStraight") == -1) {
			this.smallBttn.setDisable(false);
		}
		if (scoreCard.get("LargeStraight") == -1) {
			this.largeBttn.setDisable(false);
		}
		if (scoreCard.get("Chance") == -1) {
			this.chanceBttn.setDisable(false);
		}
		
		// a player can ONLY score a Yahtzee or bonus Yahtzee if they haven't already
		// used the Yahtzee slot as a scratch for 0 points
		if (scoreCard.get("Yahtzee") == -1 || p.getScoreCard().getNumOfYahtzeesScored() > 0) { 
			this.yahtzeeBttn.setDisable(false);
		}
	}

	/**
	 * Should be called after a player's combo phase ends
	 * @param comboName
	 * @param comboScore
	 */
    void playerSelectedCombo(String comboName, int comboScore) {
    	this.disableComboButtons();
    	this.goToNextBttn.setDisable(false);
    	this.comboPointsText.setVisible(true);
    	if (comboScore == 1) {
    		this.comboPointsText.setText(comboName + "\n" + match.getCurrentPlayer().getPlayerName() + " scored " + comboScore + " point!");
    	} else {
    		this.comboPointsText.setText(comboName + "\n" + match.getCurrentPlayer().getPlayerName() + " scored " + comboScore + " points!");
    	}
	}
    
	@FXML
	/**
	 * Adds up all dice with the value of 1
	 * @param event
	 */
    void acesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 1) {
    			score += 1;
    		}
    	}
    	scoreCard.put("Aces", score);
    	this.playerSelectedCombo("Aces", score);
    }

	@FXML
    /**
     * Adds up all dice with the value of 5
     * @param event
     */
    void fivesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 5) {
    			score += 5;
    		}
    	}
    	scoreCard.put("Fives", score);
    	this.playerSelectedCombo("Fives", score);
    }

    @FXML
    /**
     * Adds total of all dice
     * @param event
     */
    void fourBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	
    	// check if they actually have a four of a kind    	
    	HashMap <Integer, Integer> diceMap = new HashMap<>();
    	boolean matchFound = false;
    	for (int i = 0; i < diceVals.length; i++) {
    		int curVal = diceVals[i];
    		if (!diceMap.containsKey(curVal)) {
    			diceMap.put(curVal, 1);
    		} else {
    			int newAmount = diceMap.get(curVal) + 1;
    			diceMap.put(curVal, newAmount);
    			if (newAmount == 4) {
    				matchFound = true;
    				break;
    			}
    		}
    	}
    	// if the current player does not actually have a four of a kind, then
    	// they are using this slot as a scratch (for 0 pts)
    	if (!matchFound) {
    		scoreCard.put("4-of-a-kind", 0);
    		this.playerSelectedCombo("4-of-a-kind", 0);
    		return;
    	}
    	
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		score += diceVals[i];
    	}
    	scoreCard.put("4-of-a-kind", score);
    	this.playerSelectedCombo("4-of-a-kind", score);
    }

    @FXML
    /**
     * Adds up all dice with the value of 4
     * @param event
     */
    void foursBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 4) {
    			score += 4;
    		}
    	}
    	scoreCard.put("Fours", score);
    	this.playerSelectedCombo("Fours", score);
    }

    @FXML
    /**
     * A full house earns 25 points
     * @param event
     */
    void fullBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	
    	if (inYahtzeeBonus) {
    		inYahtzeeBonus = false;
    		int score = 25;
        	scoreCard.put("FullHouse", score);
        	this.playerSelectedCombo("FullHouse", score);
        	return;
    	}
    	
    	// check if they actually have a full house or if they are using this slot
    	// for a scratch (0 pts)
    	HashMap <Integer, Integer> diceMap = new HashMap<>();
    	for (int i = 0; i < diceVals.length; i++) {
    		int curVal = diceVals[i];
    		if (!diceMap.containsKey(curVal)) {
    			diceMap.put(curVal, 1);
    		} else {
    			int newAmount = diceMap.get(curVal) + 1;
    			diceMap.put(curVal,	newAmount);
    		}
    	}
    	boolean matchFound = false;
    	if (diceMap.containsValue(2) && diceMap.containsValue(3)) {
    		matchFound = true;
    	}
    	
    	if (!matchFound) {
    		scoreCard.put("FullHouse", 0);
    		this.playerSelectedCombo("FullHouse", 0);
    		return;
    	}
    	
    	// update score
    	int score = 25;
    	scoreCard.put("FullHouse", score);
    	this.playerSelectedCombo("FullHouse", score);
    }

    @FXML
    /**
     * A large straight scores 40 points
     * @param event
     */
    void largeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	
    	if (inYahtzeeBonus) {
    		inYahtzeeBonus = false;
    		int score = 30;
        	scoreCard.put("SmallStraight", score);
        	this.playerSelectedCombo("SmallStraight", score);
        	return;
    	}
    	
    	// check if the current player actually has a large straight
    	int [] sortedDice = Arrays.copyOf(diceVals, TOTAL_NUM_OF_DICE);
    	Arrays.sort(sortedDice);
    	
    	// only two possible ways to get a large straight
    	boolean hasLargeStraight1 = sortedDice[0] == 1 && sortedDice[1] == 2 && sortedDice[2] == 3 && sortedDice[3] == 4 && sortedDice[4] == 5;
    	boolean hasLargeStraight2 = sortedDice[0] == 2 && sortedDice[1] == 3 && sortedDice[2] == 4 && sortedDice[3] == 5 && sortedDice[4] == 6;
    	
    	if (hasLargeStraight1 || hasLargeStraight2) {
	    	// update score
	    	int score = 40;
	    	scoreCard.put("LargeStraight", score);
	    	this.playerSelectedCombo("LargeStraight", score);
	    	return;
    	} 
    	// else they are using this slot as a scratch for 0 pts
    	scoreCard.put("LargeStraight", 0);
    	this.playerSelectedCombo("LargeStraight", 0);
    }

    @FXML
    /**
     * Adds up all dice with the value of 6
     * @param event
     */
    void sixesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 6) {
    			score += 6;
    		}
    	}
    	scoreCard.put("Sixes", score);
    	this.playerSelectedCombo("Sixes", score);
    }

    @FXML
    /**
     * A small straight is worth 30 points
     * @param event
     */
    void smallBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	
    	if (inYahtzeeBonus) {
    		inYahtzeeBonus = false;
    		int score = 30;
        	scoreCard.put("SmallStraight", score);
        	this.playerSelectedCombo("SmallStraight", score);
        	return;
    	}
    	
    	// check if player does indeed have a small straight
    	HashSet <Integer> diceValsSet = new HashSet<>();
    	for (int i = 0; i < diceVals.length; i++) {
    		diceValsSet.add(diceVals[i]);
    	}
    	
    	// check for all three possible small straights
    	boolean straight1 = diceValsSet.contains(1) && diceValsSet.contains(2) && diceValsSet.contains(3) && diceValsSet.contains(4);
    	boolean straight2 = diceValsSet.contains(2) && diceValsSet.contains(3) && diceValsSet.contains(4) && diceValsSet.contains(5);
    	boolean straight3 = diceValsSet.contains(3) && diceValsSet.contains(4) && diceValsSet.contains(5) && diceValsSet.contains(6);
    	
    	// if they do indeed have a small straight
    	if (straight1 || straight2 || straight3) {
    		// update score
        	int score = 30;
        	scoreCard.put("SmallStraight", score);
        	this.playerSelectedCombo("SmallStraight", score);
        	return;
    	}
    	// else, they are using this slot as a scratch for 0 pts
    	scoreCard.put("SmallStraight", 0);
    	this.playerSelectedCombo("SmallStraight", 0);
    }

    @FXML
    /**
     * Adds total of all dice
     * @param event
     */
    void threeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	
    	// check if they actually have a three of a kind    	
    	HashMap <Integer, Integer> diceMap = new HashMap<>();
    	boolean matchFound = false;
    	for (int i = 0; i < diceVals.length; i++) {
    		int curVal = diceVals[i];
    		if (!diceMap.containsKey(curVal)) {
    			diceMap.put(curVal, 1);
    		} else {
    			int newAmount = diceMap.get(curVal) + 1;
    			diceMap.put(curVal, newAmount);
    			if (newAmount == 3) {
    				matchFound = true;
    				break;
    			}
    		}
    	}
    	// if the current player does not actually have a three of a kind, then
    	// they are using this slot as a scratch (for 0 pts)
    	if (!matchFound) {
    		scoreCard.put("3-of-a-kind", 0);
    		this.playerSelectedCombo("3-of-a-kind", 0); 
    		return;
    	}
    	
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		score += diceVals[i];
    	}
    	scoreCard.put("3-of-a-kind", score);
    	this.playerSelectedCombo("3-of-a-kind", score);
    }

    @FXML
    /**
     * Adds up all dice with the value of 3
     * @param event
     */
    void threesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 3) {
    			score += 3;
    		}
    	}
    	scoreCard.put("Threes", score);
    	this.playerSelectedCombo("Threes", score);
    }

    @FXML
    /**
     * Adds up all dice with the value of 2
     * @param event
     */
    void twosBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		if (diceVals[i] == 2) {
    			score += 2;
    		}
    	}
    	scoreCard.put("Twos", score);
    	this.playerSelectedCombo("Twos", score);
    }

    @FXML
    /**
     * Handles the yahtzee possibilities
     * 1. if they get their first yahtzee
     * 2. if they have already used their yahtzee slot
     * 3. if they are using the yahtzee slot as a scratch/dump slot
     * @param event
     */
    void yahtzeeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	// check if they actually have a Yahtzee
    	boolean trueYahtzee = true;
    	int sampleDieVal = this.diceVals[0];
    	for (int i = 1; i < TOTAL_NUM_OF_DICE; i++) {
    		if (this.diceVals[i] != sampleDieVal) {
    			trueYahtzee = false;
    			break;
    		}
    	}
    	
    	// check if player has not yet used their Yahtzee slot
    	if (scoreCard.get("Yahtzee") == -1) {
    		int score;
    		if (trueYahtzee) { // if they actually have a 5-of-a-kind, give them 50 points
	    		score = 50; // further Yahtzees this player gets can be scored as bonus Yahtzees (100 pts each)
	    		incrementNumberOfYahtzeesScored(curPlayer); // increment value to show player's first Yahtzee
    		} else { // otherwise they are using the Yahtzees slot as a scratch
    			score = 0; // a zero here will prohibit the player from scoring any yahtzee bonuses
    		}
    		scoreCard.put("Yahtzee", score);
    		this.playerSelectedCombo("Yahtzee", score);
    	} else {
    		
    		
    		// if player has already used their Yahtzee slot
    		// figure out what kind of Yahtzee it is (i.e. all 1's, all 2's, etc.)
    		int score = 0;
    		if (sampleDieVal == 1) {
    			if (scoreCard.get("Aces") == -1) {
    				score = 100;
    				scoreCard.put("Aces", score);
    				this.playerSelectedCombo("Aces", score);
    			} else {
    				inYahtzeeBonus = true;
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		} else if (sampleDieVal == 2) {
    			if (scoreCard.get("Twos") == -1) {
    				score = 100;
    				scoreCard.put("Twos", score);
    				this.playerSelectedCombo("Twos", score);
    			} else {
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		} else if (sampleDieVal == 3) {
    			if (scoreCard.get("Threes") == -1) {
    				score = 100;
    				scoreCard.put("Threes", score);
    				this.playerSelectedCombo("Threes", score);
    			} else {
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		} else if (sampleDieVal == 4) {
    			if (scoreCard.get("Fours") == -1) {
    				score = 100;
    				scoreCard.put("Fours", score);
    				this.playerSelectedCombo("Fours", score);
    			} else {
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		} else if (sampleDieVal == 5) {
    			if (scoreCard.get("Fives") == -1) {
    				score = 100;
    				scoreCard.put("Fives", score);
    				this.playerSelectedCombo("Fives", score);
    			} else {
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		} else {
    			if (scoreCard.get("Sixes") == -1) {
    				score = 100;
    				scoreCard.put("Sixes", score);
    				this.playerSelectedCombo("Sixes", score);
    			} else {
    				YahtzeeBonusController.display(acesBttn, twosBttn, threesBttn, foursBttn, fivesBttn,
    						sixesBttn, threeBttn, fourBttn, smallBttn, largeBttn, fullBttn, chanceBttn);
    			}
    		}
    		this.incrementNumberOfYahtzeesScored(curPlayer);
    	}
    }
    
    /**
     * Takes in a Player to increment the number of Yahtzees they have filled in
     * on their score cards by 1
     * @param p
     */
    public void incrementNumberOfYahtzeesScored(Player p) {
    	int numOfYahtzeesScored = p.getScoreCard().getNumOfYahtzeesScored() + 1;
		p.getScoreCard().setNumOfYahtzeesScored(numOfYahtzeesScored);
    }
    
    @FXML
    /**
     * Adds the total of all 5 dice to the score
     * @param event
     */
    void chanceBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    	// update score
    	int score = 0;
    	for (int i = 0; i < TOTAL_NUM_OF_DICE; i++) {
    		score += diceVals[i];
    	}
    	scoreCard.put("Chance", score);
    	this.playerSelectedCombo("Chance", score);
    }
	
	public int[] getDiceVals() {
		return diceVals;
	}

	public void setDiceVals(int[] diceVals) {
		this.diceVals = diceVals;
	}

	/**
	 * Gets the dice values that the given player ended their turn with
	 * by populating the diceVals array at the top of this class
	 * @param p
	 */
	public void getCurrentPlayerDiceValues(Player p) {
		// get all dice values of desired player
		int idx = 0;
		
		ArrayList<Dice> diceCup = p.getDiceCup();
		ArrayList<Dice> keepers = p.getKeepers();
		// get dice values in current player's dice cup
		for (int i = 0; i < diceCup.size(); i++) {
			diceVals[idx++] = diceCup.get(i).getValue();
		}
		// get dice values in current player's keepers
		for (int i = 0; i < keepers.size(); i++) {
			diceVals[idx++] = keepers.get(i).getValue();
		}
	}
	
	/**
	 * checks for all dice combos
	 * @param p
	 * @param diceVals
	 */
	public void checkForDiceCombos(Player p, int [] diceVals) {
		checkForUpperSectionCombos(p, diceVals);
		checkForLowerSectionCombos(p, diceVals);
	}
	
	/**
	 * this will only check which upper section combos have already been used by the given player
	 * @param p
	 * @param diceVals
	 */
	public void checkForUpperSectionCombos(Player p, int [] diceVals) {
		Hashtable <String, Integer> scoreCard = p.getScoreCard().getScoreCard();
		
		// check for aces
		if (scoreCard.get("Aces") == -1) {
			this.acesBttn.setDisable(false);
		}
		
		// check for twos
		if (scoreCard.get("Twos") == -1) {
			this.twosBttn.setDisable(false);
		}
		
		// check for threes
		if (scoreCard.get("Threes") == -1) {
			this.threesBttn.setDisable(false);
		}
		
		// check for fours
		if (scoreCard.get("Fours") == -1) {
			this.foursBttn.setDisable(false);
		}
		
		// check for fives
		if (scoreCard.get("Fives") == -1) {
			this.fivesBttn.setDisable(false);
		}
		
		// check for sixes
		if (scoreCard.get("Sixes") == -1) {
			this.sixesBttn.setDisable(false);
		}
	}
	
	/**
	 * Checks only the lower section combos
	 * @param p
	 * @param diceVals
	 */
	public void checkForLowerSectionCombos(Player p, int [] diceVals) {
		this.checkForCombosWithMatches(diceVals);
		this.checkForSmallStraight(diceVals);
		this.checkForLargeStraight(diceVals);
		this.checkForChance(p);
	}
	
	/**
	 * checks for three of a kind, four of a kind, and full house
	 * @param diceVals
	 */
	public void checkForCombosWithMatches(int [] diceVals) {
		// store how many of each die value the player has
		HashMap <Integer, Integer> tally = new HashMap<>();
		for (int i : diceVals) {
			if (tally.containsKey(i)) tally.put(i, tally.get(i) + 1);
			else tally.put(i, 1);
		}
		// find out what combos that involve matching dice exist
		boolean pairFound = false;
		boolean threeOAKFound = false;
		boolean fourOAKFound = false;
		for (int key : tally.keySet()) {
			int val = tally.get(key);
			if (val == 2) pairFound = true;
			if (val >= 3) threeOAKFound = true;
			if (val >= 4) fourOAKFound = true;
		}
		boolean fullHouseFound = pairFound && threeOAKFound;
		
		Player p = match.getCurrentPlayer();
		Hashtable <String, Integer> scoreCard = p.getScoreCard().getScoreCard();
		
		// also check if combo has been used by player already (except for a yahtzee combo)
		if (threeOAKFound && scoreCard.get("3-of-a-kind") == -1) {
			this.threeBttn.setDisable(false);
		}
		if (fourOAKFound && scoreCard.get("4-of-a-kind") == -1) {
			this.fourBttn.setDisable(false);
		}
		if (fullHouseFound && scoreCard.get("FullHouse") == -1) {
			this.fullBttn.setDisable(false);
		}
	}
	
	/**
	 * Just check if this combo has been used since it accepts anything
	 * @param p
	 */
	public void checkForChance(Player p) {
		if (p.getScoreCard().getScoreCard().get("Chance") == -1) {
			this.chanceBttn.setDisable(false);
		}
	}
	
	public void checkForSmallStraight(int [] diceVals) {
		Player curPlayer = match.getCurrentPlayer();
		// check if player already has small straight slot filled
		if (curPlayer.getScoreCard().getScoreCard().get("SmallStraight") != -1) {
			return;
		}
		int [] diceValsSorted = Arrays.copyOf(diceVals, TOTAL_NUM_OF_DICE);
		Arrays.sort(diceValsSorted);
		int prevVal = diceVals[0];
		// check first four dice values
		for (int i = 1; i < TOTAL_NUM_OF_DICE - 1; i++) {
			int curVal = diceVals[i];
			if (prevVal != curVal - 1) {
				// lower small straight not possible
			}
			prevVal = curVal;
		}
		//check last four dice values
		prevVal = diceVals[1];
		for (int i = 2; i < TOTAL_NUM_OF_DICE; i++) {
			int curVal = diceVals[i];
			if (prevVal != curVal - 1) {
				// higher small straight not possible, thus no small straight is possible
				return;
			}
			prevVal = curVal;
		}
		// passes small straight check
		this.smallBttn.setDisable(false);
	}
	
	/**
	 * Checks for large straight
	 * @param diceVals
	 */
	public void checkForLargeStraight(int [] diceVals) {
		Player curPlayer = match.getCurrentPlayer();
		// check if player already has large straight slot filled
		if (curPlayer.getScoreCard().getScoreCard().get("LargeStraight") != -1) {
			return;
		}
		int [] diceValsSorted = Arrays.copyOf(diceVals, TOTAL_NUM_OF_DICE);
		Arrays.sort(diceValsSorted);
		int prevVal = diceVals[0];
		for (int i = 1; i < TOTAL_NUM_OF_DICE; i++) {
			int curVal = diceVals[i];
			if (prevVal != curVal - 1) {
				// large straight not possible
				return;
			}
		}
		// passes large straight check
		this.largeBttn.setDisable(false);
	}
	
	/**
	 * Handles changing turns
	 */
	public void changeToNextTurn() {
		//System.out.println(match.getCurrentPlayer().getScoreCard().toString());
		
		// change the player to the next one in the roster
		match.nextTurn();
		
		//set up text boxes
		this.currentPlayerName.setText(match.getCurrentPlayer().getPlayerName());
		this.comboPointsText.setVisible(false);
		this.diceKeepText.setVisible(false);
		
		// hide dice images
		this.hideDiceImages();
		
		// set up buttons
		this.resetButtonsForNextPlayer();
	}
	
	/**
	 * resets buttons for next player
	 */
	public void resetButtonsForNextPlayer() {
		// remove toggles for each die
		this.hideAllRadioButtons();
		// reset toggles for each die
		this.setAllRadioButtonsToReroll();
		
		// enable roll button
		this.rollBttn.setDisable(false);
		 
		// disable combo and go to next player buttons
		this.disableComboButtons();	
		this.goToNextBttn.setDisable(true);
	}

	/**
	 * disables combo buttons
	 */
	void disableComboButtons() {
		this.endRollPhaseBttn.setDisable(true);
		this.chanceBttn.setDisable(true);
		this.threeBttn.setDisable(true);
		this.fourBttn.setDisable(true);
		this.fullBttn.setDisable(true);
		this.smallBttn.setDisable(true);
		this.yahtzeeBttn.setDisable(true);
		this.largeBttn.setDisable(true);
		this.acesBttn.setDisable(true);
		this.twosBttn.setDisable(true);
		this.threesBttn.setDisable(true);
		this.foursBttn.setDisable(true);
		this.fivesBttn.setDisable(true);
		this.sixesBttn.setDisable(true);
	}
	
	/**
	 * hides dice images
	 */
	void hideDiceImages() {
		this.diceImageView1.setVisible(false);
		this.diceImageView2.setVisible(false);
		this.diceImageView3.setVisible(false);
		this.diceImageView4.setVisible(false);
		this.diceImageView5.setVisible(false);
	}
	
	/**
	 * shows dice images
	 */
	void showDiceImages() {
		this.diceImageView1.setVisible(true);
		this.diceImageView2.setVisible(true);
		this.diceImageView3.setVisible(true);
		this.diceImageView4.setVisible(true);
		this.diceImageView5.setVisible(true);
	}
	
	/**
	 * sets all radio buttons to reroll
	 */
	void setAllRadioButtonsToReroll() {
		this.dice1KeepRadio.setSelected(false);
		this.dice2KeepRadio.setSelected(false);
		this.dice3KeepRadio.setSelected(false);
		this.dice4KeepRadio.setSelected(false);
		this.dice5KeepRadio.setSelected(false);
		
		this.dice1RerollRadio.setSelected(true);
		this.dice2RerollRadio.setSelected(true);
		this.dice3RerollRadio.setSelected(true);
		this.dice4RerollRadio.setSelected(true);
		this.dice5RerollRadio.setSelected(true);
	}
	
	@Override
	/**
	 * the initialize method
	 * @param arg0
	 * @param arg1
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gModel = new GameplayModel();
		
		this.hideDiceImages();
		
		// set up text boxes
		this.currentPlayerName.setText(match.getCurrentPlayer().getPlayerName());
		this.comboPointsText.setVisible(false);
		this.diceKeepText.setVisible(false);
		
		// disable all lower buttons except ROLL button
		this.resetButtonsForNextPlayer();
	}
}
