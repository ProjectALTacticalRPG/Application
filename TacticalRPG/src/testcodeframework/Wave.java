package testcodeframework;

import gameframework.base.MoveStrategy;
import gameframework.expansion.MoveStrategyKeaton;
import gameframework.expansion.MoveStrategyOctorock;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;

public class Wave {

	private String waveType;
	private int waveLength;
	private int waveStartTime;
	private boolean waveKilled;
	private Canvas canvas;
	private MoveBlockerChecker moveBlockedChecker = new MoveBlockerCheckerDefaultImpl();
	private int spriteSize;
	private GameUniverse universe;
	private Point toFollow;
	
	public Wave(String wt, int wl, int wst, Canvas c, int spSize, GameUniverse u, Point tf) {
		waveType = wt;
		waveLength = wl;
		waveStartTime = wst;
		waveKilled = false;
		canvas = c;
		spriteSize = spSize;
		universe = u;
		toFollow = tf;
	}
	
	public void initWave() {
		GameMovable ennemyType = null;
		GameMovableDriverDefaultImpl driver = null;
		MoveStrategy move = null;
		for(int t = 0; t < waveLength; ++t) {
			if(waveType.equals("octorok")) {
				ennemyType = new Octorock(canvas);
				ennemyType.setPosition(new Point(14 * spriteSize, 15 * spriteSize));
				driver = new OctorockMovableDriver();
				move = new MoveStrategyOctorock();
			}
			if(waveType.equals("keaton")) {
				ennemyType = new Keaton(canvas);
				ennemyType.setPosition(new Point(14 * spriteSize, 15 * spriteSize));
				driver = new KeatonMovableDriver();
				move = new MoveStrategyKeaton(ennemyType.getPosition(), toFollow);
			}
			driver.setStrategy(move);
			driver.setmoveBlockerChecker(moveBlockedChecker);
			//ennemyType = new Octorock(canvas);
			ennemyType.setDriver(driver);
			if(ennemyType != null)
				universe.addGameEntity((GameEntity) ennemyType);
		}
	}

	public String getWaveType() {
		return waveType;
	}

	public int getWaveLength() {
		return waveLength;
	}

	public int getWaveStartTime() {
		return waveStartTime;
	}
	
	public boolean getWaveKilled() {
		return waveKilled;
	}
	
}
