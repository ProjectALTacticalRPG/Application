package testscode.talents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private Image background = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/back.png"));
	
	public MainPanel() {
		Arbre a = new Arbre(635, 30, 400, 340);
		add(a);
		validate();
		Shop s = new Shop(635, 400, 400, 270);
		add(s);
		revalidate();
	}

	public void paintComponents(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(background, 0, 0, this);
	}
	
}
