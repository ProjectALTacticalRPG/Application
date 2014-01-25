package gameframework.expansion;

import java.awt.Point;

import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;


/* On a modifié pour permettre au personnage de passer d'un mouvement diagonale 
 * à un mouvement horizontal/vertical si le premier est impossible
 */ 
public class GameMovableDriverTweaked extends GameMovableDriverDefaultImpl {

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		SpeedVector possibleSpeedVector;

		possibleSpeedVector = moveStrategy.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}
		else {
			possibleSpeedVector.setDirection(new Point(possibleSpeedVector.getDirection().x, 0));
			if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
				return possibleSpeedVector;
			}else{
				possibleSpeedVector.setDirection(new Point(0, possibleSpeedVector.getDirection().y));
				if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
					return possibleSpeedVector;
				}
			}
		}

		// If the strategy did not provide a valid vector, try to keep the
		// current vector.
		possibleSpeedVector = m.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}

		return SpeedVectorDefaultImpl.createNullVector();
	}
}
