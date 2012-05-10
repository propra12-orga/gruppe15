package level;

import entities.BreakableWall;
import entities.Wall;
import game.Game;
import game.Main;

import java.util.Scanner;

public class Loader {

	public void addWalls(String filename, int zeilen, int spalten) {

		int i, j, k = 0;

		Scanner maps;
		try {

			maps = new Scanner(
					Main.class.getResourceAsStream("/ressources/maps/"
							+ filename));
			while (maps.hasNextLine()) {
				String text = maps.nextLine();

				for (i = 0; i < spalten; i++) {
					j = Integer.parseInt("" + text.charAt(i));

					if (j == 1) {
						Game.entities.add(new BreakableWall(
								i * Game.BLOCK_SIZE, k * Game.BLOCK_SIZE));
					}
					if (j == 2) {
						Game.entities.add(new Wall(i * Game.BLOCK_SIZE, k
								* Game.BLOCK_SIZE));

					}
				}
				k++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
