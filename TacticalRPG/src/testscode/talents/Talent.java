package testscode.talents;

import java.awt.Image;

import javax.swing.JButton;

public class Talent extends JButton {

	private String nom;
	private Image image;
	private String tooltip;
	private int element_mod;
	private double[] valeur_mod;
	private int max_points;
	private int nb_points;
	private Talent talent_requis;
	
	public Talent(String n, Image i, String tt, int em, double[] vm, int mx, int nb, Talent rq) {
		nom = n;
		image = i;
		tooltip = tt;
		element_mod = em;
		valeur_mod = vm;
		max_points = mx;
		nb_points = nb;
		talent_requis = rq;
		this.setText(n + " (0/" + mx + ")");
		this.setSize(100, 100);
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

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
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
