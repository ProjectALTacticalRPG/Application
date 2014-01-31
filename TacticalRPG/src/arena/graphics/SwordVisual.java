package arena.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.SpriteManager;

public class SwordVisual implements Drawable, Overlappable, GameEntity{
	public final SpriteManager spriteManager;
	protected Point position;
	protected DrawableImage tempSword;
	public static final int RENDERING_SIZE_W = (int) (26*1.35);
	public static final int RENDERING_SIZE_H = (int) (21*1.35);
	protected boolean movable = true;
	protected boolean attacking = false;

	public SwordVisual(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/sword_mvt_down.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 10, 1);
		spriteManager.setTypes("down");
		position = new Point(0, 0);
		tempSword = new DrawableImage("src/ressources/img/epee.png", defaultCanvas);
	}

	public void draw(Graphics g) {
		if(isAttacking()){
			spriteManager.setType("down");
			spriteManager.draw(g, getPosition());
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE_W, RENDERING_SIZE_H));
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public boolean isAttacking(){
		return attacking;
	}

	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}
}
