import graphics.Image;
import graphics.Sprite;

import org.junit.Assert;
import org.junit.Test;

public class SpriteTest {

	@Test
	public void testLoading() {

		Image[][] sprite = Sprite.load("sonic.png", 32, 39);

		Assert.assertNotNull(sprite);

		sprite = Sprite.load("not_existing.png", 32, 39);

		Assert.assertNull(sprite);

	}
}
