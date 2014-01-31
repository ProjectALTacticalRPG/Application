package arena.game;

import java.awt.AlphaComposite; 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

import arena.graphics.LinkVisual;
import units.Wave;
import gameframework.base.Drawable;
import gameframework.game.GameEntity;

public class GeneralLevelUI implements Drawable, GameEntity {

	private Font font1;
	private Font font2;
	private Font font3;
	private Font font4;
	private Image heart;
	private Image sword;
	private Image imgLink;
	private int timerTick;
	private Wave nextWave;
	private boolean firstUpdate;
	private LinkVisual link;
	private int linkLifeTotal;
	private boolean inGame;
	private float alpha = 0.0f;
	
	public GeneralLevelUI(LinkVisual link) {
		timerTick = 0;
		nextWave = null;
		this.link = link;
		linkLifeTotal = link.getMaximumHealth();
		firstUpdate = false;
		inGame = false;
		try {
			font1 = Font.createFont(0, new File("ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,23F);
			font2 = Font.createFont(0, new File("ressources/fonts/arcapedi.ttf")).deriveFont(Font.BOLD,20F);
			font3 = Font.createFont(0, new File("ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,90F);
			font4 = Font.createFont(0, new File("ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,70F);
			heart = Toolkit.getDefaultToolkit().getImage("ressources/img/ui_hearts.png");
			sword = Toolkit.getDefaultToolkit().getImage("ressources/img/ui_weapons_sword.png");
			imgLink = Toolkit.getDefaultToolkit().getImage("ressources/img/ui_link.png");
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
	
	public int[] getItemImage(double x, double y, int size, float proportion, int itemLevel) {
		int[] ret = new int[8];
		ret[0] = (int) x;
		ret[1] = (int) y;
		ret[2] = (int) (ret[0]+(size*proportion));
		ret[3] = (int) (ret[1]+(size*proportion));
		ret[4] = size*itemLevel;
		ret[5] = 0;
		ret[6] = ret[4]+size;
		ret[7] = size;
		return ret;
	}
	
	public void setInGame() {
		inGame = !inGame;
	}
	
	public void resetOpacity() {
		alpha = 0.0f;
	}
	
	public void incrementOpacity() {
		if(alpha < 1.00f)
			alpha += 0.1f;
	}
	
	public void decrementOpacity() {
		if(alpha > 0.00f)
			alpha -= 0.1f;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		if(!inGame && link.isAlive()) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.setColor(Color.WHITE);
			g2d.setFont(font3);
			g2d.drawString("The Legend Of Zelda", 283, 403);
			g2d.drawString("The Legend Of Zelda", 277, 403);
			g2d.drawString("The Legend Of Zelda", 283, 397);
			g2d.drawString("The Legend Of Zelda", 277, 397);
			g2d.setFont(font4);
			g2d.drawString("Arena", 573, 503);
			g2d.drawString("Arena", 573, 497);
			g2d.drawString("Arena", 567, 503);
			g2d.drawString("Arena", 567, 497);
			g2d.setColor(Color.RED);
			g2d.setFont(font3);
			g2d.drawString("The Legend Of Zelda", 280, 400);
			g2d.setFont(font4);
			g2d.drawString("Arena", 570, 500);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.00f));
		} else if(inGame && link.isAlive()) {
			g2d.setColor(new Color(0, 0, 0, 170));
			g2d.fillRect(0, 20, 340, 50);
			g2d.fillRect(0, 80, 250, 35);
			g2d.fillRoundRect(628, 20, 50, 50, 5, 5);
			g2d.fillRoundRect(688, 20, 50, 50, 5, 5);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(-1, 20, 340, 50);
			g2d.drawRect(-1, 80, 250, 35);
			g2d.setFont(font2);
			g2d.drawString("Weapons", 621, 25);
			g2d.drawString("A", 666, 76);
			g2d.drawString("Z", 726, 76);
			g2d.setFont(font1);
			g2d.drawString("Next wave :", 8, 106);
			g2d.setColor(Color.WHITE);
			g2d.setFont(font2);
			g2d.drawString("Weapons", 620, 24);
			g2d.drawString("A", 665, 75);
			g2d.drawString("Z", 725, 75);
			g2d.setFont(font1);
			g2d.drawString("Next wave :", 8, 105);
			int posXd = 132;
			int posYd = 83;
			int posXs = posXd + 48;
			int posYs = posYd + 32;
			if(firstUpdate && nextWave!=null) {
				g2d.drawImage(Toolkit.getDefaultToolkit().getImage("ressources/img/wave_"+ nextWave.getWaveType()+".png"), posXd, posYd, posXs, posYs, 0, 0, 24, 16, null);
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
			Point posItemA = new Point(637, 30);
			Point posItemB = new Point(697, 30); 
			int[] pA = getItemImage(posItemA.getX(), posItemA.getY(), 16, 2, 0);
			int[] pB = getItemImage(posItemB.getX(), posItemB.getY(), 16, 2, 0);
			g2d.drawImage(sword, pA[0], pA[1], pA[2], pA[3], pA[4], pA[5], pA[6], pA[7], null);
			g2d.drawImage(null, pB[0], pB[1], pB[2], pB[3], pB[4], pB[5], pB[6], pB[7], null);
		} else {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, 1366, 768);
			g2d.setColor(Color.WHITE);
			g2d.setFont(font3);
			g2d.drawString("Vous etes mort.", 233, 373);
			g2d.drawString("Vous etes mort.", 227, 373);
			g2d.drawString("Vous etes mort.", 233, 367);
			g2d.drawString("Vous etes mort.", 227, 367);
			g2d.setFont(font4);
			g2d.drawString("Retentez votre chance.", 473, 443);
			g2d.drawString("Retentez votre chance.", 473, 437);
			g2d.drawString("Retentez votre chance.", 467, 443);
			g2d.drawString("Retentez votre chance.", 467, 437);
			g2d.setColor(Color.RED);
			g2d.setFont(font3);
			g2d.drawString("Vous etes mort.", 230, 370);
			g2d.setFont(font4);
			g2d.drawString("Retentez votre chance.", 470, 440);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.00f));
		}
	}

}
