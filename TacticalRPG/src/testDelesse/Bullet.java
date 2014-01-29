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
	public static final int RENDERING_SIZE_W = (int) (7*1.35);
	public static final int RENDERING_SIZE_H = (int) (7*1.35);

	public Bullet(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/fire.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 1, 1);
		spriteManager.setTypes("fire");
		prev="fire";
		shadow = new DrawableImage("src/ressources/img/shadow.png", defaultCanvas);
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		if (tmp.getX() == 1) {
			spriteType += "fire";
		} else if (tmp.getX() == -1) {
			spriteType += "fire";
		} else if (tmp.getY() == 1) {
			spriteType += "fire";
		} else if (tmp.getY() == -1) {
			spriteType += "fire";
		} else {
			
			spriteType = prev;
			
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

