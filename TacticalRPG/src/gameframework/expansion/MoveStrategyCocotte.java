package gameframework.expansion;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

public class MoveStrategyCocotte implements MoveStrategy {
	private SpeedVector move = new SpeedVectorDefaultImpl(new Point(0,0), 4);
	private static Random random = new Random();
	private int hold = 0;
	private Point goal, currentPosition;
	private double dist;

	public MoveStrategyCocotte(Point pos, Point goal) {
		this.goal = goal;
		this.currentPosition = pos;
		dist = currentPosition.distance(goal);
		int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX()) / dist);
		int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY()) / dist);
		move.setDirection(new Point(xDirection, yDirection));
	}

	public SpeedVector getSpeedVector() {
		return move;
	}
	
	public void setGoal(Point goal) {
		this.goal = goal;
	}
}
