package testscode.talents;

import javax.swing.JFrame;

public class Window {

	private JFrame f;
	
	public Window() {
		f = new JFrame();
		f.setTitle("Test Arbo Talents");
		f.setSize(1082, 738);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new MainPanel());
		f.setVisible(true);
	}
	
}
