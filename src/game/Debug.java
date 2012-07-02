package game;

/**
 * @author Philipp
 * 
 */
public class Debug {
	/**
	 * Constant for important messages
	 */
	public static int ERROR = 1;
	/**
	 * Constant for debug messages
	 */
	public static int DEBUG = 2;
	/**
	 * Constant for no messages
	 */
	public static int NONE = 0;

	/**
	 * Set default mode
	 */
	private static int mode = Debug.DEBUG;

	/**
	 * Set the new mode
	 * 
	 * @param m
	 */
	public static void setMode(int m) {
		Debug.mode = m;
	}

	/**
	 * Output a message if current mode is <= message mode
	 * 
	 * @param m
	 *            int DEBUG.[ERROR|DEBUG|VERBOSE|NONDE]
	 * @param message
	 *            The message to be print
	 */
	public static void log(int m, Object message) {
		if (m <= Debug.mode) {
			System.out.println(message.toString());
		}
	}
}
