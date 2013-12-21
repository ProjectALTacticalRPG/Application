package gameframework.expansion;

import javax.swing.JFrame;

import gameframework.game.GameDefaultImpl;

public class UIExtended extends GameDefaultImpl {

	public UIExtended() {
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
