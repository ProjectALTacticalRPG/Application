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


public class Arbre extends PanelDrawing {
	
	private ArrayList<Talent> talents;
	public final MainPanel ultimAccess;
	private int nbPointsDispo;
	private int nbPointsDepenses;
	private Talent tooltip;
	
	public Arbre(int x, int y, int w, int h, MainPanel m) {
		super("Arbre des talents", x, y, w, h);
		ultimAccess = m;
		double[] puissance = {1, 2, 3.5, 5};
		double[] armure = {1, 2, 3.5, 5};
		double[] vivacité = {5, 10};
		double[] furie = {1, 2, 3.5, 5};
		double[] blockage = {15};
		double[] erudit = {5, 10, 15, 20};
		double[] richesse = {2.5, 5, 7.5, 10};
		nbPointsDispo = 10;
		nbPointsDepenses = 0;
		tooltip = null;
		talents = new ArrayList<Talent>();
		
		talents.add(new Talent("Puissance", "puissance", "Augmente la puissance de Link de 1/2/3.5/5%", 2, puissance, 4, 0, 0, null));
		talents.add(new Talent("Armure", "armure", "Augmente la résistance de     Link de 1/2/3.5/5%", 2, armure, 4, 0, 0, null));
		talents.add(new Talent("Vivacité", "vivacite", "Augmente la vitesse de        déplacement de Link de 5/10%", 2, vivacité, 2, 0, 0, null));
		talents.add(new Talent("Furie", "furie", "Réduit le temps entre deux    attaques de Link de 1/2/3/5%", 2, furie, 4, 0, 0, null));

		talents.add(new Talent(null));
		talents.add(new Talent("Blockage", "blockage", "Confère à Link 15% de chance  de bloquer les attaques", 2, blockage, 1, 0, 0, null));
		talents.add(new Talent("Erudit", "erudit", "Augmente l'expérience reçue   de 5/10/15/20%", 2, erudit, 4, 0, 0, null));
		talents.add(new Talent("Richesse", "richesse", "Augmente l'or reçu de         2.5/5/7.5/10%", 2, richesse, 4, 0, 0, null));
		
		for(final Talent t:talents) {
			this.add(t);
			if(!t.getIsNull()) {
				t.addMouseListener(
					new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							if(t.getNb_points_requis() <= nbPointsDepenses && nbPointsDispo > 0) {
								if(t.getNb_points() < t.getMax_points()) {
									t.setNbPoints(1);
									ultimAccess.repaint();
									nbPointsDispo--;
									nbPointsDepenses++;
								}
							}
						}
						
						public void mouseEntered(MouseEvent e) { 
							tooltip = t;
							ultimAccess.repaint();
							System.out.println("toto");
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
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(fontExp);
		g2d.drawString("Points disponibles : " + nbPointsDispo, posX+10, posY+55);
		g2d.setFont(fontSmall);
		int i = 0;
		int j = 0;
		for(final Talent t:talents) {
			this.add(t);
			t.setSize(50, 50);
			t.setLocation(685 + i * 80, 95 + j * 90);
			if(t.getIsNull()) {
				t.setVisible(false);
			}
			else {
				g2d.drawImage(t.getImage(), t.getLocation().x, t.getLocation().y, this);
				if(t.getNb_points_requis() > nbPointsDepenses) {
					g2d.setColor(new Color(0, 0, 0, 180));
					g2d.fillRect(t.getLocation().x, t.getLocation().y, 64, 64);
				}
				g2d.setColor(gold);
				g2d.drawRect(t.getLocation().x, t.getLocation().y, 64, 64);
				g2d.setColor(Color.black);
				g2d.fillRect(t.getLocation().x+38, t.getLocation().y+57, 30, 15);
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.drawString(t.getNb_points() + "/" + t.getMax_points(), t.getLocation().x+46, t.getLocation().y+69);
				if(t.getNb_points_requis() > nbPointsDepenses) {
					g2d.setColor(new Color(0, 0, 0, 180));
					g2d.fillRect(t.getLocation().x+38, t.getLocation().y+57, 30, 15);
				}
				g2d.setColor(gold);
				g2d.drawRect(t.getLocation().x+38, t.getLocation().y+57, 30, 15);
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
			g2d.setColor(new Color(0, 0, 0, 240));
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
			if(tooltip.getNb_points_requis() > nbPointsDepenses) {
				g2d.setColor(Color.red);
				i++;
				g2d.setFont(fontTTReq);
				g2d.drawString("(Requiert " + tooltip.getNb_points_requis() + " points dépensés)", tooltip.getLocation().x-210, tooltip.getLocation().y+55 + i * 15);
			}
		}
	}
	
}
