package testscode.talents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout; 
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;


public class Arbre extends JPanel {
	
	private ArrayList<Talent> talents;
	private Image back = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/back.png"));
	private Image ignite = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ressources/img/ignite.png"));
	private Font fontHeader = new Font("Arial", Font.PLAIN, 25);
	private Font fontSmall = new Font("Arial", Font.PLAIN, 12);
	private Font fontTTHeader = new Font("Arial", Font.BOLD, 17);
	private Font fontTTDesc = new Font("Arial", Font.PLAIN, 12);
	public final Arbre ultimAccess = this;
	private int nbPointsDispo;
	private int nbPointsDepenses;
	private Talent tooltip;
	
	public Arbre() {
		double[] p = {1, 2, 3.5, 5};
		nbPointsDispo = 10;
		nbPointsDepenses = 0;
		tooltip = null;
		talents = new ArrayList<Talent>();
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent(null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent(null));
		talents.add(new Talent(null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent(null));
		talents.add(new Talent(null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		talents.add(new Talent(null));
		talents.add(new Talent("Puissance", ignite, "Augmente la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, 0, null));
		for(final Talent t:talents) {
			this.add(t);
			if(!t.getIsNull()) {
				t.addMouseListener(
					new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							if(t.getNb_points_requis() <= nbPointsDepenses && nbPointsDispo > 0) {
								if(SwingUtilities.isLeftMouseButton(e))
									t.setNbPoints(1);
								if(SwingUtilities.isRightMouseButton(e))
									t.setNbPoints(-1);
								ultimAccess.repaint();
								nbPointsDispo--;
								nbPointsDepenses++;
							}
						}
						
						public void mouseEntered(MouseEvent e) { 
							tooltip = t;
							ultimAccess.repaint();
						}
						
						public void mouseExited(MouseEvent e) {
							tooltip = null;
							ultimAccess.repaint();
						}
					}
				);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(back, 0, 0, this);
		g2d.setColor(new Color(0, 0, 0, 128));
		g2d.fillRect(635, 30, 400, 600);
		g2d.setColor(Color.white);
		g2d.setFont(fontHeader);
		g2d.drawString("Arbre des talents", 645, 60);
		g2d.setFont(fontSmall);
		g2d.drawString("Points disponibles : " + nbPointsDispo, 645, 80);
		int i = 0;
		int j = 0;
		for(final Talent t:talents) {
			this.add(t);
			t.setSize(50, 50);
			t.setLocation(700 + i *70, 120 + j * 80);
			if(t.getIsNull()) {
				t.setVisible(false);
			}
			else {
				g2d.setColor(Color.white);
				g2d.fillRect(t.getLocation().x+10, t.getLocation().y+50, 30, 15);
				g2d.setColor(Color.black);
				g2d.drawString(t.getNb_points() + "/" + t.getMax_points(), t.getLocation().x+17, t.getLocation().y+62);
				t.setNb_points_requis(j*4);
			}
			if(i==3) {
				i = 0;
				j++;
			}
			else
				i++;
		}
		if(tooltip!=null) {
			g2d.setColor(new Color(0, 0, 0, 220));
			g2d.fillRect(tooltip.getLocation().x-220, tooltip.getLocation().y, 200, 120);
			g2d.setColor(Color.white);
			g2d.setFont(fontTTHeader);
			g2d.drawString(tooltip.getNom(), tooltip.getLocation().x-210, tooltip.getLocation().y+25);
			g2d.setFont(fontTTDesc);
			ArrayList<String> tooltipDec = tooltip.getTooltip();
			i = 0;
			for(String s:tooltipDec) {
				g2d.drawString(s, tooltip.getLocation().x-210, tooltip.getLocation().y+55 + i * 15);
				i++;
			}
		}
	}
	
}
