package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	public Sounds(String filename) {
		this.filename = filename;

	}

	private String filename;

	public void play() {

		Clip clip;
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class
					.getResourceAsStream("/ressources/sound/" + this.filename));
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}