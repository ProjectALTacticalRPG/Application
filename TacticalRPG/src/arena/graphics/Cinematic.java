package arena.graphics;

import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;

public class Cinematic extends Thread {

	private LinkVisual link;
	private Point to;
	private Cinematicable level;
	
	public Cinematic(LinkVisual myLink, Point entree, Point arrivee, Cinematicable lvl) {
		myLink.setPosition(entree);
		level = lvl;
		link = myLink;
		to = arrivee;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(link.getPosition().y < to.y) {
			link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(0, 1), 5));
		}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(-1, 0), 0));
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(1, 0), 0));
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(-1, 0), 0));
		try {
			this.sleep(1000);
		} catch (InterruptedException e) {}
		link.setSpeedVector(new SpeedVectorDefaultImpl(new Point(0, 1), 0));
		level.launchGame();
	}

}