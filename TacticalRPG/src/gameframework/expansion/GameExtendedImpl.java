package gameframework.expansion;

import javax.swing.JFrame;

import gameframework.game.GameDefaultImpl;

public class GameExtendedImpl extends GameDefaultImpl {

	private int resolutionW;
	private int resolutionH;
	public GameExtendedImpl(int resolutionW, int resolutionH) {
		super();
		this.resolutionW = resolutionW;
		this.resolutionH = resolutionH;
	}
	
	public void frameName(String name) {
		f.setTitle(name);
	}
	
	
	
	public void setFrameParametersToDefault() {
		defaultCanvas.setSize(resolutionW, resolutionH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
