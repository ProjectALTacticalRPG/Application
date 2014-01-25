package testFrameworkDelesse;

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
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Wave {

	private String waveType;
	private int waveLength;
	private int waveStartTime;
	private boolean waveKilled;
	private Canvas canvas;
	private MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
	private int spriteSize;
	private GameUniverse universe;
	private Point toFollow;
	
	public Wave(String wt, int wl, int wst, Canvas c, int spSize, GameUniverse u, Point tf, MoveBlockerChecker mc) {
		waveType = wt;
		waveLength = wl;
		waveStartTime = wst;
		waveKilled = false;
		canvas = c;
		spriteSize = spSize;
		universe = u;
		toFollow = tf;
		moveBlockerChecker = mc;
	}
	
	public void initWave(ArrayList<Rectangle> spawns) {
		GameMovable ennemyType = null;
		GameMovableDriverDefaultImpl driver = null;
		MoveStrategy move = null;
		for(int t = 0; t < waveLength; ++t) {
			if(waveType.equals("octorok")) {
				ennemyType = new Octorock(canvas);
				ennemyType.setPosition(randomSpawn(spawns));
				driver = new OctorockMovableDriver();
				move = new MoveStrategyOctorock();
			}
			if(waveType.equals("keaton")) {
				ennemyType = new Keaton(canvas);
				ennemyType.setPosition(randomSpawn(spawns));
				driver = new KeatonMovableDriver();
				move = new MoveStrategyKeaton(ennemyType.getPosition(), toFollow);
			}
			driver.setStrategy(move);
			driver.setmoveBlockerChecker(moveBlockerChecker);
			//ennemyType = new Octorock(canvas);
			ennemyType.setDriver(driver);
			if(ennemyType != null)
				universe.addGameEntity((GameEntity) ennemyType);
		}
	}

	private Point randomSpawn(ArrayList<Rectangle> spawns) {
		Random rdm = new Random();
		int i = rdm.nextInt(spawns.size());
		Rectangle r = spawns.get(i);
		int x = 0, y = 0;
		x = r.x+rdm.nextInt(r.width)-spriteSize;
		y = r.y+rdm.nextInt(r.height)-spriteSize;
		return new Point(x, y);
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
