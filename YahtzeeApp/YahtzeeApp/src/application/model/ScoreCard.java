package application.model;

import java.util.Hashtable;

public class ScoreCard {
	
	//Initialization of important variables.
	private String playerName;
	private String[] keys = new String[] {"Aces","Twos","Threes","Fours","Fives","Sixes","UpperBonus"
			,"UpperTotal","3-of-a-kind","4-of-a-kind","FullHouse","SmallStraight","LargeStraight"
			,"Yahtzee","Chance","YahtzeeBonus","LowerTotal","GrandTotal"};
	private Hashtable<String, Integer> scoreCard = new Hashtable<String,Integer>();
	private boolean isComplete = false;
	private int numOfYahtzeesScored = 0;
	
	//ScoreCard constructor. Assigns player name and initializes scoreCard HashMap.
	public ScoreCard() {
		for(String a : this.keys) {
			this.scoreCard.put(a, -1);
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
		return this.scoreCard;
	}
	
	public boolean isComplete() {
		return this.isComplete;
	}
	
	//returns total value/score of scoreCard (used to find a player's final score)
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

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public void setNumOfYahtzeesScored(int numOfYahtzeesScored) {
		this.numOfYahtzeesScored = numOfYahtzeesScored;
	}
	
	//printScoreCard function. Returns the non-overridden toString of the score_card Hashtable.
	public String printScoreCard()	{
		return this.scoreCard.toString();
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
