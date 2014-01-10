package testscode.talents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Talent extends JComponent {

	private String nom;
	private Image image;
	private ArrayList<String> tooltip;
	private int element_mod;
	private double[] valeur_mod;
	private int max_points;
	private int nb_points;
	private int nb_points_requis;
	private Talent talent_requis;
	private boolean isNull;
	
	public Talent(String n, Image i, String tt, int em, double[] vm, int mx, int nbp, int nbpr, Talent rq) {
		nom = n;
		image = i;
		tooltip = new ArrayList<String>();
		setTooltip(tt);
		element_mod = em;
		valeur_mod = vm;
		max_points = mx;
		nb_points = nbp;
		nb_points_requis = nbpr;
		talent_requis = rq;
		isNull = false;
	}
	
	public Talent(Object object) {
		// Talent vide
		isNull = true;
	}
	
	public boolean getIsNull() {
		return isNull;
	}

	public int getNb_points_requis() {
		return nb_points_requis;
	}

	public void setNb_points_requis(int nb_points_requis) {
		this.nb_points_requis = nb_points_requis;
	}

	public void setNbPoints(int i) {
		if(i>0)
			if(nb_points < 4)
				nb_points += i;
		if(i<0)
			if(nb_points > 0)
				nb_points += i;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ArrayList<String> getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tt) {
		int start = 0;
		int end = 29;
		boolean stop = false;
		while(!stop) {
			tooltip.add(tt.substring(start, end));
			start+=30;
			end +=30;
			if(end > tt.length()) {
				end = tt.length();
				stop = true;
			}
		}
		tooltip.add(tt.substring(start, end));
	}

	public int getElement_mod() {
		return element_mod;
	}

	public void setElement_mod(int element_mod) {
		this.element_mod = element_mod;
	}

	public double[] getValeur_mod() {
		return valeur_mod;
	}

	public void setValeur_mod(double[] valeur_mod) {
		this.valeur_mod = valeur_mod;
	}

	public int getMax_points() {
		return max_points;
	}

	public void setMax_points(int max_points) {
		this.max_points = max_points;
	}

	public int getNb_points() {
		return nb_points;
	}
	
}
