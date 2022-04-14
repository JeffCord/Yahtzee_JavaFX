package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ResourceBundle;

import application.model.Dice;
import application.model.GameplayModel;
import application.model.Match;
import application.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameplayController implements Initializable {

    @FXML
    private Button acesBttn;

    @FXML
    private Button chanceBttn;

    @FXML
    private Text comboPointsText;

    @FXML
    private Text currentPlayerName;

    @FXML
    private Text currentPlayerScore;

    @FXML
    private CheckBox dice1CheckBox;

    @FXML
    private ImageView dice1Image;

    @FXML
    private CheckBox dice2CheckBox;

    @FXML
    private CheckBox dice3CheckBox;

    @FXML
    private CheckBox dice4CheckBox;

    @FXML
    private CheckBox dice5CheckBox;

    @FXML
    private ImageView diceImage2;

    @FXML
    private ImageView diceImage3;

    @FXML
    private ImageView diceImage4;

    @FXML
    private ImageView diceImage5;

    @FXML
    private Text diceKeepText;

    @FXML
    private Rectangle dicePanel;
    
    @FXML
    private Button endTurnBttn;

    @FXML
    private Button fivesBttn;

    @FXML
    private Button fourBttn;

    @FXML
    private Button foursBttn;

    @FXML
    private Button fullBttn;

    @FXML
    private AnchorPane gamePlayPanel;

    @FXML
    private Button keepBttn;

    @FXML
    private Button largeBttn;

    @FXML
    private Button rerollBttn;

    @FXML
    private Button rollBttn;

    @FXML
    private Button saveExitBttn;

    @FXML
    private Button sixesBttn;

    @FXML
    private Button smallBttn;

    @FXML
    private Button threeBttn;

    @FXML
    private Button threesBttn;

    @FXML
    private Button twosBttn;

    @FXML
    private Button yahtzeeBttn;
    
    final int TOTAL_NUM_OF_DICE = 5;
    
    public static int numOfPlayers = 1;
	
	public static Match match;
	
	private int [] diceVals = new int[TOTAL_NUM_OF_DICE];
	
	private GameplayModel gModel;
	
//	private Image diceImage1;
//	
//	private Image diceImage2;
//	
//	private Image diceImage3;
//	
//	private Image diceImage4;
//	
//	private Image diceImage5;
//	
//	private Image diceImage6;
//	
//	private Image [] diceImages = new Image[6];
//	
//	private ImageView [] diceImageViews = new ImageView[5];


    @FXML
    void KeepBttnPressed(ActionEvent event) {

    }

    @FXML
    void RerollBttnPressed(ActionEvent event) {

    }

    @FXML
    /*
     * roll each die in the current player's dice cup
     */
    void RollBttnPressed(ActionEvent event) {
    	Player p = match.getCurrentPlayer();
    	ArrayList<Dice> diceCup = p.getDiceCup();
    	String result = "";
    	int idx = 0;
    	for (int i = 0; i < diceCup.size(); i++) {
    		// roll the current die in cup
    		Dice curDie = diceCup.get(i);
    		curDie.rollDice();
    		int curDieVal = curDie.getValue();
    		
    		// TODO update image views
    		
//    		this.diceImageViews[idx].setImage(diceImages[curDieVal - 1]);
    		idx++;
    		
    		result += diceCup.get(i).getValue() + " ";
    	}
    	
    	// TODO update image views for dice that were kept
    	ArrayList<Dice> keepers = p.getKeepers();
    	for (int i = 0; i < keepers.size(); i++) {
    		System.out.println("Keepers " + i);
    		Dice curDie = keepers.get(i);
    		int curDieVal = curDie.getValue();
    		
    		// TODO update image views

    		idx++;
    		
    		result += diceCup.get(i).getValue() + " ";
    	}
    	System.out.println("DICE: " + result);
    }

    @FXML
    void SaveExitBttnPressed(ActionEvent event) {

    }

    @FXML
    void acesBttnPressed(ActionEvent event) {

    }

    @FXML
    void chanceBttnPressed(ActionEvent event) {

    }

    @FXML
    /**
     * Defines what should happen when the current player is done rolling their dice
     * @param event
     */
    void endTurnBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	// gather what dice values the player ended up with for their turn
    	this.getCurrentPlayerDiceValues(curPlayer);
    	// enable the buttons the player can use now that they have ended their turn
    	this.checkForDiceCombos(curPlayer, diceVals);
    }
    
    @FXML
    void fivesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void fourBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void foursBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void fullBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void largeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void sixesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void smallBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void threeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void threesBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void twosBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }

    @FXML
    void yahtzeeBttnPressed(ActionEvent event) {
    	Player curPlayer = match.getCurrentPlayer();
    	Hashtable <String, Integer> scoreCard = curPlayer.getScoreCard().getScoreCard();
    }
	
	public int[] getDiceVals() {
		return diceVals;
	}

	public void setDiceVals(int[] diceVals) {
		this.diceVals = diceVals;
	}

	public int getTOTAL_NUM_OF_DICE() {
		return TOTAL_NUM_OF_DICE;
	}

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
			diceVals[idx++] = diceCup.get(i).getValue();
		}
	}
	
	public void checkForDiceCombos(Player p, int [] diceVals) {
		checkForUpperSectionCombos(p, diceVals);
		checkForLowerSectionCombos(p, diceVals);
	}
	
	// this will only check which upper section combos have already been used by the given player
	public void checkForUpperSectionCombos(Player p, int [] diceVals) {
		Hashtable <String, Integer> scoreCard = p.getScoreCard().getScoreCard();
		
		// check for aces
		if (scoreCard.get("Aces") == 0) {
			this.acesBttn.setDisable(false);
		}
		
		// check for twos
		if (scoreCard.get("Twos") == 0) {
			this.twosBttn.setDisable(false);
		}
		
		// check for threes
		if (scoreCard.get("Threes") == 0) {
			this.threesBttn.setDisable(false);
		}
		
		// check for fours
		if (scoreCard.get("Fours") == 0) {
			this.foursBttn.setDisable(false);
		}
		
		// check for fives
		if (scoreCard.get("Fives") == 0) {
			this.fivesBttn.setDisable(false);
		}
		
		// check for sixes
		if (scoreCard.get("Sixes") == 0) {
			this.sixesBttn.setDisable(false);
		}
	}
	
	public void checkForLowerSectionCombos(Player p, int [] diceVals) {
		this.checkForCombosWithMatches(diceVals);
		this.checkForChance(p);
	}
	
	// checks for three of a kind, four of a kind, full house, and Yahtzee
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
		boolean yahtzeeFound = false;
		for (int key : tally.keySet()) {
			int val = tally.get(key);
			if (val == 2) pairFound = true;
			if (val >= 3) threeOAKFound = true;
			if (val >= 4) fourOAKFound = true;
			if (val == 5) yahtzeeFound = true;
		}
		boolean fullHouseFound = pairFound && threeOAKFound;
		
		Player p = match.getCurrentPlayer();
		Hashtable <String, Integer> scoreCard = p.getScoreCard().getScoreCard();
		
		// TODO also check if combo has been used by player already (except for a yahtzee combo)
		if (threeOAKFound && scoreCard.get("3-of-a-kind") == 0) {
			this.threeBttn.setDisable(false);
		}
		if (fourOAKFound && scoreCard.get("4-of-a-kind") == 0) {
			this.fourBttn.setDisable(false);
		}
		if (fullHouseFound && scoreCard.get("FullHouse") == 0) {
			this.fullBttn.setDisable(false);
		}
		if (yahtzeeFound) {
			
		}
	}
	
	// Just check if this combo has been used since it accepts anything
	public void checkForChance(Player p) {
		if (p.getScoreCard().getScoreCard().get("chance") == 0) {
			this.chanceBttn.setDisable(false);
		}
	}
	
	public void checkForSmallStraight(int [] diceVals) {
		Player curPlayer = match.getCurrentPlayer();
		// check if player already has small straight slot filled
		if (curPlayer.getScoreCard().getScoreCard().get("SmallStraight") != 0) {
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
			}
			prevVal = curVal;
		}
		// passes small straight check
		this.smallBttn.setDisable(false);
	}
	
	public void checkForLargeStraight(int [] diceVals) {
		Player curPlayer = match.getCurrentPlayer();
		// check if player already has large straight slot filled
		if (curPlayer.getScoreCard().getScoreCard().get("LargeStraight") != 0) {
			return;
		}
		int [] diceValsSorted = Arrays.copyOf(diceVals, TOTAL_NUM_OF_DICE);
		Arrays.sort(diceValsSorted);
		int prevVal = diceVals[0];
		for (int i = 1; i < TOTAL_NUM_OF_DICE; i++) {
			int curVal = diceVals[i];
			if (prevVal != curVal - 1) {
				// large straight not possible
			}
		}
		// passes large straight check
		this.largeBttn.setDisable(false);
	}
	
