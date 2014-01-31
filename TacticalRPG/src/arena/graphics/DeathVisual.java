package arena.graphics;

import arena.game.GeneralLevelUI;

public class DeathVisual extends Thread {

	private GeneralLevelUI levelUI;
	
	public DeathVisual(GeneralLevelUI lvlUI) {
		levelUI = lvlUI;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		levelUI.resetOpacity();
		for(int i = 0; i<9; i++) {
			try { this.sleep(100); } catch (InterruptedException e) {}
			levelUI.incrementOpacity();
		}
	}
	
}
