package arena.graphics;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class CocotteVisual extends LinkedEntity implements Drawable, GameEntity,
Overlappable {
	protected final SpriteManager spriteManager;
	protected DrawableImage shadow;
	public static final int RENDERING_SIZE_W = (int) (24);
	public static final int RENDERING_SIZE_H = (int) (24);
	public static final int DAMAGE_COCOTTE = 10;
	protected boolean movable = true;

	public CocotteVisual(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("ressources/img/cocottes.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 3, 2);
		spriteManager.setTypes("left", "right");
		
		shadow = new DrawableImage("ressources/img/shadow.png", defaultCanvas);
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
		} else {
			spriteType += "left";
		}
		
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
		g.drawImage(shadow.getImage(), posX, posY+30, RENDERING_SIZE_W-6, RENDERING_SIZE_H,
				null);

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
		// Not implemented
	}

	@Override
	public void removeSword() {
		// Not implemented
	}

}

