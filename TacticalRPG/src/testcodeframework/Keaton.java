package testcodeframework;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Keaton extends GameMovable implements Drawable, GameEntity,
Overlappable {
	protected final SpriteManager spriteManager;
	protected DrawableImage shadow;
	public static final int RENDERING_SIZE_W = (int) (32*1.35);
	public static final int RENDERING_SIZE_H = (int) (26*1.35);
	protected boolean movable = true;
	protected boolean vulnerable = false;
	protected int vulnerableTimer = 0;
	private String prev = "down";

	public Keaton(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/sprite_keaton_v1.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 4, 4);
		spriteManager.setTypes("up", "right", "left", "down");
		
		shadow = new DrawableImage("src/ressources/img/shadow.png", defaultCanvas);
	}

	public void setInvulnerable(int timer) {
		vulnerableTimer = timer;
	}

	public boolean isVulnerable() {
		return (vulnerableTimer <= 0);
	}

	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		movable = true;
		if (!isVulnerable()) {
			spriteType += "invulnerable-";
		}

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
		
		int posX = getPosition().x;
		int posY = getPosition().y+2;
		
		if(spriteType.contains("left")){
			posX+=2;
		}
		else if(spriteType.contains("right")){
			posX +=6;
		}
		else{
			posX +=3;
		}
		
		if(spriteType.contains("up"))
			posY-=2;
		
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

}
