package application.model;

import java.util.HashMap;

/**
 * This class handles the gameplay model
 * 
 * @author Jeffrey Cordes (vkn217)
 * UTSA CS 3443 - Group Project
 * Spring 2022
 */
public class GameplayModel {
	private HashMap <Integer, String> diceImageFilesMap;
	
	/**
	 * the constructor for the gameplay model
	 */
	public GameplayModel() {
		this.diceImageFilesMap = new HashMap<>();
		for (int i = 1; i <= 6; i++) {
			this.diceImageFilesMap.put(i, "images/dice-" + i + ".png");
		}
	}
	
	/**
	 * gets the dice hash map
	 * @return
	 */
	public HashMap<Integer, String> getDiceMap() {
		return diceImageFilesMap;
	}

	/**
	 * sets the dice hash map
	 * @param diceMap
	 */
	public void setDiceMap(HashMap<Integer, String> diceMap) {
		this.diceImageFilesMap = diceMap;
	}

	
}
