package application.model;

import java.io.BufferedReader;	
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Match {

	//Initialization of important variables.
	int playerCount;
	int turnNumber; // indicates whose turn it is. [0, playerCount - 1]
	int turnCounter;
	int roundNumber;
	Player currentPlayer;
	ArrayList<Player> players = new ArrayList<Player>();
	
	static Player winner = null;
	
	//Match constructor. Takes an int n, number of players, and assigns it to playerCounter.
	//Initializes turnCounter to zero.
	public Match(int n) {
		this.playerCount = n;
		this.turnNumber = 0;
		this.turnCounter = 0;
		this.roundNumber = 0;
	}

	//addPlayer function. Adds a player to the players ArrayList if they aren't already added.
	//If players ArrayList is empty, also set new player as currentPlayer.
	public boolean addPlayer(Player p) {
		if(this.players.size()==0) {
			this.players.add(p);
			this.setCurrentPlayer(p);
			return true;
		}
//		if(!this.players.contains(p)) { // NOTE: This if statement was buggy because it was comparing a reference, not the name String
//			this.players.add(p);
//			return true;
//		}
		for (int i = 0; i < this.players.size(); i++) {
			if (p.getPlayerName().equals(this.players.get(i).getPlayerName())) {
				System.out.println("Player " + p.getPlayerName() + " already added to match.");
				return false;
			}
		}
		this.players.add(p);
		return true;
	}
	
	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public static Player getWinner() {
		return winner;
	}

	public static void setWinner(Player winner) {
		Match.winner = winner;
	}

	//nextTurn function. Set currentPlayer to the next in players ArrayList and increment turnCounter.
	//Check that the next player's index isn't out of bounds. If it is out of bounds, we've hit the final player.
	//If we've hit the final player we need to set the currentPlayer to our first player, at index 0.
	public void nextTurn() {
		try {
//			int next = this.players.indexOf(currentPlayer)+1;
//			if(!(next>this.players.size()-1)) {
//				this.setCurrentPlayer(this.players.get(next));
//				this.turnCounter++;
//			}
//			else {
//				this.setCurrentPlayer(this.players.get(0));
//				this.turnCounter++;
//			}
			this.turnNumber = (this.turnNumber + 1) % this.playerCount; // wraps around back to zero when needed
			this.setCurrentPlayer(this.players.get(this.turnNumber));
			this.turnCounter++;
		}
		catch(Exception e) {System.out.println("\nError: player index out of bounds for players ArrayList");}
	}
	
	
	//saveMatch function. Writes all match information to a text file.
	//Each player has their own line in the text file which holds their name and score card values.
	public void saveMatch(Match m) {
		try {
			File saveFile = new File("../Yahtzee/saveGame.txt");
			FileWriter myWriter = new FileWriter(saveFile);
			
			//Hard write simple match variables to beginning of text file.
			//Each is contained on its own line.
			myWriter.write(String.valueOf(this.playerCount)+'\n');
			myWriter.write(String.valueOf(this.turnNumber)+'\n');
			myWriter.write(String.valueOf(this.turnCounter)+'\n');
			myWriter.write(String.valueOf(this.roundNumber)+'\n');
			myWriter.write(this.currentPlayer.getPlayerName()+'\n');
			
			//For every player in the match's "players" array list.
			//Gather their score card & keys. Write name to file followed by score card values.
			for(Player p : this.players) {
				Hashtable<String, Integer> sc = p.getScoreCard().getScoreCard();
				String[] k = p.getScoreCard().getKeys();
				
				//Write player name as start of line.
				myWriter.write(p.getPlayerName()+';');
				
				//For every string in "keys" write score card value to text file.
				for(String a : k) {
					//If we're on the last score card value, don't add a field separator to end of line.
					if (a==k[k.length-1]) {
						myWriter.write(String.valueOf(sc.get(a)));
						break;
					}
					myWriter.write(String.valueOf(sc.get(a))+';');
				}
				//End each player line with a newline.
				myWriter.write('\n');
			}
			//Remember to close file writer because I'm not a scrub.
			myWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//loadMatch function. Takes in a file path/name and outputs a corresponding match object.
	//Each line from the file is read and its contents applied to the appropriate match variable.
	//All players are added along with their score card information within save file.
	public Match loadMatch(String a) throws IOException {
			
			//Initialization of important tools.
			BufferedReader br = new BufferedReader(new FileReader(a));
			String holder;
			
			//Read first line which holds number of players. Load into new Match instance to be returned.
			holder=br.readLine();
			Match m = new Match(Integer.parseInt(holder));
			
			//Read next line for turn number. Load into Match "m".
			holder=br.readLine();
			m.setTurnNumber(Integer.parseInt(holder));
			
			//Read next line for turn counter. Load into Match "m".
			holder=br.readLine();
			m.setTurnCounter(Integer.parseInt(holder));
			
			//Read next line for round number. Load into Match "m".
			holder=br.readLine();
			m.setRoundNumber(Integer.parseInt(holder));
			
			//Read next line for current player. Hold onto name for later use.
			holder=br.readLine();
			String currP = holder;
			
			//Read remaining lines with player & score card information.
			while ((holder=br.readLine()) != null) {
				//Split line into fields.
				String[] parts = holder.split(";");
				
				//Create new player object to be initialized.
				//Use first index of parts to input player name.
				Player p = new Player(parts[0]);
				
				//Create/gather necessary tools to populate player score card.
				ScoreCard c = new ScoreCard();
				String[] k = p.getScoreCard().getKeys();
				int i = 1;
				
				//For every score card value, fill value with next index of parts.
				for(String key : k) {
					c.setScore(key, Integer.parseInt(parts[i]));
					i++;
				}
				//Set player score card to equal filled score card & add player to match array list.
				p.setScoreCard(c);
				m.addPlayer(p);
				
				//If current player's name equals loading match's current player, set player as current player.
				if(p.getPlayerName().equals(currP)) {
					m.setCurrentPlayer(p);
				}
			}
			
			//Close buffered reader and return match object.
			br.close();
			return m;
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
		String x = "\nMatch: \nCurrentRound: "+this.roundNumber+"\nCurrentTurn: "+this.turnCounter+"\nCurrentPlayer: "+this.getCurrentPlayer().getPlayerName()+"\nTotalPlayers: "+this.playerCount;
		for(Player play : this.players) {
			x = x+"\t"+play.toString()+"\n";
		}
		return x;
	}
	
}
