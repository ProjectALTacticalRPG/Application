package gameframework.expansion;

import gameframework.base.MoveStrategy; 
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;

public class MoveStrategyCocotte implements MoveStrategy {
	private SpeedVector move = new SpeedVectorDefaultImpl(new Point(0,0), 4);
	private Point currentPosition;
	private Point goal;
	private double dist;

	public MoveStrategyCocotte(Point pos, Point goal) {
		this.currentPosition = pos;
		this.goal = goal;
	}

	public SpeedVector getSpeedVector() {
		dist = currentPosition.distance(goal);
		int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX()) / dist);
		int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY()) / dist);
		move.setDirection(new Point(xDirection, yDirection));
		return move;
	}
}
