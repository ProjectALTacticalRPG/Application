package arena;
import java.util.ArrayList;

import arena.game.GameLevelOne;
import gameframework.expansion.*;
import gameframework.game.GameLevel;

public class Main {

	public static void main(String[] args) {
		GameExtendedImpl g = new GameExtendedImpl(1366, 768);
		g.setFrameParametersToDefault();
		g.frameName("The Legend Of Zelda : Arena");
		
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelOne(g));
		g.setFrameParametersToDefault();
		g.setLevels(levels);
		g.start();
	}
}
