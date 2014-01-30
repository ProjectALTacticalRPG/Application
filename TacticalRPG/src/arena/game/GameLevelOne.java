package arena.game;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import arena.graphics.Cinematicable;
import arena.graphics.LinkVisual;
import arena.graphics.LinkedEntity;
import arena.graphics.MapAsset;
import arena.graphics.MapVisual;
import units.AbstractFactory;
import units.FactoryImpl;
import units.Wave;
import units.WaveMember;
import utils.CalculateMatrix;
import utils.DeathObserver;
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

public class GameLevelOne extends GameLevelDefaultImpl implements Cinematicable, DeathObserver {
	
	Canvas canvas;
	private final static int GAME_SPEED = 50;
	public static final int SPRITE_SIZE = 16;
	private final ArrayList<Wave> waves;
	private final ArrayList<MapVisual> elementsOver;
	private ArrayList<Rectangle> spawns;
	private ArrayList<Rectangle> collisions;
	private int timerTick;
	private LinkVisual myLink;
	private MoveBlockerChecker moveBlockerChecker;
	private GeneralLevelUI levelUI;
	private AbstractFactory factory;
	private AudioRead audioReader;

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
		waves = new ArrayList<Wave>();
		elementsOver = new ArrayList<MapVisual>();
		spawns = null;
		collisions = null;
		timerTick = 0;
		factory = new FactoryImpl(canvas);
		elementsOver.add(new MapVisual(canvas, 589, 335, 188, 88, "src/ressources/img/elementOver_3.png"));
		elementsOver.add(new MapVisual(canvas, 932, 456, 106, 31, "src/ressources/img/elementOver_2.png"));
		elementsOver.add(new MapVisual(canvas, 911, 315, 152, 58, "src/ressources/img/elementOver_1.png"));
		elementsOver.add(new MapVisual(canvas, 845, 596, 110, 23, "src/ressources/img/elementOver_4.png"));
		elementsOver.add(new MapVisual(canvas, 152, 371, 108, 26, "src/ressources/img/elementOver_5.png"));
		audioReader = new AudioRead();
	}

	@Override
	protected void init() {
		
		CalculateMatrix cm = new CalculateMatrix();
		cm.calculateMatrix("src/ressources/img/collisions.png");
		collisions = cm.getCollisions();
		spawns = cm.getSpawns();
		
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		ArenaOverlapRules overlapRules = new ArenaOverlapRules();
		overlapProcessor.setOverlapRules(overlapRules);
		moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);
		overlapRules.setMoveBlockerChecker(moveBlockerChecker);
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		universe.addGameEntity(new MapVisual(canvas, 0, 0, "src/ressources/img/background_arena_1.gif"));
		
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		for(Rectangle r : collisions){
			universe.addGameEntity(new MapAsset(canvas, r.x, r.y, r.width, r.height, ""));
		}
		
		myLink = factory.createLink();
		levelUI = new GeneralLevelUI(myLink);
		universe.addGameEntity(myLink);
	    myLink.setPosition(new Point(667, 17*SPRITE_SIZE));
	    myLink.addSword();
		launchGame();
		/*Cinematic cine = new Cinematic(myLink, new Point(667, 3*SPRITE_SIZE), new Point(667, 17*SPRITE_SIZE), this);
		cine.start();*/
		refreshElements();
		
		//Ajout d'une vague d'octorocks
		ArrayList<WaveMember> enemies = new ArrayList<WaveMember>();
		for(int i = 0; i < 5; ++i){
			enemies.add(factory.createOctorock(moveBlockerChecker, this));
		}
		waves.add(new Wave(enemies, 3, 30, universe));
		enemies.clear();
		
		//Ajout d'une vague de keatons
		for(int i = 0; i < 5; ++i){
			enemies.add(factory.createKeaton(moveBlockerChecker, myLink.getPosition(), this));
		}
		waves.add(new Wave(enemies, 15, 30, universe));
		enemies.clear();
		
		Wave first = getNextWave(0);
		levelUI.updateTimer(0, first);
	}
	
	public Wave getNextWave(int i) {
		Wave tmp = null;
		for(Wave w:waves) {
			if(tmp == null) {
				if(w.getWaveStartTime()>i)
					tmp = w;
			} else {
				if(w.getWaveStartTime()>i && w.getWaveStartTime()<tmp.getWaveStartTime())
					tmp = w;
			}
		}
		return tmp;
	}
	
	public void launchGame() {
		GameMovableDriverDefaultImpl linkDriver = new GameMovableDriverTweaked();
		MoveStrategyKeyboardExtended keyStr = new MoveStrategyKeyboardExtended(this, myLink);
		linkDriver.setStrategy(keyStr);
		linkDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myLink.setDriver(linkDriver);
		launchWaves();
		//audioReader.getPlaylistElement(0).loop();
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
			if(myLink.isAttacking())
				myLink.decrementAttackTimer();
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
				levelUI.updateTimer(timerTick, getNextWave(timerTick));
				for(Wave w:waves) {
					if(w.getWaveStartTime()==timerTick) {
						w.initWave(spawns);
						refreshElements();
					}
				}
			}
		}, 0, 1000);
	}
	
	@Override
	public void addCocotteWaveKonami() {
		ArrayList<WaveMember> enemies = new ArrayList<WaveMember>();
		for(int i = 0; i < 20; ++i){
			enemies.add(factory.createCocotte(myLink.getPosition()));
		}
		waves.add(new Wave(enemies, timerTick+1, 30, universe));
		enemies.clear();
	}
	
	public void refreshElements() {
		for(MapVisual mv : elementsOver){
			universe.removeGameEntity(mv);
			universe.addGameEntity(mv);
		}
		universe.removeGameEntity(levelUI);
		universe.addGameEntity(levelUI);
	}

	@Override
	public void update(LinkedEntity l) {
		if(!l.isAlive()){
			System.out.println(l.getClass().getSimpleName() + " is dead.");
			universe.removeGameEntity(l);
		}
	}

}
