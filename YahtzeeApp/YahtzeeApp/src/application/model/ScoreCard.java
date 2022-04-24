package application.model;

import java.util.Hashtable;

/**
 * This class handles the score card object for yahtzee
 * 
 * @author Nicholas Ray (gmm408)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class ScoreCard {
	
	//Initialization of important variables.
	private String playerName;
	private String[] keys = new String[] {"Aces","Twos","Threes","Fours","Fives","Sixes","UpperBonus"
			,"UpperTotal","3-of-a-kind","4-of-a-kind","FullHouse","SmallStraight","LargeStraight"
			,"Yahtzee","Chance","YahtzeeBonus","LowerTotal","GrandTotal"};
	private Hashtable<String, Integer> scoreCard = new Hashtable<String,Integer>();
	private boolean isComplete = false;
	private int numOfYahtzeesScored = 0;
	
	/**
	 * ScoreCard constructor. Assigns player name and initializes scoreCard HashMap.
	 */
	public ScoreCard() {
		for(String a : this.keys) {
			this.scoreCard.put(a, -1);
		}
	}
	
	//Getters.
	/**
	 * gets the keys 
	 * @return
	 */
	public String[] getKeys() {
		return this.keys;
	}
	
	/**
	 * gets the player name
	 * @return
	 */
	public String getPlayerName() {
		return this.playerName;
	}
	
	/**
	 * gets the number of yahtzees scored by the player
	 * @return
	 */
	public int getNumOfYahtzeesScored() {
		return numOfYahtzeesScored;
	}
	
	/**
	 * gets the score card in hashtable form
	 * @return
	 */
	public Hashtable<String, Integer> getScoreCard() {
		return this.scoreCard;
	}
	
	/**
	 * returns true if the score card is full
	 * @return
	 */
	public boolean isComplete() {
		return this.isComplete;
	}
	
	/**
	 * returns total value/score of scoreCard (used to find a player's final score)
	 * @return
	 */
	public int getTotalScore() {
		int upperTotal = scoreCard.get("Aces") + scoreCard.get("Twos") + scoreCard.get("Threes") + 
				scoreCard.get("Fours") + scoreCard.get("Fives") + scoreCard.get("Sixes");
		
		if (upperTotal > 63) {
			int upperBonus = 35;
			upperTotal += upperBonus;
		}
		scoreCard.put("UpperTotal", upperTotal);
		
		int lowerTotal = scoreCard.get("3-of-a-kind") + scoreCard.get("4-of-a-kind") + 
				scoreCard.get("FullHouse") + scoreCard.get("SmallStraight") + 
				scoreCard.get("LargeStraight") + scoreCard.get("Chance") + scoreCard.get("Yahtzee");
		scoreCard.put("LowerTotal", lowerTotal);
		
		int total = upperTotal + lowerTotal;
		scoreCard.put("GrandTotal", total);
		return total;
	}
	
	//Setters.
	/**
	 * sets the player name
	 * @param n
	 */
	public void setPlayerName(String n) {
		this.playerName = n;
	}
	
	/**
	 * sets the score of a category
	 * @param k
	 * @param v
	 */
	public void setScore(String k, int v) {
		this.scoreCard.put(k, v);
	}
	
	/**
	 * sets the keys
	 * @param keys
	 */
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	/**
	 * sets the score card
	 * @param scoreCard
	 */
	public void setScoreCard(Hashtable<String, Integer> scoreCard) {
		this.scoreCard = scoreCard;
	}

	/**
	 * sets the completion boolean
	 * @param isComplete
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * sets the number of yahtzees scored
	 * @param numOfYahtzeesScored
	 */
	public void setNumOfYahtzeesScored(int numOfYahtzeesScored) {
		this.numOfYahtzeesScored = numOfYahtzeesScored;
	}
	
	/**
	 * printScoreCard function. Returns the non-overridden toString of the score_card Hashtable.
	 * @return
	 */
	public String printScoreCard()	{
		return this.scoreCard.toString();
	}

	
	@Override
	/**
	 * toString method custom override.
	 * Iterate through key String array and print out values within scoreCard Hashtable.
	 * @return
	 */
	public String toString() {
		String x="\n"+this.playerName+"'s ScoreCard:";
		for(String a : this.keys) {
			x = x+"\n\t"+a+":\t"+scoreCard.get(a);
		}
		return x;
	}
}
