package units;

import java.awt.Point;

import utils.DeathObserver;
import gameframework.game.MoveBlockerChecker;
import arena.graphics.CocotteVisual;
import arena.graphics.KeatonVisual;
import arena.graphics.LinkVisual;
import arena.graphics.OctorockVisual;

public interface AbstractFactory {
	public OctorockVisual createOctorock(MoveBlockerChecker moveBlocker);
	public OctorockVisual createOctorock(MoveBlockerChecker moveBlocker, DeathObserver obs);
	public KeatonVisual createKeaton(MoveBlockerChecker moveBlocker, Point goal);
	public KeatonVisual createKeaton(MoveBlockerChecker moveBlocker, Point goal, DeathObserver obs);
	public CocotteVisual createCocotte(Point goal);
	public CocotteVisual createCocotte(Point goal, DeathObserver obs);
	public LinkVisual createLink();
	public LinkVisual createLink(DeathObserver obs);
	
}
