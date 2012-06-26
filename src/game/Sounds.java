package game;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sounds implements LineListener {
	public Sounds(String filename, boolean restart) {
		this.filename = filename;
		this.restart = restart;
	}

	private String filename;
	private Clip clip;
	private boolean restart;

	public void play() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class
					.getResource("/ressources/sound/" + this.filename));
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);

			this.clip = (Clip) AudioSystem.getLine(info);
			this.clip.addLineListener(this);
			this.clip.open(af, audio, 0, size);
			this.clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if (this.restart && (type == LineEvent.Type.STOP)) {
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}

}