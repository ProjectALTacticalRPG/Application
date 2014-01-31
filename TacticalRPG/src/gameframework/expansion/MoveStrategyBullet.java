package gameframework.expansion;

import gameframework.base.MoveStrategy; 
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;


public class MoveStrategyBullet implements MoveStrategy {
	
	SpeedVector currentMove = null;
	static Random random = new Random();
	private Point goal;
	
	public MoveStrategyBullet(Point pos, Point goal) {
		this.goal = goal;
	}

	public SpeedVector getSpeedVector() {	
		
		//cas particulier si l'octorock ne se déplace pas
		if(goal.getX() == 0 && goal.getY()== 0 && currentMove == null) 
		{
			int i = random.nextInt(4);

			switch (i) {	
			case 0:
				currentMove = new SpeedVectorDefaultImpl(new Point(0,1));
			case 1:
				currentMove = new SpeedVectorDefaultImpl(new Point(0,-1));
			case 2:
				currentMove = new SpeedVectorDefaultImpl(new Point(1,0));
			case 3:
				currentMove = new SpeedVectorDefaultImpl(new Point(-1,0));
			}		
			
			return currentMove;
		}
		else if (currentMove != null)
		{
			return currentMove;
		}
		
		//Cas généraux
		return new SpeedVectorDefaultImpl(goal);
	}
}
