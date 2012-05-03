import graphics.Image;
import junit.framework.Assert;

import org.junit.Test;

public class ImageTest {

	@Test
	public void testImage() {
		Image i = new Image(10, 40);

		Assert.assertEquals(10, i.w);
		Assert.assertEquals(40, i.h);

	}

}
