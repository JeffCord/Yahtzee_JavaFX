package application.model;

import java.util.ArrayList;

public class Player {
	
	//Initialization of important variables.
	String playerName;
	ArrayList<Dice> diceCup = new ArrayList<Dice>();
	ArrayList<Dice> keepers = new ArrayList<Dice>();
	ScoreCard card = new ScoreCard();

	//Player constructor. Takes a string as the player's name and creates 5 dice for their diceCup.
	public Player(String name) {
		this.playerName=name;
		this.card.setPlayerName(name);
		for(int i=0;i<5;i++) {
			Dice newDice = new Dice();
			this.diceCup.add(newDice);
		}
		
	}
	
	//keepDice function. Takes an int index which is used to search the player's diceCup ArrayList.
	//If dice b, at passed index d, is contained within diceCup remove and add dice to keepers ArrayList.
	public void keepDice(int d) {
		try {
			Dice b = this.getDice(d);
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
	
	//rollCup function. For each dice in diceCup, call the rollDice method for a new value.
	public void rollCup() {
		for(Dice dice : this.diceCup) {
			dice.rollDice();
		}
	}
	
	//getDice function. Returns the dice object at passed index a within diceCup ArrayList.
	public Dice getDice(int a) {
		return this.diceCup.get(a);
	}
	
	//getScoreCard function. Returns card object.
	public ScoreCard getScoreCard()	{
		return this.card;
	}
	
	//getPlayerName function. Returns this player's player name.
	public String getPlayerName() {
		return this.playerName;
	}
	
	//toString method. Prints the player's name, diceCup contents, and keepers contents.
	@Override
	public String toString() {
		return "\nPlayer Name: "+this.playerName+"\n\tDiceCup: "+this.diceCup.toString()+"\n\tKeepers: "+this.keepers.toString();
	}
	
}
