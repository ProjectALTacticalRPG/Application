package arena.graphics;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class KeatonVisual extends LinkedEntity implements Drawable, GameEntity,
Overlappable {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE_W = (int) (32*1.35);
	public static final int RENDERING_SIZE_H = (int) (26*1.35);
	protected boolean movable = true;
	private String prev = "down";

	public KeatonVisual(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("ressources/img/sprite_keaton_v1.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 4, 4);
		spriteManager.setTypes("up", "right", "left", "down");
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;

		if (tmp.getX() == 1) {
			spriteType += "right";
		} else if (tmp.getX() == -1) {
			spriteType += "left";
		} else if (tmp.getY() == 1) {
			spriteType += "down";
		} else if (tmp.getY() == -1) {
			spriteType += "up";
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
			if (!isVulnerable()) {
				vulnerableTimer--;
			}
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE_W, RENDERING_SIZE_H));
	}

	@Override
	public void addSword() {
		//Not implemented
	}

	@Override
	public void removeSword() {
		//Not implemented
	}
}

