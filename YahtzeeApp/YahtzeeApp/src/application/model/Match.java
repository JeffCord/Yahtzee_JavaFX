package application.model;

import java.util.ArrayList;

public class Match {

	//Initialization of important variables.
	int playerCount;
	int turnCounter;
	Player currentPlayer;
	ArrayList<Player> players = new ArrayList<Player>();
	
	//Match constructor. Takes an int n, number of players, and assigns it to playerCounter.
	//Initializes turnCounter to zero.
	public Match(int n) {
		this.playerCount = n;
		this.turnCounter = 0;
	}
	
	//addPlayer function. Adds a player to the players ArrayList if they aren't already added.
	//If players ArrayList is empty, also set new player as currentPlayer.
	public void addPlayer(Player p) {
		try {
			if(this.players.size()==0) {
				this.players.add(p);
				this.setCurrentPlayer(p);
			}
			if(!this.players.contains(p)) {
				this.players.add(p);
			}
		}
		//Catch block for player already present in match Array List.
		catch(Exception e) {System.out.println("Player already added to match.");}
	}
	
	//nextTurn function. Set currentPlayer to the next in players ArrayList and increment turnCounter.
	//Check that the next player's index isn't out of bounds. If it is out of bounds, we've hit the final player.
	//If we've hit the final player we need to set the currentPlayer to our first player, at index 0.
	public void nextTurn() {
		try {
			int next = this.players.indexOf(currentPlayer)+1;
			if(!(next>this.players.size()-1)) {
				this.setCurrentPlayer(this.players.get(next));
				this.turnCounter++;
			}
			else {
				this.setCurrentPlayer(this.players.get(0));
				this.turnCounter++;
			}
		}
		catch(Exception e) {System.out.println("\nError: player index out of bounds for players ArrayList");}
	}
	
	//Getters.
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public int getPlayerCount() {
		return this.playerCount;
	}
	
	public int getTurnCount() {
		return this.turnCounter;
	}
	
	//getSize for testing purposes.
	public int getSize() {
		return players.size();
	}
	
	//Setters.
	public void setCurrentPlayer(Player c) {
		if(players.contains(c)) {
			this.currentPlayer = c;
		}
	}
		
	public void setPlayerCount(int p) {
		this.playerCount = p;
	}
	
	public void setTurnCount(int t) {
		this.turnCounter = t;
	}
	
	//toString method. Prints each player in players Array List.
	@Override
	public String toString() {
		String x = "\nMatch: \nCurrentTurn: "+this.turnCounter+"\nCurrentPlayer: "+this.getCurrentPlayer().getPlayerName()+"\nPlayers: "+this.playerCount;
		for(Player play : this.players) {
			x = x+"\t"+play.toString()+"\n";
		}
		return x;
	}
	
}
