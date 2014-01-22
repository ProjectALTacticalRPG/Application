package gameframework.expansion;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

public class MoveStrategyKeaton implements MoveStrategy {
	SpeedVector move = new SpeedVectorDefaultImpl(new Point(0,
			0), 4);
	Point goal, currentPosition;

	public MoveStrategyKeaton(Point pos, Point goal) {
		this.goal = goal;
		this.currentPosition = pos;
	}

	public SpeedVector getSpeedVector() {
		double dist = currentPosition.distance(goal);
		int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX())
				/ dist);
		int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY())
				/ dist);
		move.setDirection(new Point(xDirection,
				yDirection));
		return move;
	}
	
	public void setGoal(Point goal) {
		this.goal = goal;
	}
}
