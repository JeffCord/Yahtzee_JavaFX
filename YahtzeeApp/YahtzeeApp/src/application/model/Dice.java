package application.model;

import java.util.Random;

/**
 * Dice class that represents any dice we will roll in our Yahtzee matches.
 * 
 * @author Nicholas Ray (gmm408)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class Dice {
	
	//Initialization of important variables.
	Random rn = new Random();
	int value;
	
	/**
	 * Dice constructor. Initialize value to 1.
	 */
	public Dice() {
		this.value = 1;
	}
	
	/**
	 * rollDice function. Generates a random number between 1-6 and assigns it to value.
	 */
	public void rollDice() {
		this.value = rn.nextInt(6)+1;
	}
	
	//Getters.
	/**
	 * gets the die's value
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
	
	//Setters.
	/**
	 * sets the die's value
	 * @param x
	 */
	public void setValue(int x) {
		this.value = x;
	}
	
	 @Override
	 /**
	  * toString method. Prints the value of the dice.
	  * @return
	  */
	public String toString() {
		return String.valueOf(this.value);
	}
}
