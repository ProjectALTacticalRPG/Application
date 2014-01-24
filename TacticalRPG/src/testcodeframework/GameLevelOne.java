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

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
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
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
			}
		}, 0, 1000);
		
		Wave w = new Wave("octorok", 5, 5, canvas, SPRITE_SIZE, universe, myLink.getPosition(), moveBlockerChecker);
		w.initWave();
		
		Keaton myKeaton;
		for (int t = 0; t < 2; ++t) {
			GameMovableDriverDefaultImpl keatonDriv = new KeatonMovableDriver();
			myKeaton = new Keaton(canvas);
			myKeaton.setDriver(keatonDriv);
			myKeaton.setPosition(new Point((10 + new Random().nextInt(14)) * SPRITE_SIZE, (10 + new Random().nextInt(14)) * SPRITE_SIZE));
			universe.addGameEntity(myKeaton);
			MoveStrategyKeaton ranStr = new MoveStrategyKeaton(myKeaton.getPosition(), myLink.getPosition());
			keatonDriv.setStrategy(ranStr);
			keatonDriv.setmoveBlockerChecker(moveBlockerChecker);
		}
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
