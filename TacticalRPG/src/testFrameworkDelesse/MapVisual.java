package testFrameworkDelesse;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;

public class MapVisual implements Drawable, GameEntity {
	protected DrawableImage image = null;
	int x, y;
	protected int sizeW, sizeH;

	public MapVisual(Canvas defaultCanvas, int xx, int yy, int sw, int sh, String imgPath) {
		image = new DrawableImage(imgPath, defaultCanvas);
		x = xx;
		y = yy;
		sizeW = sw;
		sizeH = sh;
	}
	
	public MapVisual(Canvas defaultCanvas, int xx, int yy, String imgPath) {
		image = new DrawableImage(imgPath, defaultCanvas);
		x = xx;
		y = yy;
		sizeW = image.getImage().getWidth(null);
		sizeH = image.getImage().getHeight(null);;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, sizeW, sizeH,
				null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}
}