//	public void finishGame() {
//		finalizeScoreCards();
//		determineWinner();
//	}
//	
//	public void finalizeScoreCards() {
//		
//	}
//	
//	// Find which player has the highest grand total
//	public void determineWinner() {
//		ArrayList <Player> players = match.getPlayers();
//		Player winner = players.get(0);
//		int highScore = winner.getScoreCard().getGrandTotal();
//		for (int i = 1; i < players.size(); i++) {
//			Player curPlayer = players.get(i);
//			int curPlayerScore = curPlayer.getScoreCard().getGrandTotal();
//			if (curPlayerScore > highScore) {
//				highScore = curPlayerScore;
//				winner = curPlayer;
//			}
//		}
//	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		// set up array for dice images
//		diceImage1 = new Image("src/dice-1.png");
//		diceImage2 = new Image("src/dice-2.png");
//		diceImage3 = new Image("src/dice-3.png");
//		diceImage4 = new Image("src/dice-4.png");
//		diceImage5 = new Image("src/dice-5.png");
//		diceImage6 = new Image("src/dice-6.png");
//		this.diceImages[0] = diceImage1;
//		this.diceImages[1] = diceImage2;
//		this.diceImages[2] = diceImage3;
//		this.diceImages[3] = diceImage4;
//		this.diceImages[4] = diceImage5;
//		this.diceImages[5] = diceImage6;
//		
//		// set up array for dice image views
//		this.diceImageViews[0] = diceImageView1;
//		this.diceImageViews[1] = diceImageView2;
//		this.diceImageViews[2] = diceImageView3;
//		this.diceImageViews[3] = diceImageView4;
//		this.diceImageViews[4] = diceImageView5;
		
		this.gamePlayPanel.setStyle("-fx-background-color: #FF0000");
		this.gModel = new GameplayModel();
		
		// set up text boxes
		this.currentPlayerName.setText(match.getCurrentPlayer().getPlayerName());
		this.currentPlayerScore.setText("Overall score: ");
		this.comboPointsText.setVisible(false);
		this.diceKeepText.setVisible(false);
		
		// disable all lower buttons except ROLL button
		this.resetButtonsForNextPlayer();
		
	}
	
	public void resetButtonsForNextPlayer() {
		this.keepBttn.setDisable(true);
		this.rerollBttn.setDisable(true);
		this.endTurnBttn.setDisable(true);
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
}
