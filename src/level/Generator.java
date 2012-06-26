package level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

	Loader l1 = new Loader();
	FileWriter fw;
	File file;
	int fieldheight;
	int fieldwith;
	char maparray[][];

	public void generateMap(int fieldheight, int fieldwith) {
		this.fieldheight = fieldheight;
		this.fieldwith = fieldwith;
		this.maparray = new char[this.fieldheight][this.fieldwith];

		this.file = new File("src/ressources/maps/FileWriterTest.txt");
		String test = "";
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
			for (int i = 0; i < (this.fieldheight); i++) {
				for (int j = 0; j < (this.fieldwith); j++) {
					this.fw.write(this.maparray[i][j]);
				}
				this.fw.write(System.getProperty("line.separator"));
			}

			this.fw.write(test);
			this.fw.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}