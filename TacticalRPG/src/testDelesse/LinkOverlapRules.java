package testDelesse;

import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.Point;
import java.util.Vector;

public class LinkOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected Vector<Bullet> vBullet = new Vector<Bullet>();

	static final int INVULNERABLE_DURATION = 60;
	protected Point linkStartPos;
	protected Point bulletStartPos;
	protected boolean manageLinkDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	private int totalNbBullet = 0;

	public LinkOverlapRules(Point linkPos, Point bPos,
			ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		linkStartPos = (Point) linkPos.clone();
		bulletStartPos = (Point) bPos.clone();
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
	}

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void setTotalNbBullet(int totalNbBullet) {
		this.totalNbBullet = totalNbBullet;
	}

	public void addBullet(Bullet b) {
		vBullet.addElement(b);
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		manageLinkDeath = true;
		super.applyOverlapRules(overlappables);
	}

	public void overlapRule(Link l, Bullet b) {
		
		System.out.println("intersection entre link et un bullet");
		
		if (!l.isVulnerable()) {
			System.out.println("LINK EST INVULNERABLE");
		} 
		else 
		{
			if (manageLinkDeath) {
					
				System.out.println("LINK EST MORTEL");
					
				universe.removeGameEntity(b);
				
				
				manageLinkDeath = false;
			}
		}
	}
}
