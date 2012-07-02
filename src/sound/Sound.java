package sound;

import game.Main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sound implements LineListener {
	public Sound(String filename, boolean restart) {
		this.filename = filename;
		this.restart = restart;
		this.forced = false;
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResource("/ressources/sound/"
					+ this.filename));
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);

			this.clip = (Clip) AudioSystem.getLine(info);
			this.clip.addLineListener(this);
			this.clip.open(af, audio, 0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String filename;
	private Clip clip;
	private boolean restart;
	private boolean forced;

	public void play() {
		if (Soundmanager.getInstance().enabled()) {
			try {
				this.clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if (this.restart && (type == LineEvent.Type.STOP) && (this.forced == false)) {
			this.clip.setFramePosition(0);
			this.clip.start();
			this.forced = false;
		}
	}

	public boolean repeat() {
		return this.restart;
	}

	public void stop() {
		this.forced = true;
		this.clip.stop();
		this.clip.setFramePosition(0);

	}

}