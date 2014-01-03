package modele;

public class Link extends FormesMobiles {

    private int my_map_x;
    private int my_map_y;
    private int my_vie;
    private int my_vie_max;
    private int my_rubbies;
    private int my_keys;
    private int my_bombs;
    private int my_nb_get_keys;
    private int my_sword_unlock;
    private int my_bombs_unlock;
    private int my_just_get_sword;
    private int my_just_get_triforce;
    private String my_arme_a;
    private String my_arme_b;

    public Link (int x, int y, String arme_a, String arme_b, int direction, int map_x, int map_y) {
        super(x, y, direction);
        my_vie = 6;
        // Vie max = 5 coeurs, 10 demi coeurs
        my_vie_max = 6;
        my_arme_a  = arme_a;
        my_arme_b  = arme_b;
        my_rubbies = 0;
        my_keys  = 0;
        my_bombs = 0;
        my_map_x = map_x;
        my_map_y = map_y;
        my_nb_get_keys = 0;
        // 0 = faux, 1 = vrai
        my_bombs_unlock  = 0;
        my_sword_unlock  = 0;
        // Sert uniquement pour les animations :
        my_just_get_sword = 0;
        my_just_get_triforce = 0;
    }

    // Tous les gets et les sets
    public int getVie() {
    	return my_vie;
    }
    
    public int getVieMax() {
    	return my_vie_max;
    }

    public String getMy_arme_a() {
        return my_arme_a;
    }

    public String getMy_arme_b() {
        return my_arme_b;
    }

    public int getMy_keys() {
        return my_keys;
    }

    public int getMy_rubbies() {
        return my_rubbies;
    }

    public int getMy_bombs() {
        return my_bombs;
    }
    
    public int getMyNbGetKeys() {
    	return my_nb_get_keys;
    }
    
    public int getMy_bombs_unlock() {
		return my_bombs_unlock;
	}
    
    public int getMy_sword_unlock() {
		return my_sword_unlock;
	}
    
    public int getMy_map_x() {
        return my_map_x;
    }

    public int getMy_map_y() {
        return my_map_y;
    }
    
    public int getMy_just_get_sword() {
		return my_just_get_sword;
	}
    
    public int getMy_just_get_triforce() {
		return my_just_get_triforce;
	}

    public void setMy_arme_a(String arme_a) {
        my_arme_a = arme_a;
    }

    public void setMy_arme_b(String arme_b) {
        my_arme_b = arme_b;
    }
    
    public void setVie(int valeur) {
    	my_vie+= valeur;
    	// On empeche que la vie monte au dessus de la vie maximum
    	if(my_vie > my_vie_max)
    		my_vie = my_vie_max;
    }
    
    public void setNbGetKeys() {
    	my_nb_get_keys++;
    }

    public void setVieMax(int valeur) {
    	my_vie_max+= valeur;
    }
    
    public void setMy_keys(int keys) {
        my_keys += keys;
    }

    public void setMy_bombs(int bombs) {
        my_bombs += bombs;
    }

    public void setMy_rubbies(int rubbies) {
        my_rubbies += rubbies;
    }

    public void setMy_map_x(int map_x) {
        my_map_x += map_x;
    }

    public void setMy_map_y(int map_y) {
        my_map_y += map_y;
    }
    
    public void setMySwordUnlock() {
    	// On passe la valeur à 1 car Link peut gagner l'épée mais ne peut pas la perdre
    	my_sword_unlock = 1;
    	my_arme_a = "sword";
    }
    
    public void setMyBombsUnlock() {
    	// De même que pour l'épée
    	my_bombs_unlock = 1;
    	// On l'affecte à la main libre
    	if(my_arme_a == "sword")
    		my_arme_b = "bomb";
    	else
    		my_arme_a = "bomb";
    }
    
    public void setMy_just_get_sword() {
		my_just_get_sword++;
	}

    public void setMy_just_get_triforce() {
		my_just_get_triforce++;
	}
    
}
