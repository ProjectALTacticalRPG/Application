package testscode.talents;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanelDrawing extends JPanel {
	
	private Boolean drawBg;
	
	protected String panelName;
	protected int posX;
	protected int posY;
	protected int height;
	protected int width;
	
	protected Font fontHeader = new Font("Arial", Font.PLAIN, 25);
	protected Font fontSmall = new Font("Arial", Font.BOLD, 12);
	protected Font fontTTHeader = new Font("Arial", Font.BOLD, 17);
	protected Font fontTTDesc = new Font("Arial", Font.PLAIN, 12);
	protected Font fontTTReq = new Font("Arial", Font.ITALIC, 12);
	
	public PanelDrawing(String pn, int x, int y, int w, int h) {
		panelName = pn;
		posX = x;
		posY = y;
		width = w;
		height = h;
	}

	public void paintComponents(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0, 0, 0, 128));
		g2d.fillRect(posX, posY, width, height);
		g2d.setColor(new Color(255, 215, 128));
		g2d.drawRect(posX, posY, width, height);
		g2d.setColor(Color.white);
		g2d.setFont(fontHeader);
		g2d.drawString(panelName, posX+10, posY+30);
		g2d.setFont(fontSmall);
	}
}
