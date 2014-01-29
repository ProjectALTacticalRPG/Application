package arena.graphics;

import gameframework.base.DrawableImage;
import gameframework.game.MoveBlocker;

import java.awt.Canvas;
import java.awt.Rectangle;

public class MapAsset extends MapVisual implements MoveBlocker {
	protected DrawableImage image = null;
	public static final int DEFAULT_RENDERING_SIZE = 16;

	public MapAsset(Canvas defaultCanvas, int xx, int yy, String imgPath) {
		super(defaultCanvas, xx, yy, imgPath);
		sizeW = sizeH = DEFAULT_RENDERING_SIZE;
	}
	
	public MapAsset(Canvas defaultCanvas, int xx, int yy, int sw, int sh, String imgPath) {
		super(defaultCanvas, xx, yy, sw, sh, imgPath);
	}
	
	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, sizeW, sizeH));
	}
}
