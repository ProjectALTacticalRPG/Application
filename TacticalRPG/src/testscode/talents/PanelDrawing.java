package testscode.talents;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.FileInputStream;

import javax.swing.JPanel;

public class PanelDrawing extends JPanel {
	
	private Boolean drawBg;
	
	protected String panelName;
	protected int posX;
	protected int posY;
	protected int height;
	protected int width;
	
	protected Font fontHeader;
	protected Font fontSmall = new Font("Arial", Font.BOLD, 12);
	protected Font fontExp;
	protected Font fontSmallItalic = new Font("Arial", Font.ITALIC, 12);
	protected Font fontTTHeader = new Font("Arial", Font.BOLD, 17);
	protected Font fontTTDesc = new Font("Arial", Font.PLAIN, 12);
	protected Font fontTTReq = new Font("Arial", Font.ITALIC, 12);
	protected Color gold = new Color(255, 215, 0);
	
	public PanelDrawing(String pn, int x, int y, int w, int h) {
		try {
			fontHeader = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,23F);
			fontExp = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/arcapedi.ttf")).deriveFont(Font.PLAIN,13F);
		} catch(Exception e) {
			fontHeader = new Font("Arial", Font.BOLD, 25);
		}
		panelName = pn;
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(new Color(0, 0, 0, 128));
		g2d.fillRect(posX, posY, width, height);
		g2d.setColor(new Color(255, 215, 128));
		g2d.drawRect(posX, posY, width, height);
		g2d.setFont(fontHeader);
		g2d.setColor(Color.BLACK);
		g2d.drawString(panelName, posX+9, posY+31);
		g2d.setColor(Color.WHITE);
		g2d.drawString(panelName, posX+10, posY+30);
		g2d.setFont(fontSmall);
	}
}
