package level;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {

	Loader l1 = new Loader();
	FileWriter fw;
	File file;
	int fieldheight, fieldwith;

	public Generator(int fieldheight, int fieldwith) {
		this.fieldheight = fieldheight;
		this.fieldwith = fieldwith;
	}

	public void generateMap() {
		this.file = new File("FileWriterTest.txt");
		String test = "";
		try {
			this.fw = new FileWriter(this.file);

			for (int j = 1; j <= this.fieldheight; j++) {
				test += "2";
				if ((j > this.fieldheight) || (j <= 1)) {
					for (int i = 1; i < (this.fieldwith - 1); i++) {
						test += "2";
					}
				} else {
					for (int i = 1; i < (this.fieldwith - 1); i++) {
						test += "0";
					}
				}
				test += "2";
				if (!(j > (this.fieldheight - 1))) {
					test += System.getProperty("line.separator");
				}
			}

			this.fw.write(test);
			this.fw.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}