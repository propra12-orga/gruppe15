package level;

import entities.BreakableWall;
import entities.Wall;
import game.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

	public void addWalls(String filename, int zeilen, int spalten) {

		int i, j, l, k = 0;
		int[][] arr = new int[zeilen][spalten];
		Scanner maps;
		try {
			maps = new Scanner(new FileInputStream("/ressources/maps/"
					+ filename));
			String text = maps.toString();
			for (i = 0; i < zeilen; i++) {
				for (j = 0; j < spalten; j++) {
					arr[i][j] = Integer.parseInt("" + text.charAt(k));
					l = arr[i][j];

					if (l == 1) {
						Game.entities.add(new BreakableWall(
								j * Game.BLOCK_SIZE, i * Game.BLOCK_SIZE));
					}
					if (l == 2) {
						Game.entities.add(new Wall(j * Game.BLOCK_SIZE, i
								* Game.BLOCK_SIZE));
					}
					k++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
