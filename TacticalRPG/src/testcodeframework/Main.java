package testcodeframework;

import gameframework.expansion.GameExtendedImpl;
import gameframework.game.GameLevel;

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameExtendedImpl g = new GameExtendedImpl(1280, 720);
		g.setFrameParametersToDefault();
		g.frameName("The Legend Of Zelda : Arena");
		
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelOne(g));
		g.setFrameParametersToDefault();
		g.setLevels(levels);
		g.start();
		
	}

}
