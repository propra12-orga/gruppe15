package level;

public class Loader {

	public boolean isBlocking(String S, int zeilen, int spalten) {

		int i, j, l, k = 0;
		char arr[][] = new char[zeilen][spalten];
		for (i = 0; i < zeilen; i++) {
			for (j = 0; j < spalten; j++) {
				arr[i][j] = S.charAt(k);
				l = (arr[i][j] - '0');
				if (l == 1) {
					return true;
				}
				if (l == 2) {
					return false;
				}
				k++;
			}
		}
	}
}