package gameframework.expansion;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

public class MoveStrategyOctorock implements MoveStrategy {
	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0), 4);
	static Random random = new Random();
	private int hold = 0;
	private Point oldDirection;

	public SpeedVector getSpeedVector() {
		
		if(hold <= 0){
			
			int i = random.nextInt(5);

			switch (i) {
			case 0:
				currentMove.setDirection(new Point(1, 0));
				break;
			case 1:
				currentMove.setDirection(new Point(-1, 0));
				break;
			case 2:
				currentMove.setDirection(new Point(0, -1));
				break;
			case 3:
				currentMove.setDirection(new Point(0, 1));
				break;
			case 4:
				currentMove.setDirection(new Point(0, 0));
				break;
			}
			
			if(i==4)
				hold = 75;
			else
				hold = 4+random.nextInt(7);
			oldDirection = currentMove.getDirection();
		}
		else
		{
			--hold;
			currentMove.setDirection(oldDirection);
		}
		
		return currentMove;
	}
}
