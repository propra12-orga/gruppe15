package sound;

import game.Settings;

import java.util.concurrent.CopyOnWriteArrayList;

public class Soundmanager {
	/**
	 * Static var for Singleton
	 */
	private static Soundmanager instance = null;

	/**
	 * Get Singleton-Instance
	 * 
	 * @return Game
	 */
	public static Soundmanager getInstance() {
		if (Soundmanager.instance == null) {
			Soundmanager.instance = new Soundmanager();
		}
		return Soundmanager.instance;
	}

	private CopyOnWriteArrayList<Sound> sounds;
	private boolean status;

	private Soundmanager() {
		this.sounds = new CopyOnWriteArrayList<Sound>();
		if (Settings.getInstance().getBoolean("sound") == true) {
			this.status = true;
		} else {
			this.status = false;
		}
	}

	public Sound load(String filename, boolean repeat) {
		Sound s = new Sound(filename, repeat);
		this.sounds.add(s);
		return s;
	}

	public void enable() {
		this.status = true;
		for (Sound s : this.sounds) {
			if (s.repeat()) {
				s.play();
			}
		}
	}

	public void disable() {
		this.status = false;
		for (Sound s : this.sounds) {
			s.stop();
		}
	}

	public boolean enabled() {
		return this.status;
	}
}
