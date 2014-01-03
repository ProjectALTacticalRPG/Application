package modele;

public class Mobs extends FormesMobiles {

    private int my_vie;
    private int my_direction_actuelle;
    private int my_hauteur;
    private int my_largeur;
    private int my_type_monstre;
    private int my_nb_max_deplacement;
	
    public Mobs(int x, int y, int type, int direction) {
    	super(x, y, direction);
        my_nb_deplacement_direction = 0;
        my_type_monstre = type;
        switch(type) {
        	case 1: // Chauve-souris
        		my_vie = 2;
        		my_hauteur = 20;
        		my_largeur = 32;
        		my_nb_max_deplacement = 5;
        		break;
        	case 2: // Moblin
        		my_vie = 2;
        		my_hauteur = 32;
        		my_largeur = 28;
        		my_nb_max_deplacement = 10;
        		break;
        	case 3: // Octorok
        		my_vie = 2;
        		my_hauteur = 32;
        		my_largeur = 32;
        		my_nb_max_deplacement = 10;
        		break;
        	case 8: // Demi-boss
        		my_vie = 10;
        		my_hauteur = 32;
        		my_largeur = 94;
        		my_nb_max_deplacement = 8;
        		break;
        	case 9: // Boss
        		my_vie = 30;
        		my_hauteur = 64;
        		my_largeur = 64;
        		my_nb_max_deplacement = 10;
        		break;
        }
    }
	
    public void setVie(int decrement) {
    	my_vie-= decrement;
    }
	
    public int getVie() {
    	return my_vie;
    }

    public int getNbDeplaceDirection() {
        return my_nb_deplacement_direction;
    }

    public int getDirection() {
        return my_direction_actuelle;
    }

    public int getMyNbMaxDepl() {
    	return my_nb_max_deplacement;
    }
    
    public void addNbDeplaceDirection() {
        my_nb_deplacement_direction++;
    }

    public void resetNbDeplaceDirection() {
        my_nb_deplacement_direction = 0;
    }
    
    public void setDirection(int direction) {
        my_direction_actuelle = direction;
    }
    
    public int getHauteur() {
    	return my_hauteur;
    }
    
    public int getLargeur() {
    	return my_largeur;
    }
    
    public int getTypeMonstre() {
    	return my_type_monstre;
    }
    
}
