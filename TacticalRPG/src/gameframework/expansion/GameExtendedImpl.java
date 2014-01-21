package gameframework.expansion;

import javax.swing.JFrame;

import gameframework.game.GameDefaultImpl;

public class GameExtendedImpl extends GameDefaultImpl {

	public GameExtendedImpl() {
		super();
	}
	
	public void frameName(String name) {
		f.setTitle(name);
	}
	
	public void setFrameParametersToDefault() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
