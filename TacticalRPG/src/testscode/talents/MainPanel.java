package testscode.talents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private Image background = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/back.png"));
	private ArrayList<PanelDrawing> elements;
	
	public MainPanel() {
		elements = new ArrayList<PanelDrawing>();
		elements.add(new Character(30, 30, 575, 110));
		elements.add(new Arbre(635, 30, 400, 340));
		elements.add(new Shop(635, 400, 400, 270));
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(background, 0, 0, this);
		for(PanelDrawing p:elements)
			p.paintComponent(g2d);
	}
	
}
