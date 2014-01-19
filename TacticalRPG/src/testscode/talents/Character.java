package testscode.talents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Character extends PanelDrawing {
	
	private double exp_max;
	private double exp_get;
	private int level;
	
	public Character(int x, int y, int w, int h) {
		super("Link", x, y, w, h);
		exp_get = 542.0;
		exp_max = 1000.0;
		level = 20;
	}
	
	public int calcPercent() {
		return (int) Math.round(exp_get / exp_max * 100);
	}
	
	public int calcWidth() {
		return (int) Math.round(calcPercent() * width / 100 - 24);
	}
	
	public int roundToInt(double v) {
		return (int) Math.round(v);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(new Color(76, 166, 107));
		g2d.fillRect(posX+12, posY+65, calcWidth(), 30);
		g2d.setColor(gold);
		g2d.drawRect(posX+12, posY+65, width-24, 30);
		g2d.setColor(Color.WHITE);
		g2d.setFont(fontSmall);
		g2d.drawString("Niveau : " + level, posX+12, posY+50);
		g2d.setFont(fontSmallItalic);
		g2d.drawString("Expérience : " + roundToInt(exp_get) + "/" + roundToInt(exp_max) + " (" + calcPercent() + "%)", posX+22, posY+85);
	}
	
}
