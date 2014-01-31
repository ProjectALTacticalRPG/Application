package arena.graphics;

import gameframework.base.DrawableImage;
import gameframework.base.MoveStrategyDefaultImpl;
import gameframework.expansion.MoveStrategyBullet;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class BulletVisual extends ProjectileVisual {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE_W = (int) (15*1.35);
	public static final int RENDERING_SIZE_H = (int) (15*1.35);
	public static final int EXPLODING_TIME = 10;
	private int explosion = EXPLODING_TIME;
	private boolean exploded = false;
	private boolean hit = false;
	private LinkedEntity owner;

	public BulletVisual(Canvas defaultCanvas, LinkedEntity owner) {
		spriteManager = new SpriteManagerCustom("ressources/img/fire.png",
				defaultCanvas, RENDERING_SIZE_W, RENDERING_SIZE_H, 1, 2);
		spriteManager.setTypes("fire","explose");
		prev="fire";
		shadow = new DrawableImage("ressources/img/shadow.png", defaultCanvas);
		this.owner = owner;
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
		g.drawImage(shadow.getImage(), getPosition().x+4, getPosition().y+5, RENDERING_SIZE_W-8, RENDERING_SIZE_H,
				null);
		spriteManager.draw(g, getPosition());
		
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
		if(explosion <= 0 && !exploded){
			exploded = true;
		}
		
		if(hit || (getSpeedVector().getDirection().getX()==0 && getSpeedVector().getDirection().getY()==0)){
			--explosion;
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(4, 4, 10, 10));
	}

	@Override
	public boolean isStopped(){
		return exploded;
	}

	@Override
	public LinkedEntity getOwner() {
		return owner;
	}
	
	public void hasHit(){
		hit = true;
		GameMovableDriverDefaultImpl driver = new GameMovableDriverDefaultImpl();
		//move = new MoveStrategyStraightLine(bullet.getPosition(),getSpeedVector().getDirection());
        
        
        driver.setStrategy(new MoveStrategyDefaultImpl());
		this.setDriver(driver);
	}
}

