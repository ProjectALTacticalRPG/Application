package modele;

public class FormesMobiles extends Formes {

    protected int my_direction_regard;
    protected int my_nb_deplacement_direction;
	
    public FormesMobiles(int x, int y, int direction_regard) {
    	super(x, y);
        my_direction_regard = direction_regard;
        my_nb_deplacement_direction = 0;
    }
	
    public void deplacer(int dx, int dy) {
        my_x += dx;
        my_y += dy;
    }

    // Fonction de deplacement
    public void setMy_direction_regard(int direction_regard, int vitesse) {
        my_direction_regard = direction_regard;
        switch(my_direction_regard) {
            case 1:
                this.deplacer(-vitesse, 0);
                break;
            case 2:
                this.deplacer(0, -vitesse);
                break;
            case 3:
                this.deplacer(0, vitesse);
                break;
            case 4:
                this.deplacer(vitesse, 0);
                break;
        }
        // On met a jour la vue apres le deplacement
        validerChamps();
    }

    public int getMy_direction_regard() {
        return my_direction_regard;
    }

    public void setMy_nb_deplacement_direction() {
        my_nb_deplacement_direction++;
    }

    public void resetMy_nb_deplacement_direction() {
        my_nb_deplacement_direction = 0;
    }

    public int getMy_nb_deplacement_direction() {
        return my_nb_deplacement_direction;
    }
	
}
