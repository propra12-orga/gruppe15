package level;

import game.Main;

import java.util.Scanner;

public class Validator {
	public void validate(String filename) {
		Scanner maps;
		try {

			maps = new Scanner(
					Main.class.getResourceAsStream("/ressources/maps/"
							+ filename));
			while (maps.hasNextLine()) {
				String text = maps.nextLine();

				// TODO Map validieren
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}