package arena.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import arena.graphics.LinkVisual;

import units.Wave;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

public class GeneralLevelUI implements Drawable, GameEntity {

	private Font font1;
	private Font font2;
	private Image rupee;
	private Image heart;
	private Image imgLink;
	private int timerTick;
	private Wave nextWave;
	private boolean firstUpdate;
	private LinkVisual link;
	
	public GeneralLevelUI(LinkVisual link) {
		timerTick = 0;
		nextWave = null;
		this.link = link;
		firstUpdate = false;
		try {
			font1 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,23F);
			font2 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/arcapedi.ttf")).deriveFont(Font.BOLD,20F);
			rupee = Toolkit.getDefaultToolkit().getImage("src/ressources/img/rupee.png");
			heart = Toolkit.getDefaultToolkit().getImage("src/ressources/img/ui_hearts.png");
			imgLink = Toolkit.getDefaultToolkit().getImage("src/ressources/img/ui_link.png");
		} catch (Exception e) {
			
		}
	}
	
	public void updateTimer(int tick, Wave next) {
		if(!firstUpdate)
			firstUpdate = true;
		timerTick = tick;
		nextWave = next;
	}
	
	public String convertTimeToCountdownString() {
		int tmp = nextWave.getWaveStartTime()-timerTick;
		String secondes = String.valueOf(tmp%60);
		String minutes = String.valueOf((tmp%3600)/60);
		if(secondes.length()==1) {
			secondes = "0" + secondes;
		}
		return minutes + ":" + secondes;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(new Color(0, 0, 0, 170));
		g2d.fillRect(0, 20, 340, 50);
		g2d.fillRect(0, 80, 250, 35);
		g2d.fillRect(0, 125, 100, 35);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(-1, 20, 340, 50);
		g2d.drawRect(-1, 80, 250, 35);
		g2d.drawRect(-1, 125, 100, 35);
		int rupee_nb = 921;
		g2d.setColor(Color.BLACK);
		g2d.setFont(font2);
		g2d.drawString(String.valueOf(rupee_nb), 41, 151);
		g2d.setFont(font1);
		g2d.drawString("Next wave :", 8, 106);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font2);
		g2d.drawString(String.valueOf(rupee_nb), 40, 150);
		g2d.setFont(font1);
		g2d.drawString("Next wave :", 8, 105);
		g2d.drawImage(rupee, 5, 126, null);
		int posXd = 132;
		int posYd = 83;
		int posXs = posXd + 48;
		int posYs = posYd + 32;
		if(firstUpdate && nextWave!=null) {
			g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/ressources/img/wave_"+ nextWave.getWaveType()+".png"), posXd, posYd, posXs, posYs, 0, 0, 24, 16, null);
			g2d.setColor(Color.BLACK);
			g2d.drawString(convertTimeToCountdownString(), posXd+56, posYd+22);
			g2d.setColor(Color.WHITE);	
			g2d.drawString(convertTimeToCountdownString(), posXd+55, posYd+21);
		} else {
			g2d.setColor(Color.BLACK);
			g2d.drawString("None", posXd+1, posYd+22);
			g2d.setColor(Color.WHITE);	
			g2d.drawString("None", posXd, posYd+21);
		}
		g2d.drawImage(imgLink, 5, 1, null);
		int linkLifeTotal = link.getMaximumHealth();
		int linkLifeRemaining = link.getHealth();
		int row = 0;
		int column = 0;
		for(int i=0; i<(linkLifeTotal/4); i++) {
			int dx1 = column*22 + 110;
			int dy1 = row*19 + 28;
			int dx2 = dx1 + 22;
			int dy2 = dy1 + 16;
			int sx1;
			column++;
			if(i==9) {
				row++;
				column = 0;
			}
			if(i*4<linkLifeRemaining && (i+1)*4>linkLifeRemaining) {
				sx1 = (linkLifeRemaining-i*4)*11;
			} else if(i*4<linkLifeRemaining) {
				sx1 = 44;
			} else {
				sx1 = 0;
			}
			int sy1 = 0;
			int sx2 = sx1 + 11;
			int sy2 = sy1 + 8;
			g2d.drawImage(heart, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		}
	}

}
