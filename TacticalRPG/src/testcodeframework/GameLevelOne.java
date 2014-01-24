package testcodeframework;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import utils.CalculateMatrix;

import gameframework.expansion.MoveStrategyKeaton;
import gameframework.expansion.MoveStrategyKeyboardExtended;
import gameframework.expansion.MoveStrategyOctorock;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class GameLevelOne extends GameLevelDefaultImpl {
	
	Canvas canvas;
	private final static int GAME_SPEED = 50;
	public static final int SPRITE_SIZE = 16;
	private final ArrayList<Wave> waves;
	private int timerTick;

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
		waves = new ArrayList<Wave>();
		timerTick = 0;
	}

	@Override
	protected void init() {
		
		CalculateMatrix cm = new CalculateMatrix();
		ArrayList<Rectangle> collisions = cm.calculateMatrix("src/ressources/img/collisions.png");
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapProcessor.setOverlapRules(new OverlapRulesApplierDefaultImpl() {
			@Override
			public void setUniverse(GameUniverse universe) {}
		});
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		universe.addGameEntity(new MapVisual(canvas, 0, 0, "src/ressources/img/background_arena_1.gif"));
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		for(int i = 0; i < collisions.size(); ++i){
			Rectangle r = collisions.get(i);
			universe.addGameEntity(new MapAsset(canvas, r.x, r.y, r.width+1, r.height+1, ""));
		}
		
		Link myLink = new Link(canvas);
		GameMovableDriverDefaultImpl linkDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboardExtended keyStr = new MoveStrategyKeyboardExtended();
		linkDriver.setStrategy(keyStr);
		linkDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myLink.setDriver(linkDriver);
		myLink.setPosition(new Point(39*SPRITE_SIZE, 17*SPRITE_SIZE));
		universe.addGameEntity(myLink);

		waves.add(new Wave("octorok", 10, 5, canvas, SPRITE_SIZE, universe, myLink.getPosition(), moveBlockerChecker));
		waves.add(new Wave("keaton", 10, 15, canvas, SPRITE_SIZE, universe, myLink.getPosition(), moveBlockerChecker));
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				timerTick++;
				for(Wave w:waves) {
					if(w.getWaveStartTime()==timerTick) {
						w.initWave();
					}
				}
			}
		}, 0, 1000);
	}
	
	@Override
	public void run() {
		stopGameLoop = false;
		// main game loop :
		long start;
		while (!stopGameLoop && !this.isInterrupted()) {
			start = new Date().getTime();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
			try {
				long sleepTime = GAME_SPEED
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
			}
		}
	}

}
