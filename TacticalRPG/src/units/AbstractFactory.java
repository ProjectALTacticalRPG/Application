package units;

import java.awt.Point;

import gameframework.game.MoveBlockerChecker;
import arena.graphics.CocotteVisual;
import arena.graphics.KeatonVisual;
import arena.graphics.LinkVisual;
import arena.graphics.OctorockVisual;

public interface AbstractFactory {
	public OctorockVisual createOctorock(MoveBlockerChecker moveBlocker);
	public KeatonVisual createKeaton(MoveBlockerChecker moveBlocker, Point goal);
	public CocotteVisual createCocotte(Point goal);
	public LinkVisual createLink();
}
