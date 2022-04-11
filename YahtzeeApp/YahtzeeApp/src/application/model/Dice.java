package application.model;

import java.util.Random;

//Dice class that represents any dice we will roll in our Yahtzee matches.
public class Dice {
	
	//Initialization of important variables.
	Random rn = new Random();
	int value;
	
	//Dice constructor. Initialize value to 1.
	public Dice() {
		this.value = 1;
	}
	
	//rollDice function. Generates a random number between 1-6 and assigns it to value.
	public void rollDice() {
		this.value = rn.nextInt(6)+1;
	}
	
	//Getters.
	public int getValue() {
		return this.value;
	}
	
	//Setters.
	public void setValue(int x) {
		this.value = x;
	}
	
	//toString method. Prints the value of the dice.
	 @Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
