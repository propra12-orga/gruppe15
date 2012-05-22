package level;

import entities.Background;
import entities.BreakableWall;
import entities.Finishpoint;
import entities.Player1;
import entities.Player2;
import entities.Wall;
import entities.WallWithFinishingPoint;
import game.Game;
import game.Main;

import java.util.Scanner;

public class Loader {

	/**
	 * Creates a level from a map file Types: 1 = Breakable Wall 2 = Solid Wall
	 * 3 = Spawnpoint 0 = empty Background 5 = Finishpoint
	 * 
	 * @param filename
	 */
	public void loadMap(String filename) {

		int x = 0, type, y = 0;

		Scanner maps;
		try {

			maps = new Scanner(
					Main.class.getResourceAsStream("/ressources/maps/"
							+ filename));
			while (maps.hasNextLine()) {
				String text = maps.nextLine();
				for (x = 0; x < text.length(); x++) {
					type = Integer.parseInt("" + text.charAt(x));

					if (type == 1) {
						Game.entities.add(new BreakableWall(
								x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 2) {
						Game.entities.add(new Wall(x * Game.BLOCK_SIZE, y
								* Game.BLOCK_SIZE));
					} else if (type == 3) {
						Player1 p1 = new Player1(x * Game.BLOCK_SIZE, y
								* Game.BLOCK_SIZE);
						Game.entities.add(p1);
						Game.players.add(p1);
						Game.staticBackground.add(new Background(x
								* Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 4) {
						Player2 p2 = new Player2(x * Game.BLOCK_SIZE, y
								* Game.BLOCK_SIZE);
						Game.entities.add(p2);
						Game.players.add(p2);
						Game.staticBackground.add(new Background(x
								* Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 5) {
						Game.entities.add(new Finishpoint(x * Game.BLOCK_SIZE,
								y * Game.BLOCK_SIZE));
						Game.staticBackground.add(new Background(x
								* Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 0) {
						Game.staticBackground.add(new Background(x
								* Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					} else if (type == 6) {
						Game.entities.add(new WallWithFinishingPoint(x
								* Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));

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
