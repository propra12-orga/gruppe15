package level;

import entities.BreakableWall;
import entities.Wall;
import game.Game;

public class Loader {

	public void addWalls(String S, int zeilen, int spalten) {

		int i, j, l, k = 0;
		char arr[][] = new char[zeilen][spalten];
		for (i = 0; i < zeilen; i++) {
			for (j = 0; j < spalten; j++) {
				arr[i][j] = S.charAt(k);
				l = (arr[i][j] - '0');
				if (l == 1) {
					Game.entities.add(new BreakableWall(i, j));
				}
				if (l == 2) {
					Game.entities.add(new Wall(i, j));
				}
				k++;
			}
		}
	}
}
