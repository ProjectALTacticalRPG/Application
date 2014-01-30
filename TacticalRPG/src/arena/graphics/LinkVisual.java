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

public class LinkVisual extends LinkedEntity implements Drawable, GameEntity,
Overlappable {
	protected final SpriteManager spriteManager;
	public static final int ATTACK_DURATION = 20;
	protected DrawableImage shadow;
	protected SwordVisual sword;
	public static final int RENDERING_SIZE_W = (int) (24*1.35);
	public static final int RENDERING_SIZE_H = (int) (26*1.35);
	protected boolean movable = true;
	protected int attackingTimer = 0;
	private String prev = "down_static";

	public LinkVisual(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerCustom("src/ressources/img/sprite_link_v1.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 10, 8);
		spriteManager.setTypes("down", "left", "right", "up", 
				"down_static", "right_static", "left_static", "up_static");
		
		shadow = new DrawableImage("src/ressources/img/shadow.png", defaultCanvas);
		sword = new SwordVisual(defaultCanvas);
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
			if(prev.contains("static")){
				spriteType = prev;
			}
			else if(prev.equals("right") || prev.equals("left") || prev.equals("down") || prev.equals("up")){
				spriteType += prev + "_static";
			}
			else{
				spriteType = "down_static";
			}
			spriteManager.reset();
			movable = false;
		}
		
		prev = spriteType;
		spriteManager.setType(spriteType);
		
		int posX = getPosition().x;
		int posY = getPosition().y+2;
		int posXE = getPosition().x;
		int posYE = getPosition().y;
		
		if(spriteType.contains("left")){
			posXE-=20;
			posX+=2;
		}
		else if(spriteType.contains("right")){
			posX +=6;
			posXE+=20;
		}
		else{
			posX +=4;
		}
		
		if(spriteType.contains("up")){
			posYE -=20;
		}
		else if(spriteType.contains("down")){
			posYE+=20;
		}
		
		if(spriteType.contains("up"))
			posY-=2;
		
		if(isAttacking())
			
		g.drawImage(shadow.getImage(), posX, posY, RENDERING_SIZE_W, RENDERING_SIZE_H,
				null);
		
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
		linkWith.addSword();
	}

	@Override
	public void removeSword() {
		linkWith.removeSword();
	}
}
