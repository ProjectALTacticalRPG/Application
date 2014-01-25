package testcodeframework;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import utils.CalculateMatrix;

import gameframework.expansion.GameMovableDriverTweaked;
import gameframework.expansion.MoveStrategyKeyboardExtended;
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

public class GameLevelOne extends GameLevelDefaultImpl implements Cinematicable {
	
	Canvas canvas;
	private final static int GAME_SPEED = 50;
	public static final int SPRITE_SIZE = 16;
	private final ArrayList<Wave> waves;
	private final ArrayList<MapVisual> elementsOver;
	private ArrayList<Rectangle> spawns;
	private ArrayList<Rectangle> collisions;
	private int timerTick;
	private Link myLink;
	private MoveBlockerChecker moveBlockerChecker;

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
		waves = new ArrayList<Wave>();
		elementsOver = new ArrayList<MapVisual>();
		spawns = null;
		collisions = null;
		timerTick = 0;
		
		elementsOver.add(new MapVisual(canvas, 589, 335, 188, 88, "src/ressources/img/elementOver_3.png"));
		elementsOver.add(new MapVisual(canvas, 932, 456, 106, 31, "src/ressources/img/elementOver_2.png"));
		elementsOver.add(new MapVisual(canvas, 911, 315, 152, 58, "src/ressources/img/elementOver_1.png"));
		elementsOver.add(new MapVisual(canvas, 845, 596, 110, 23, "src/ressources/img/elementOver_4.png"));
		elementsOver.add(new MapVisual(canvas, 152, 371, 108, 26, "src/ressources/img/elementOver_5.png"));
	}

	@Override
	protected void init() {
		
		CalculateMatrix cm = new CalculateMatrix();
		cm.calculateMatrix("src/ressources/img/collisions.png");
		collisions = cm.getCollisions();
		spawns = cm.getSpawns();
		
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapProcessor.setOverlapRules(new OverlapRulesApplierDefaultImpl() {
			@Override
			public void setUniverse(GameUniverse universe) {}
		});
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		universe.addGameEntity(new MapVisual(canvas, 0, 0, "src/ressources/img/background_arena_1.gif"));
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		for(Rectangle r : collisions){
			universe.addGameEntity(new MapAsset(canvas, r.x, r.y, r.width, r.height, ""));
		}
		
		myLink = new Link(canvas);
		universe.addGameEntity(myLink);
		Cinematic cine = new Cinematic(myLink, new Point(667, 3*SPRITE_SIZE), new Point(667, 17*SPRITE_SIZE), this);
		cine.start();
		refreshElements();
		waves.add(new Wave("octorok", 10, 3, canvas, 30, universe, myLink.getPosition(), moveBlockerChecker));
		waves.add(new Wave("keaton", 10, 15, canvas, 30, universe, myLink.getPosition(), moveBlockerChecker));	
	}
	
	public void launchGame() {
		GameMovableDriverDefaultImpl linkDriver = new GameMovableDriverTweaked();
		MoveStrategyKeyboardExtended keyStr = new MoveStrategyKeyboardExtended();
		linkDriver.setStrategy(keyStr);
		linkDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myLink.setDriver(linkDriver);
		launchWaves();
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
	
	public void launchWaves() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				timerTick++;
				for(Wave w:waves) {
					if(w.getWaveStartTime()==timerTick) {
						w.initWave(spawns);
						refreshElements();
					}
				}
			}
		}, 0, 1000);
	}
	
	public void refreshElements(){
		for(MapVisual mv : elementsOver){
			universe.removeGameEntity(mv);
			universe.addGameEntity(mv);
		}
	}

}
