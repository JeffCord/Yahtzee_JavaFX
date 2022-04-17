package application.model;

import java.util.HashMap;

public class GameplayModel {
	private HashMap <Integer, String> diceImageFilesMap;
	
	public GameplayModel() {
		this.diceImageFilesMap = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
			this.diceImageFilesMap.put(i, "dice-" + i + ".png");
		}
	}
	
	public HashMap<Integer, String> getDiceMap() {
		return diceImageFilesMap;
	}

	public void setDiceMap(HashMap<Integer, String> diceMap) {
		this.diceImageFilesMap = diceMap;
	}

	
}
