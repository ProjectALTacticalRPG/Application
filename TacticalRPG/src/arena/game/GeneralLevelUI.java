package arena.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

public class GeneralLevelUI implements Drawable, GameEntity {

	private Font font1;
	private Font font2;
	private Image rupee;
	private Image heart;
	private Image link;
	
	public GeneralLevelUI() {
		try {
			font1 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/Triforce.ttf")).deriveFont(Font.BOLD,23F);
			font2 = Font.createFont(0, getClass().getResourceAsStream("/ressources/fonts/arcapedi.ttf")).deriveFont(Font.BOLD,20F);
			rupee = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/rupee.png"));
			heart = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/ui_hearts.png"));
			link = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/ui_link.png"));
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g2d.setColor(new Color(0, 0, 0, 170));
		g2d.fillRect(0, 20, 340, 50);
		g2d.fillRect(0, 80, 100, 35);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 20, 340, 50);
		g2d.drawRect(0, 80, 100, 35);
		g2d.setFont(font2);
		int rupee_nb = 921;
		g2d.setColor(Color.BLACK);
		g2d.drawString(String.valueOf(rupee_nb), 41, 106);
		g2d.setColor(Color.WHITE);
		g2d.drawString(String.valueOf(rupee_nb), 40, 105);
		g2d.drawImage(rupee, 5, 81, null);
		g2d.drawImage(link, 5, 1, null);
		int linkLifeTotal = 80;
		int linkLifeRemaining = 74;
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
