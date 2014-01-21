package testcodeframework;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Date;


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

public class GameLevelOne extends GameLevelDefaultImpl {
	
	Canvas canvas;
	private final static int GAME_SPEED = 40;
	
	static int[][] tab = { 
		    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static final int SPRITE_SIZE = 16;

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapProcessor.setOverlapRules(new OverlapRulesApplierDefaultImpl() {
			
			@Override
			public void setUniverse(GameUniverse universe) {
				
			}
		});
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl)gameBoard).setBackground("src/ressources/img/middle-finger-guy-gif.gif");
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		for (int i = 0; i < 31; ++i) {
			for (int j = 0; j < 28; ++j) {
				if (tab[i][j] == 0) {
					//universe.addGameEntity(new MapAsset(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE, "src/ressources/img/dirt_16.png"));
				}
				if (tab[i][j] == 1) {
					universe.addGameEntity(new MapAsset(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE, "src/ressources/img/wall.png"));
				}
			}
		}
		
		Link myLink = new Link(canvas);
		GameMovableDriverDefaultImpl linkDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboardExtended keyStr = new MoveStrategyKeyboardExtended();
		linkDriver.setStrategy(keyStr);
		linkDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myLink.setDriver(linkDriver);
		myLink.setPosition(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE));
		universe.addGameEntity(myLink);
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
