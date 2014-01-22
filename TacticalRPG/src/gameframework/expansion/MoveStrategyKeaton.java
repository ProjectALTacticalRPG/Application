package gameframework.expansion;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

public class MoveStrategyKeaton implements MoveStrategy {
	SpeedVector move = new SpeedVectorDefaultImpl(new Point(0,0), 4);
	static Random random = new Random();
	private int hold = 0;
	private Point oldDirection;
	private double distanceAggro = 250;
	private double distanceDesaggro = 400;
	private boolean inAggro = false;
	Point goal, currentPosition;

	public MoveStrategyKeaton(Point pos, Point goal) {
		this.goal = goal;
		this.currentPosition = pos;
	}

	public SpeedVector getSpeedVector() {
		double dist = currentPosition.distance(goal);
		double distAgg;
		if(inAggro) {
			distAgg = distanceDesaggro;
		} else {
			distAgg = distanceAggro;
		}
		if(dist > distAgg) {
			inAggro = false;
			if(hold <= 0){		
				int i = random.nextInt(6);
				switch (i) {
				case 0:
					move.setDirection(new Point(1, 0));
					break;
				case 1:
					move.setDirection(new Point(-1, 0));
					break;
				case 2:
					move.setDirection(new Point(0, -1));
					break;
				case 3:
					move.setDirection(new Point(0, 1));
					break;
				case 4:
					move.setDirection(new Point(0, 0));
					break;
				}				
				if(i==4)
					hold = 75;
				else
					hold = 4+random.nextInt(7);
				oldDirection = move.getDirection();
			} else 	{
				--hold;
				move.setDirection(oldDirection);
			}
		} else {
			inAggro = true;
			int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX()) / dist);
			int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY()) / dist);
			move.setDirection(new Point(xDirection, yDirection));
		}
		return move;
	}
	
	public void setGoal(Point goal) {
		this.goal = goal;
	}
}
