package testDelesse;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.MoveStrategy;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.Overlappable;
import gameframework.expansion.MoveStrategyBullet;
import gameframework.expansion.MoveStrategyOctorock;
import gameframework.expansion.SpriteManagerCustom;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.SpriteManager;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Octorock extends GameMovable implements Drawable, GameEntity,
Overlappable, Runnable {
	protected final SpriteManager spriteManager;
	protected DrawableImage shadow;
	public static final int RENDERING_SIZE_W = (int) (20*1.35);
	public static final int RENDERING_SIZE_H = (int) (17*1.35);
	protected boolean movable = true;
	protected boolean vulnerable = false;
	protected int vulnerableTimer = 0;
	private String prev = "down";
	private Canvas myCanevas;
	private Bullet bullet;
	private MoveBlockerChecker moveBlockerChecker;
	private GameUniverse universe;

	public Octorock(Canvas defaultCanvas, MoveBlockerChecker moveChecker, GameUniverse gameUniverse) {
		myCanevas=defaultCanvas;
		spriteManager = new SpriteManagerCustom("src/ressources/img/sprite_octorock_v1.png",
				myCanevas, RENDERING_SIZE_W, RENDERING_SIZE_H, 4, 4);
		spriteManager.setTypes("down", "left", "right", "up");
		
		bullet=null;
		universe=gameUniverse;
		moveBlockerChecker=moveChecker;
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
		
		g.drawImage(shadow.getImage(), posX, posY, RENDERING_SIZE_W-6, RENDERING_SIZE_H,
				null);
		
		spriteManager.draw(g, getPosition());
		
		if(bullet!=null)
		{
			bullet.draw(g);
		}
			

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
	public void run() {
		
		int min =1;
		int max=2;
				
		GameMovableDriverDefaultImpl driver = null;
		MoveStrategy move = null;
		
		while(true)
		{
			try {			
				
				/*
				 * Si l'octorock n'as pas encore tiré ou que son précédent tir est détruit
				 */
				if(bullet==null) {
					
						int sec =  (int) ((min + (Math.random() * (max - min)))*1000) ;
						Thread.sleep(sec);
						//System.out.println("Lancer un projectile en direction" + getSpeedVector().getDirection());
						
						bullet = new Bullet(myCanevas);
						bullet.setPosition(this.getPosition());
						
						driver = new BulletMovableDriver();
						//move = new MoveStrategyStraightLine(bullet.getPosition(),getSpeedVector().getDirection());
			            move = new MoveStrategyBullet(bullet.getPosition(),getSpeedVector().getDirection());
			            
			            driver.setStrategy(move);
			            driver.setmoveBlockerChecker(moveBlockerChecker);
			            
						bullet.setDriver(driver);
						
						if(bullet != null)
							universe.addGameEntity(bullet);
					
						Thread.sleep(1000); //Limite la maj du thread
					}
					else
					{
						/*
						 * Si le tir touche le décors
						 */
						if(bullet.getSpeedVector().getDirection().getX()==0 && bullet.getSpeedVector().getDirection().getY()==0)
						{
							Thread.sleep(1000);
							universe.removeGameEntity(bullet);
							bullet = null;
							driver = null;
							move = null;
						}
						else
						{
							//if(universe.)
							Thread.sleep(1000); //Limite la maj du thread
						}						
					}
				}
				catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
		}
		
	}
}

