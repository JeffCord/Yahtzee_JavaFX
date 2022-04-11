package application.controller;

import java.util.ArrayList;

import application.model.Dice;
import application.model.Match;
import application.model.Player;
import application.model.ScoreCard;

public class GameplayController {

	static int numOfPlayers = 1;
	
	private Match match;
	
	public GameplayController() {
		// create match and players
		match = new Match(numOfPlayers);
		for (int i = 0; i < numOfPlayers; i++) {
			String playerName = "Player #" + i; // this is TEMPORARY until we finish the player names scene/code
			Player newPlayer = new Player(playerName); // TODO get custom player name
			match.addPlayer(newPlayer);
		}
		
	}
	
	
}
