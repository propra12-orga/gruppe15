package game;

public class Debug {
	public static int ERROR = 1;
	public static int DEBUG = 2;
	public static int NONE = 0;

	static int mode = Debug.NONE;

	public static void setMode(int m) {
		Debug.mode = m;
	}

	public static void log(int m, Object message) {
		if (m <= Debug.mode) {
			System.out.println(message.toString());
		}
	}
}
