package gameframework.game;

import gameframework.base.IntersectTools;
import gameframework.base.Movable;
import gameframework.base.SpeedVector;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MoveBlockerCheckerDefaultImpl implements MoveBlockerChecker {
	private ConcurrentLinkedQueue<MoveBlocker> moveBlockers;
	private MoveBlockerRulesApplier moveBlockerRuleApplier;

	public MoveBlockerCheckerDefaultImpl() {
		moveBlockers = new ConcurrentLinkedQueue<MoveBlocker>();
		this.moveBlockerRuleApplier = new MoveBlockerRulesApplierDefaultImpl();
	}

	public void addMoveBlocker(MoveBlocker p) {
		moveBlockers.add(p);
	}

	public void removeMoveBlocker(MoveBlocker p) {
		moveBlockers.remove(p);
	}

	public void setMoveBlockerRules(MoveBlockerRulesApplier moveBlockerRules) {
		this.moveBlockerRuleApplier = moveBlockerRules;
	}

	/* Suppression des tests sur les Area car, en plus de ne pas en voir l'utilité, 
	 * ces tests provoquent des bugs de collisions avec les déplacements en diagonale
	 */
	public boolean moveValidation(Movable m, SpeedVector mov) {
		Shape intersectShape = IntersectTools.getIntersectShape(m, mov);
		Vector<MoveBlocker> moveBlockersInIntersection = new Vector<MoveBlocker>();
		//Area intersectArea = new Area(intersectShape);
		Rectangle tmpIntersec = (intersectShape.getBounds());

		for (MoveBlocker moveBlocker : moveBlockers) {
			Rectangle tmpB = moveBlocker.getBoundingBox();
			if (tmpIntersec.intersects(tmpB)) {
				moveBlockersInIntersection.add(moveBlocker);/*
				Area tmpArea = new Area(tmpB);
				tmpArea.intersect(intersectArea);
				if (!tmpArea.isEmpty()) {
					moveBlockersInIntersection.add(moveBlocker);
				}*/
			}
		}

		if (!moveBlockersInIntersection.isEmpty()) {
			return moveBlockerRuleApplier.moveValidationProcessing(
					moveBlockersInIntersection, m);
		}

		return true;
	}
}
