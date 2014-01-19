package testscode.talents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shop extends PanelDrawing {
	
	private int cash;
	
	public Shop(int x, int y, int w, int h) {
		super("Boutique", x, y, w, h);
		cash = 527;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.white);
		g2d.setFont(fontSmall);
		g2d.drawString("Fonds disponibles : " + cash + " rubis", posX+12, posY+50);
	}
	
}
