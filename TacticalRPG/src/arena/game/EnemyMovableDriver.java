package arena.game;

import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriverDefaultImpl;

public class EnemyMovableDriver extends GameMovableDriverDefaultImpl {

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		SpeedVector currentSpeedVector, possibleSpeedVector;

		currentSpeedVector = m.getSpeedVector();
		possibleSpeedVector = super.getSpeedVector(m);

		int nbTries = 10;
		while (true) {
			nbTries--;
			if ((possibleSpeedVector.getDirection().getX() == currentSpeedVector
					.getDirection().getX())
					&& (possibleSpeedVector.getDirection().getY() != -currentSpeedVector
							.getDirection().getY()))
				break;

			if ((possibleSpeedVector.getDirection().getX() != -currentSpeedVector
					.getDirection().getX())
					&& (possibleSpeedVector.getDirection().getY() == currentSpeedVector
							.getDirection().getY()))
				break;

			if ((possibleSpeedVector.getDirection().getX() != currentSpeedVector
					.getDirection().getX())
					&& (possibleSpeedVector.getDirection().getY() != currentSpeedVector
							.getDirection().getY()))
				break;

			possibleSpeedVector = super.getSpeedVector(m);
			if (nbTries < 1)
				break;
		}
		return (possibleSpeedVector);
	}
}
