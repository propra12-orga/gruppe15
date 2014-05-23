package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Image {
	public int[] pixels;
	public int h;
	public int w;
	public BufferedImage image;

	/**
	 * @param w
	 * @param h
	 */
	public Image(int w, int h, int type) {
		this.w = w;
		this.h = h;
		this.image = new BufferedImage(w, h, type);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer())
				.getData();
	}
}
