package testcodeframework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

public class generalLevelUI implements Drawable, GameEntity {

	private Font font1;
	private Font font2;
	private Image rupee;
	private Image heart;
	
	public generalLevelUI() {
		try {
			font1 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,23F);
			font2 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/arcapedi.ttf")).deriveFont(Font.BOLD,20F);
			rupee = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/rupee.png"));
			heart = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/ui_hearts.png"));
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(new Color(0, 0, 0, 170));
		g2d.fillRect(0, 20, 350, 50);
		g2d.fillRect(0, 80, 120, 35);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font2);
		g2d.drawString("587", 45, 105);
		g2d.drawImage(rupee, 5, 81, null);
		int linkLifeTotal = 40;
		int linkLifeRemaining = 40;
		for(int i=0; i<(linkLifeTotal/4); i++) {
			int dx1 = i*22+10;
			int dy1 = 30;
			int dx2 = dx1 + 22;
			int dy2 = dy1 + 16;
			int sx1;
			if(i*4<linkLifeRemaining && (i+1)*4>linkLifeRemaining) {
				sx1 = (linkLifeRemaining-i*4)*11;
				System.out.println((linkLifeRemaining-i*4+2));
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
