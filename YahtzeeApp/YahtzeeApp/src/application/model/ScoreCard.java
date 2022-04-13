package application.model;

import java.util.Hashtable;

public class ScoreCard {
	
	//Initialization of important variables.
	String playerName;
	String[] keys = new String[] {"Aces","Twos","Threes","Fours","Fives","Sixes","UpperBonus"
			,"UpperTotal","3-of-a-kind","4-of-a-kind","FullHouse","SmallStraight","LargeStraight"
			,"YAHTZEE","Chance","YahtzeeBonus","LowerTotal","GrandTotal"};
	Hashtable<String, Integer> scoreCard = new Hashtable<String,Integer>();
	boolean isComplete = false;
	int numOfYahtzeesScored = 0;
	
	//ScoreCard constructor. Assigns player name and initializes scoreCard HashMap.
	public ScoreCard() {
		for(String a : this.keys) {
			this.scoreCard.put(a, 0);
		}
	}
	
	//Getters.
	public String[] getKeys() {
		return this.keys;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getNumOfYahtzeesScored() {
		return numOfYahtzeesScored;
	}
	
	public Hashtable<String, Integer> getScoreCard() {
		return scoreCard;
	}
	
	public boolean isComplete() {
		return isComplete;
	}
	
	//returns total value/score of scoreCard (used to find a player's final score)
	public int getTotalScore() {
		int total = 0;
		
		for(String k : this.keys) {
			total += scoreCard.get(k);
		}
		
		return total;
	}
	
	//Setters.
	public void setPlayerName(String n) {
		this.playerName = n;
	}
	
	public void setScore(String k, int v) {
		this.scoreCard.put(k, v);
	}
	
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
	public void setScoreCard(Hashtable<String, Integer> scoreCard) {
		this.scoreCard = scoreCard;
	}

	
	//printScoreCard function. Returns the non-overridden toString of the score_card Hashtable.
	public String printScoreCard()	{
		return this.scoreCard.toString();
	}
	
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public void setNumOfYahtzeesScored(int numOfYahtzeesScored) {
		this.numOfYahtzeesScored = numOfYahtzeesScored;
	}

	//toString method custom override.
	//Iterate through key String array and print out values within scoreCard Hashtable.
	@Override
	public String toString() {
		String x="\n"+this.playerName+"'s ScoreCard:";
		for(String a : this.keys) {
			x = x+"\n\t"+a+":\t"+scoreCard.get(a);
		}
		return x;
	}
}
