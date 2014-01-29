package testDelesse;

import gameframework.base.DrawableImage;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Bullet extends Projectile {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE_W = (int) (15*1.35);
	public static final int RENDERING_SIZE_H = (int) (16*1.35);

	public Bullet(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/fire.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 1, 2);
		spriteManager.setTypes("fire","explose");
		prev="fire";
		shadow = new DrawableImage("src/ressources/img/shadow.png", defaultCanvas);
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		if (tmp.getX() == 1) {
			spriteType += "explose";
		} else if (tmp.getX() == -1) {
			spriteType += "explose";
		} else if (tmp.getY() == 1) {
			spriteType += "explose";
		} else if (tmp.getY() == -1) {
			spriteType += "explose";
		} else {
			
			spriteType = "fire";
			
			spriteManager.reset();
			movable = false;
		}
		
		prev = spriteType;
		spriteManager.setType(spriteType);
		
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE_W, RENDERING_SIZE_H));
	}

}

