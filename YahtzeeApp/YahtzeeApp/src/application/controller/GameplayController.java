package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.model.Dice;
import application.model.Match;
import application.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private ImageView dice2Image;

    @FXML
    private CheckBox dice3CheckBox;

    @FXML
    private ImageView dice3Image;

    @FXML
    private CheckBox dice4CheckBox;

    @FXML
    private CheckBox dice5CheckBox;

    @FXML
    private ImageView diceImage4;

    @FXML
    private ImageView diceImage5;

    @FXML
    private Text diceKeepText;

    @FXML
    private Rectangle dicePanel;

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


    @FXML
    void KeepBttnPressed(ActionEvent event) {

    }

    @FXML
    void RerollBttnPressed(ActionEvent event) {

    }

    @FXML
    void RollBttnPressed(ActionEvent event) {

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
    void fivesBttnPressed(ActionEvent event) {

    }

    @FXML
    void fourBttnPressed(ActionEvent event) {

    }

    @FXML
    void foursBttnPressed(ActionEvent event) {

    }

    @FXML
    void fullBttnPressed(ActionEvent event) {

    }

    @FXML
    void largeBttnPressed(ActionEvent event) {

    }

    @FXML
    void sixesBttnPressed(ActionEvent event) {

    }

    @FXML
    void smallBttnPressed(ActionEvent event) {

    }

    @FXML
    void threeBttnPressed(ActionEvent event) {

    }

    @FXML
    void threesBttnPressed(ActionEvent event) {

    }

    @FXML
    void twosBttnPressed(ActionEvent event) {

    }

    @FXML
    void yahtzeeBttnPressed(ActionEvent event) {

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

	public void getDiceValues(Player p) {
		// get all dice values of desired player
		int idx = 0;
		
		ArrayList<Dice> diceCup = p.getDiceCup();
		ArrayList<Dice> keepers = p.getKeepers();
		// get dice values in dice cup
		for (int i = 0; i < diceCup.size(); i++) {
			diceVals[idx++] = diceCup.get(i).getValue();
		}
		// get 
		for (int i = 0; i < keepers.size(); i++) {
			diceVals[idx++] = diceCup.get(i).getValue();
		}
		// print dice values for debugging
		for (int i = 0; i < diceVals.length; i++) {
			System.out.print(diceVals[i]);
		}
		System.out.println();
		
		checkForDiceCombos(p, diceVals);
	}
	
	public void checkForDiceCombos(Player p, int [] diceVals) {
		checkForUpperSectionCombos(p, diceVals);
		checkForLowerSectionCombos(p, diceVals);
	}
	
	// this will only check which upper section combos have already been used by the given player
	public void checkForUpperSectionCombos(Player p, int [] diceVals) {
		// check for aces
		
		// check for twos
		
		// check for threes
		
		// check for fours
		
		// check for fives
		
		// check for sixes
		
	}
	
	public void checkForLowerSectionCombos(Player p, int [] diceVals) {
		checkForCombosWithMatches(diceVals);
		checkForChance(p);
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
		
		// TODO also check if combo has been used by player already (except for a yahtzee combo)
		if (threeOAKFound) {
			
		}
		if (fourOAKFound) {
			
		}
		if (fullHouseFound) {
			
		}
		if (yahtzeeFound) {
			
		}
	}
	
	// Just check if this combo has been used since it accepts anything
	public void checkForChance(Player p) {
		
	}
	
	public void checkForSmallStraight(int [] diceVals) {
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
	}
	
	public void checkForLargeStraight(int [] diceVals) {
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
		// TODO Auto-generated method stub
	}
}
