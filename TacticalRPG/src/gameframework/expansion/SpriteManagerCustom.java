package gameframework.expansion;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import gameframework.base.DrawableImage;
import gameframework.game.SpriteManager;

public class SpriteManagerCustom implements SpriteManager {

	private final DrawableImage image;
	private Map<String, Integer> types;
	private final int spriteSizeW;
	private final int spriteSizeH;
	private int spriteNumber = 0;
	private final int maxSpriteNumberW;
	private int currentRow;
	private final int renderingSizeW;
	private final int renderingSizeH;

	public SpriteManagerCustom(String filename, Canvas canvas,
			int renderingSizeW, int renderingSizeH, int maxSpriteNumberW, int maxSpriteNumberH) {
		this.renderingSizeW = renderingSizeW;
		this.renderingSizeH = renderingSizeH;
		image = new DrawableImage(filename, canvas);
		this.maxSpriteNumberW = maxSpriteNumberW;
		this.spriteSizeW = image.getImage().getWidth(null) / maxSpriteNumberW;
		this.spriteSizeH = image.getImage().getHeight(null) / maxSpriteNumberH;
	}

	@Override
	public void setTypes(String... types) {
		int i = 0;
		this.types = new HashMap<String, Integer>(types.length);
		for (String type : types) {
			this.types.put(type, i);
			i++;
		}
	}

	@Override
	public void setType(String type) {
		if (!types.containsKey(type)) {
			throw new IllegalArgumentException(type
					+ " is not a valid type for this sprite manager.");
		}
		this.currentRow = types.get(type);
	}

	@Override
	public void draw(Graphics g, Point position) {
		// Destination image coordinates
		int dx1 = (int) position.getX();
		int dy1 = (int) position.getY();
		int dx2 = dx1 + renderingSizeW;
		int dy2 = dy1 + renderingSizeH;

		// Source image coordinates
		int sx1 = spriteNumber * spriteSizeW;
		int sy1 = currentRow * spriteSizeH;
		int sx2 = sx1 + spriteSizeW;
		int sy2 = sy1 + spriteSizeH;
		g.drawImage(image.getImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
				null);
	}

	@Override
	public void increment() {
		spriteNumber = (spriteNumber + 1) % maxSpriteNumberW;
	}

	@Override
	public void reset() {
		spriteNumber = 0;
	}

	@Override
	public void setIncrement(int increment) {
		this.spriteNumber = increment;

	}

}
