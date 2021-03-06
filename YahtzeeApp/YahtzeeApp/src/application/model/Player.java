package application.model;

import java.util.ArrayList;

/**
 * This class handles the player object for a game of Yahtzee
 * 
 * @author Nicholas Ray (gmm408)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class Player {
	
	//Initialization of important variables.
	private String playerName;
	private ArrayList<Dice> diceCup = new ArrayList<Dice>();
	private ArrayList<Dice> keepers = new ArrayList<Dice>();
	private ScoreCard scoreCard = new ScoreCard();

	public static final int TOTAL_NUM_OF_DICE = 5;

	//Player constructor. Takes a string as the player's name and creates 5 dice for their diceCup.
	public Player(String name) {
		this.playerName=name;
		this.scoreCard.setPlayerName(name);
		for(int i=0;i<5;i++) {
			Dice newDice = new Dice();
			this.diceCup.add(newDice);
		}
		
	}
	
	public Dice[] getAllDice() {
		Dice [] allDice = new Dice[TOTAL_NUM_OF_DICE];
		int idx = 0;
		for (int i = 0; i < this.diceCup.size(); i++) {
			allDice[idx] = this.diceCup.get(i);
			idx++;
		}
		for (int i = 0; i < this.keepers.size(); i++) {
			allDice[idx] = this.keepers.get(i);
			idx++;
		}
		return allDice;
	}
	
	//keepDice function. Takes an int index which is used to search the player's diceCup ArrayList.
	//If dice b, at passed index d, is contained within diceCup remove and add dice to keepers ArrayList.
	public void keepDice(int d) {
		try {
			Dice b = this.getDie(d);
			if(this.diceCup.contains(b)) {
				this.diceCup.remove(b);
				this.keepers.add(b);
			}
		}
		//Catch block for index out of bounds exceptions.
		catch(IndexOutOfBoundsException e){
			System.out.println("\nError: diceCup has no dice at given index.");
			}
	}
	
	// TODO add a function that can deselect "kept" dice before player rolls
	
	//rollCup function. For each dice in diceCup, call the rollDice method for a new value.
	public void rollCup() {
		for(Dice dice : this.diceCup) {
			dice.rollDice();
		}
	}
	
	//getDice function. Returns the dice object at passed index a within diceCup ArrayList.
	public Dice getDie(int a) {
		return this.diceCup.get(a);
	}
	
	
	public ArrayList<Dice> getDiceCup() {
		return diceCup;
	}

	public void setDiceCup(ArrayList<Dice> diceCup) {
		this.diceCup = diceCup;
	}

	public ArrayList<Dice> getKeepers() {
		return keepers;
	}

	public void setKeepers(ArrayList<Dice> keepers) {
		this.keepers = keepers;
	}

	//getScoreCard function. Returns card object.
	public ScoreCard getScoreCard()	{
		return this.scoreCard;
	}
	
	public void setScoreCard(ScoreCard scoreCard) {
		this.scoreCard = scoreCard;
	}
	
	//getPlayerName function. Returns this player's player name.
	public String getPlayerName() {
		return this.playerName;
	}
	
	//getPlayerFinalScore function. Returns the player's final score.
	public int getPlayerFinalScore() {
		int totalScore = scoreCard.getTotalScore();
		return totalScore;
	}
	
	public void setPlayerName(String newPlayerName) {
		this.playerName = newPlayerName;
	}
	
	
	//toString method. Prints the player's name, diceCup contents, and keepers contents.
	@Override
	public String toString() {
		return "\nPlayer Name: "+this.playerName+"\n\tDiceCup: "+this.diceCup.toString()+"\n\tKeepers: "+this.keepers.toString();
	}
}
