package arena.graphics;

import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;

import arena.game.GeneralLevelUI;

public class Cinematic extends Thread {

	private LinkVisual link;
	private Point to;
	private Cinematicable level;
	private GeneralLevelUI levelUI;
	
	public Cinematic(LinkVisual myLink, Point entree, Point arrivee, Cinematicable lvl, GeneralLevelUI lvlUI) {
		myLink.setPosition(entree);
		level = lvl;
		levelUI = lvlUI;
		link = myLink;
		to = arrivee;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(link.getPosition().y < to.y) {
			link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(0, 1), 5));
		}
		for(int i = 0; i<9; i++) {
			try { this.sleep(100); } catch (InterruptedException e) {}
			levelUI.incrementOpacity();
		}
		try { this.sleep(2000); } catch (InterruptedException e) {}
		for(int i = 0; i<9; i++) {
			try { this.sleep(100); } catch (InterruptedException e) {}
			levelUI.decrementOpacity();
		}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(-1, 0), 0));
		try { this.sleep(1000); } catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(1, 0), 0));
		try { this.sleep(1000); } catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(-1, 0), 0));
		try { this.sleep(1000);	} catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(0, 1), 0));
		level.launchGame();
	}

}
