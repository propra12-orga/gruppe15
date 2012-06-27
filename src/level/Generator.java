package level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {

	// Loader l1 = new Loader();
	FileWriter fw;
	File file;
	int fieldheight;
	int fieldwith;
	char maparray[][];

	public void generateMap(int fieldheight, int fieldwith) {
		this.fieldheight = fieldheight;
		this.fieldwith = fieldwith;
		this.maparray = new char[this.fieldheight][this.fieldwith];

		this.file = new File("src/ressources/maps/genMap");
		try {
			this.fw = new FileWriter(this.file);

			for (int j = 0; j < this.fieldheight; j++) {

				this.maparray[j][0] = '2';
				if ((j >= (this.fieldheight - 1)) || (j == 0)) {
					for (int i = 1; i < (this.fieldwith - 1); i++) {
						this.maparray[j][i] = '2';
					}
				} else {
					for (int i = 1; i < (this.fieldwith - 1); i++) {

						this.maparray[j][i] = '0';
					}
				}

				this.maparray[j][this.fieldwith - 1] = '2';
			}
			// unbegehbaren Teil erstellen
			for (int j = 1; j < (this.fieldheight - 1); j++) {
				for (int i = 1; i < (this.fieldwith - 1); i++) {
					if (new Random().nextBoolean() == true) {
						this.maparray[j][i] = '2';
					} else {
						this.maparray[j][i] = '1';
					}
				}
			}
			// Player spawnen
			int rnd = new Random().nextInt((this.fieldheight - 3)) + 1;
			this.maparray[1][rnd] = '3';
			this.maparray[1][rnd + 1] = '0';
			this.maparray[2][rnd] = '0';
			this.maparray[this.fieldheight - 2][rnd] = '3';
			this.maparray[this.fieldheight - 2][rnd + 1] = '0';
			this.maparray[this.fieldheight - 3][rnd] = '0';
			// Ziel spawnen
			this.maparray[this.fieldheight / 2][this.fieldwith / 2] = '5';

			// generiertes Feld schreiben
			for (int i = 0; i < (this.fieldheight); i++) {
				for (int j = 0; j < (this.fieldwith); j++) {
					this.fw.write(this.maparray[i][j]);
				}
				if (!(i >= (this.fieldheight - 1))) {

					this.fw.write(System.getProperty("line.separator"));
				}
			}
			this.fw.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}