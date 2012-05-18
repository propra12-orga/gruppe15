package level;

import entities.Background;
import entities.BreakableWall;
import entities.Player;
import entities.Wall;
import game.Game;
import game.Main;

import java.util.Scanner;

public class Loader {

	/**
	 * Creates a level from a map file Types: 1 = Breakable Wall 2 = Solid Wall
	 * 3 = Spawnpoint 0 = empty Background
	 * 
	 * @param filename
	 */
	public void addWalls(String filename) {

		int x = 0, type, y = 0;

		Scanner maps;
		try {

			maps = new Scanner(Main.class.getResourceAsStream("/ressources/maps/" + filename));
			while (maps.hasNextLine()) {
				String text = maps.nextLine();
				for (x = 0; x < text.length(); x++) {
					type = Integer.parseInt("" + text.charAt(x));

					if (type == 1) {
						Game.entities.add(new BreakableWall(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 2) {
						Game.entities.add(new Wall(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 3) {
						Player p = new Player(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE);
						Game.entities.add(p);
						Game.players.add(p);
						Game.staticBackground.add(new Background(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 0) {
						Game.staticBackground.add(new Background(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					}

				}
				y++;
			}
			Game.FIELD_HEIGHT = y;
			Game.FIELD_WIDTH = x;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
