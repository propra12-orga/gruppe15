package entities;

import java.awt.Graphics;


public class Wall extends Entity {

	public int size;;
	
	public Wall(int x, int y) {
		super (x,y);
		this.isBlocking = true;
		this.size = 5;
	}
	
	public void draw(Graphics g){
		g.setColor(null);
		g.drawRect(x,y,x+size,y+size);
	}
}
