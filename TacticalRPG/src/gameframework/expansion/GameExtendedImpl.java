package gameframework.expansion;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JFrame;

import gameframework.game.CanvasDefaultImpl;
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
	
	@Override
	public void createGUI() {
		f = new JFrame("Default Game");
		f.dispose();
		lifeValue = new Label();
		scoreValue = new Label();
		currentLevelValue = new Label();
		defaultCanvas = new CanvasDefaultImpl();
		defaultCanvas.setSize(SPRITE_SIZE * NB_COLUMNS, SPRITE_SIZE * NB_ROWS);
		f.add(defaultCanvas);
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	public void setFrameParametersToDefault() {
		defaultCanvas.setSize(resolutionW, resolutionH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
