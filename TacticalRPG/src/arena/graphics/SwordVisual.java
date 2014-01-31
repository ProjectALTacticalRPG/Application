package arena.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import arena.game.Weapon;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

public class SwordVisual extends GameMovable implements Drawable, Overlappable, GameEntity, Weapon{
	public final SpriteManager spriteManager;
	protected DrawableImage tempSword;
	public static final int RENDERING_SIZE_W = (int) (26*1.35);
	public static final int RENDERING_SIZE_H = (int) (26*1.35);
	protected boolean movable = true;
	protected boolean attacking = false;
	boolean cooldown = false;
	protected LinkedEntity master;
	
	public SwordVisual(Canvas defaultCanvas, LinkedEntity master) {
		spriteManager = new SpriteManagerCustom("ressources/img/link_sword.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 1, 4);
		spriteManager.setTypes("up", "down", "right", "left");
		this.master = master;
	}

	public void draw(Graphics g) {
		if(isAttacking()){
			spriteManager.reset();
			spriteManager.draw(g, getPosition());
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE_W, RENDERING_SIZE_H));
	}
	
	@Override
	public LinkedEntity getOwner(){
		return master;
	}

	public boolean isAttacking(){
		return attacking;
	}

	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		
	}
}
