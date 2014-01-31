package units;

import gameframework.base.MoveStrategy;
import gameframework.expansion.MoveStrategyCocotte;
import gameframework.expansion.MoveStrategyKeaton;
import gameframework.expansion.MoveStrategyOctorock;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.MoveBlockerChecker;

import java.awt.Canvas;
import java.awt.Point;

import utils.DeathObserver;
import arena.game.EnemyMovableDriver;
import arena.graphics.CocotteVisual;
import arena.graphics.KeatonVisual;
import arena.graphics.LinkVisual;
import arena.graphics.OctorockVisual;

public class FactoryImpl implements AbstractFactory {

	private Canvas canvas;
	public FactoryImpl(Canvas canvas){
		this.canvas = canvas;
	}
	
	@Override
	public OctorockVisual createOctorock(MoveBlockerChecker moveBlocker) {
		OctorockVisual res = new OctorockVisual(canvas);
		OctorockProxy octo = new OctorockProxy();
		res.setFighterProxy(octo);
		res.setPosition(new Point(0, 0));
		GameMovableDriverDefaultImpl driver = new EnemyMovableDriver();
		MoveStrategy move = new MoveStrategyOctorock();
		driver.setStrategy(move);
		driver.setmoveBlockerChecker(moveBlocker);
		res.setDriver(driver);
		return res;
	}

	@Override
	public KeatonVisual createKeaton(MoveBlockerChecker moveBlocker, Point goal) {
		KeatonVisual res = new KeatonVisual(canvas);
		KeatonProxy keat = new KeatonProxy();
		res.setFighterProxy(keat);
		res.setPosition(new Point(0, 0));
		GameMovableDriverDefaultImpl driver = new EnemyMovableDriver();
		MoveStrategy move = new MoveStrategyKeaton(res.getPosition(), goal);
		driver.setStrategy(move);
		driver.setmoveBlockerChecker(moveBlocker);
		res.setDriver(driver);
		return res;
	}

	@Override
	public LinkVisual createLink() {
		LinkVisual res = new LinkVisual(canvas);
		LinkProxy link = new LinkProxy();
		res.setFighterProxy(link);
		res.setPosition(new Point(0, 0));
		return res;
	}

	@Override
	public CocotteVisual createCocotte(Point goal) {
		CocotteVisual res = new CocotteVisual(canvas);
		res.setPosition(new Point(0, 0));
		GameMovableDriverDefaultImpl driver = new EnemyMovableDriver();
		MoveStrategy move = new MoveStrategyCocotte(res.getPosition(), goal);
		driver.setStrategy(move);
		res.setDriver(driver);
		return res;
	}

	@Override
	public OctorockVisual createOctorock(MoveBlockerChecker moveBlocker,
			DeathObserver obs) {
		OctorockVisual res = createOctorock(moveBlocker);
		res.addObserver(obs);
		return res;
	}

	@Override
	public KeatonVisual createKeaton(MoveBlockerChecker moveBlocker,
			Point goal, DeathObserver obs) {
		KeatonVisual res = createKeaton(moveBlocker, goal);
		res.addObserver(obs);
		return res;
	}

	@Override
	public CocotteVisual createCocotte(Point goal, DeathObserver obs) {
		CocotteVisual res = createCocotte(goal);
		res.addObserver(obs);
		return res;
	}

	@Override
	public LinkVisual createLink(DeathObserver obs) {
		LinkVisual res = createLink();
		res.addObserver(obs);
		return res;
	}

}
