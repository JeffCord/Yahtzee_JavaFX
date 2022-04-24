package application.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.controller.GameplayController;


/**
 * This class handles the match object for a game of Yahtzee
 * 
 * @author Nicholas Ray (gmm408)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class Match {

	//Initialization of important variables.
	int playerCount;
	int turnNumber; // indicates whose turn it is. [0, playerCount - 1]
	int turnCounter;
	int roundNumber;
	Player currentPlayer;
	ArrayList<Player> players = new ArrayList<Player>();
	
	static Player winner = null;
	
	/**
	 * Match constructor. Takes an int n, number of players, and assigns it to playerCounter.
	 * Initializes turnCounter to zero.
	 * @param n
	 */
	public Match(int n) {
		this.playerCount = n;
		this.turnNumber = 0;
		this.turnCounter = 0;
		this.roundNumber = 0;
	}

	/**
	 * addPlayer function. Adds a player to the players ArrayList if they aren't already added.
	 * If players ArrayList is empty, also set new player as currentPlayer.
	 * @param p
	 * @return
	 */
	public boolean addPlayer(Player p) {
		if(this.players.size()==0) {
			this.players.add(p);
			this.setCurrentPlayer(p);
			return true;
		}
		for (int i = 0; i < this.players.size(); i++) {
			if (p.getPlayerName().equals(this.players.get(i).getPlayerName())) {
				System.out.println("Player " + p.getPlayerName() + " already added to match.");
				return false;
			}
		}
		this.players.add(p);
		return true;
	}
	
	/**
	 * gets the round number
	 * @return
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * sets the round number
	 * @param roundNumber
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	/**
	 * gets the turn number
	 * @return
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * sets the turn number
	 * @param turnNumber
	 */
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	/**
	 * gets the turn counter
	 * @return
	 */
	public int getTurnCounter() {
		return turnCounter;
	}

	/**
	 * sets the turn counter
	 * @param turnCounter
	 */
	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}

	/**
	 * gets the players
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * sets the players
	 * @param players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	/**
	 * gets the winner
	 * @return
	 */
	public static Player getWinner() {
		return winner;
	}

	/**
	 * sets the winner
	 * @param winner
	 */
	public static void setWinner(Player winner) {
		Match.winner = winner;
	}

	/**
	 * nextTurn function. Set currentPlayer to the next in players ArrayList and increment turnCounter.
	 * Check that the next player's index isn't out of bounds. If it is out of bounds, we've hit the final player.
	 * If we've hit the final player we need to set the currentPlayer to our first player, at index 0.
	 */
	public void nextTurn() {
		try {
			this.turnNumber = (this.turnNumber + 1) % this.playerCount; // wraps around back to zero when needed
			if (this.turnNumber == 0) {
				this.roundNumber++;
				if (this.roundNumber == 13) {
					this.endGame();
					return;
				}
			} 		
			this.setCurrentPlayer(this.players.get(this.turnNumber));
			this.turnCounter++;
		}
		catch(Exception e) {System.out.println("\nError: player index out of bounds for players ArrayList");}
	}
	
	/**
	 * end the game
	 */
	public void endGame() {
		System.out.println("GAME OVER");
		// calculate each player's final score
		this.determineWinner();
		GameplayController.gameOver = true;
	}
	
	/**
	 * determines the winner
	 */
	public void determineWinner() {
		int bestScore = -1;
		winner = null;
		for (int i = 0; i < this.players.size(); i++) {
			int curTotal = this.players.get(i).getPlayerFinalScore();
			if (curTotal > bestScore) {
				bestScore = curTotal;
				winner = this.players.get(i);
			}
		}
		System.out.println("WINNER: " + winner.getPlayerName());
	}
	
	//Getters.
	/**
	 * gets the current player
	 * @return
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * gets the player count
	 * @return
	 */
	public int getPlayerCount() {
		return this.playerCount;
	}
	
	/**
	 * gets the turn count
	 * @return
	 */
	public int getTurnCount() {
		return this.turnCounter;
	}
	
	/**
	 * getSize for testing purposes.
	 * @return
	 */
	public int getSize() {
		return players.size();
	}
	
	//Setters.
	/**
	 * sets the current player
	 * @param c
	 */
	public void setCurrentPlayer(Player c) {
		if(players.contains(c)) {
			this.currentPlayer = c;
		}
	}
		
	/**
	 * sets the player count
	 * @param p
	 */
	public void setPlayerCount(int p) {
		this.playerCount = p;
	}
	
	/**
	 * sets the turn count
	 * @param t
	 */
	public void setTurnCount(int t) {
		this.turnCounter = t;
	}
	
	
	@Override
	/**
	 * toString method. Prints each player in players Array List.
	 */
	public String toString() {
		String x = "\nMatch: \nCurrentTurn: "+this.turnCounter+"\nCurrentPlayer: "+this.getCurrentPlayer().getPlayerName()+"\nPlayers: "+this.playerCount;
		for(Player play : this.players) {
			x = x+"\t"+play.toString()+"\n";
		}
		return x;
	}
	
}
