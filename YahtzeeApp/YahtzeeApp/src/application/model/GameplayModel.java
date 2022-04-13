package application.model;

import java.util.HashMap;

public class GameplayModel {
	private HashMap <Integer, String> diceMap;
	
	public GameplayModel() {
		this.diceMap = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
			this.diceMap.put(i, "dice-" + i + ".png");
		}
	}
	
	public HashMap<Integer, String> getDiceMap() {
		return diceMap;
	}

	public void setDiceMap(HashMap<Integer, String> diceMap) {
		this.diceMap = diceMap;
	}

	
}
