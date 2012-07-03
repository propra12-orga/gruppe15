package level;

import entities.Background;
import entities.BreakableWall;
import entities.Finishpoint;
import entities.Player;
import entities.Wall;
import entities.WallWithFinishingPoint;
import enums.Gamemode;
import game.Debug;
import game.Game;
import game.Main;
import input.KeySettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

	/**
	 * Creates a level from a map file Types: 0 = empty Background 1 = Breakable
	 * Wall 2 = Solid Wall 3 = Spawnpoint 4 = Finishpoint 5 =
	 * WallWithFinishPoint
	 * 
	 * @param filename
	 */
	public String loadMap(String filename) {

		String content = "";

		URL file = Main.class.getResource("/ressources/maps/" + filename);
		if (file == null) {
			Debug.log(Debug.ERROR, "Can't find map");
		} else {
			try {
				content = Loader.readFile(file.getPath());
			} catch (Exception e) {
				try {
					content = Loader.readFile(Main.class.getResourceAsStream("/ressources/maps/" + filename));
				} catch (IOException e1) {
					Debug.log(Debug.ERROR, "Can't read map");
				}

			}
		}

		return content;

	}

	public void loadMapFromString(String map_content) {
		int x = 0, y = 0;
		int i;
		int player_count = 0;
		Scanner s = new Scanner(map_content);
		while (s.hasNextLine()) {
			String zeile = s.nextLine();
			x = 0;
			for (i = 0; i < zeile.length(); i++) {
				int type = Integer.parseInt(String.valueOf(zeile.charAt(i)));
				if (type == 0) {
					Game.staticBackground.add(new Background(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
				} else if (type == 1) {
					Game.entities.add(new BreakableWall(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
				} else if (type == 2) {
					Game.entities.add(new Wall(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
				} else if (type == 3) {
					Player p = new Player(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE);
					if (Game.gamemode != Gamemode.NETWORK) {
						KeySettings keys;
						try {
							keys = Game.getKeySettings(player_count);
							p.setKeys(keys);
							player_count++;
							Game.entities.add(p);
							Game.players.add(p);
						} catch (Exception e) {
							Debug.log(Debug.ERROR, "Too many players in the map for local game");
							return;
						}
					}
					Game.staticBackground.add(new Background(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
				} else if (type == 4) {
					Game.entities.add(new Finishpoint(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
					Game.staticBackground.add(new Background(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));
				} else if (type == 5) {
					Game.entities.add(new WallWithFinishingPoint(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE));

				}
				x++;
			}
			y++;
		}
		Game.FIELD_HEIGHT = y;
		Game.FIELD_WIDTH = x;
	}

	/**
	 * parses a map for multiplayer mode 0 = empty Background 1 = Breakable Wall
	 * 2 = Solid Wall 3 = Spawnpoint 4 = Finishpoint 5 = WallWithFinishPoint
	 * 
	 * @param filename
	 * @return int
	 */
	public int parseForMultiplayer(String filename) {

		int x = 0, type, y = 0;
		int player_count = 0;

		String content = this.loadMap(filename);

		Scanner maps;
		try {

			maps = new Scanner(content);
			while (maps.hasNextLine()) {
				String text = maps.nextLine();
				for (x = 0; x < text.length(); x++) {
					type = Integer.parseInt("" + text.charAt(x));
					if (type == 3) {
						player_count++;
					}

				}
				y++;
			}
			maps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return player_count;
	}

	/**
	 * Return the positions of all Spawnpoints
	 * 
	 * @param filename
	 * @return
	 */
	public ArrayList<Point> getSpawnPoints(String filename) {
		int x = 0, type, y = 0;
		ArrayList<Point> spawnpoints = new ArrayList<Point>();

		String content = this.loadMap(filename);

		Scanner maps;
		try {

			maps = new Scanner(content);
			while (maps.hasNextLine()) {
				String text = maps.nextLine();
				for (x = 0; x < text.length(); x++) {
					type = Integer.parseInt("" + text.charAt(x));
					if (type == 3) {
						spawnpoints.add(new Point(x, y));
					}

				}
				y++;
			}
			maps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return spawnpoints;
	}

	/*
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from
	 * -the-contents-of-a-file
	 */
	public static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	public static String readFile(InputStream f) throws IOException {
		Scanner s = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while (s.hasNextLine()) {
			sb.append(s.nextLine());
			sb.append("\r\n");
		}
		return sb.toString();

	}
}
