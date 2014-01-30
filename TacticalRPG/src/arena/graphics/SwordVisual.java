package arena.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.SpriteManager;

public class SwordVisual implements Drawable, Overlappable{
	protected final SpriteManager spriteManager;
	protected DrawableImage tempSword;
	public static final int ATTACK_DURATION = 20;
	public static final int RENDERING_SIZE_W = (int) (24*1.35);
	public static final int RENDERING_SIZE_H = (int) (26*1.35);
	protected boolean movable = true;
	protected int attackingTimer = 0;
	private String prev = "down_static";

	public SwordVisual(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/sprite_link_v1.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 10, 8);
		spriteManager.setTypes("down", "left", "right", "up");
		
		tempSword = new DrawableImage("src/ressources/img/epee.png", defaultCanvas);
	}
	
	public void setAttacking() {
		attackingTimer = ATTACK_DURATION;
	}
	
	public void decrementAttackTimer(){
		--attackingTimer;
	}

	public boolean isAttacking() {
		return (attackingTimer > 0);
	}
	
	public void draw(Graphics g) {
		g.drawImage(tempSword.getImage(), getPosition().x, getPosition().y, RENDERING_SIZE_W, RENDERING_SIZE_H,
				null);
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE_W, RENDERING_SIZE_H));
	}

	@Override
	public Point getPosition() {
		return this.getPosition();
	}
}
