package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import level.Box;
import entities.Entity;

public class Game extends Canvas implements Runnable {

	public static final int GAME_WIDTH = 512;
	public static final int GAME_HEIGHT = (Game.GAME_WIDTH * 3) / 4;
	public static final int SCALE = 1;
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private boolean running;

	private int updateRate = 60;
	private long frameTimeNs = 1000000000 / this.updateRate;
	public int fps_static = 0;
	public int fps = 0;
	private InputHandler keys;

	public Game() {
		Dimension d = new Dimension(Game.GAME_WIDTH * Game.SCALE, Game.GAME_HEIGHT * Game.SCALE);
		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		this.setBackground(new Color(255, 255, 255));
		this.keys = new InputHandler();
		this.addKeyListener(this.keys);
	}

	@Override
	public void run() {
		long lastLoopTime = System.nanoTime();
		int lastFpsTime = 0;
		long sleepTime = 0;
		while (this.running) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) this.frameTimeNs);

			lastFpsTime += updateLength;
			this.fps++;

			if (lastFpsTime >= 1000000000) {
				this.fps_static = this.fps;
				lastFpsTime = 0;
				this.fps = 0;
			}

			this.step(delta);

			BufferStrategy bs = this.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			this.draw(g);

			bs.show();
			Toolkit.getDefaultToolkit().sync();

			sleepTime = (lastLoopTime - System.nanoTime()) / 1000000;
			if (sleepTime < 10) {
				sleepTime = 10;
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void start() {
		this.running = true;
		this.createBufferStrategy(2);
		this.run();
		this.requestFocusInWindow();
		this.requestFocus();
	}

	public void stop() {
		this.running = false;
	}

	private void step(double delta) {
		for (Entity e : Game.entities) {
			e.step(delta);
		}
	}

	private void draw(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, (Game.GAME_WIDTH * Game.SCALE) + 10, (Game.GAME_HEIGHT * Game.SCALE) + 10);
		for (Entity e : Game.entities) {
			e.draw(g);
		}
		g.setColor(Color.BLACK);
		g.drawString("FPS: " + this.fps_static, 0, 10);
	}

	public static List<Entity> getEntities(int x1, int y1, int x2, int y2) {
		List<Entity> result = new ArrayList<Entity>();
		Box b = new Box(Math.max(0, x1), Math.max(0, y1), Math.min(x2, Game.GAME_WIDTH), Math.min(y2, Game.GAME_HEIGHT));

		for (Entity e : Game.entities) {
			if (e.box.intersect(b)) {
				result.add(e);
			}
		}
		return result;
	}
}
