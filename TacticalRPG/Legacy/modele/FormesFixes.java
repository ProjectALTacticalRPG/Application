package modele;

public class FormesFixes extends Formes {

	private int my_type;
	private int my_hauteur;
	private int my_largeur;
	
	public FormesFixes(int type, int x, int y) {
		super(x, y);
		my_type = type;
		switch(my_type) {
			case 1: // Rubis
				my_hauteur = 32;
				my_largeur = 16;
				break;
			case 2: // Coeur
				my_hauteur = 16;
				my_largeur = 14;
				break;
			case 3: // Bombe
				my_hauteur = 22;
				my_largeur = 14;
				break;
			case 4: // Deblocage bombe
				my_hauteur = 22;
				my_largeur = 14;
				break;
			case 5: // Epee
				my_hauteur = 32;
				my_largeur = 14;
				break;
			case 6: // Cle
				my_hauteur = 32;
				my_largeur = 16;
				break;
			case 7: // Triforce
				my_hauteur = 20;
				my_largeur = 20;
				break;
		}
	}
	
	public int getType() {
		return my_type;
	}
	
	public int getLargeur() {
		return my_largeur;
	}
	
	public int getHauteur() {
		return my_hauteur;
	}
	
}
