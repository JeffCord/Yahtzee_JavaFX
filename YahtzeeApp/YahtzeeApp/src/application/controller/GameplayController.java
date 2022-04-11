package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Match;
import application.model.Player;
import javafx.fxml.Initializable;

public class GameplayController implements Initializable {

	static int numOfPlayers = 1;
	
	static Match match;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
//	public GameplayController() {
//		// create match and players
//		match = new Match(numOfPlayers);
//		for (int i = 0; i < numOfPlayers; i++) {
//			String playerName = "Player #" + i; // this is TEMPORARY until we finish the player names scene/code
//			Player newPlayer = new Player(playerName); // TODO get custom player name
//			match.addPlayer(newPlayer);
//		}
//		
//	}
	
	
}
