package controleur;

import java.util.ArrayList;

import modele.*;

public class Controleur 
{
    private Modele my_modele;
    private int[] my_var;
    
    public Controleur() {
    }
	
    public void setModele(Modele modele) {
        my_modele = modele;
    }
	
    public void bougerLink(int direction) {
        my_modele.bougerLink(direction);
    }
    
    public void bougerLinkTransMap(int x, int y) {
        my_modele.bougerLinkTransMap(x,y);
    }

    public void bougerMonstres() {
        my_modele.bougerMonstres();
    }

    public int getDirectionLink() {
        return my_modele.getDirectionLink();
    }

    public void attaqueLink(String attaque) {
        my_modele.linkAttaque(attaque);
    }

    public int getLinkX() {
        return my_modele.getLinkX();
    }
    
    public int getLinkY() {
        return my_modele.getLinkY();
    }

    public void setLinkVie(int val) {
        my_modele.setVie(val);
    }

    public void setMy_nb_deplacement_direction() {
        my_modele.setMy_nb_deplacement_direction();
    }

    public int getMy_nb_deplacement_direction() {
        return my_modele.getMy_nb_deplacement_direction();
    }

    public void resetMy_nb_deplacement_direction() {
        my_modele.resetMy_nb_deplacement_direction();
    }

    public int getLinkMapX() {
        return my_modele.getLinkMapX();
    }

    public int getLinkMapY() {
        return my_modele.getLinkMapY();
    }

    public int getVars(int i) {
    	my_var = my_modele.getVars();
        return my_var[i];
    }
    
    public void setLoad() {
    	my_modele.setLoad();
    }
    
    public void setJustGetSword() {
    	my_modele.setJustGetSword();
    }
    
    public void setJustGetTri() {
    	my_modele.setJustGetTri();
    }
    
    public ArrayList<FormesFixes> getObjets() {
    	return my_modele.getObjets();
    }
    
    public ArrayList<Mobs> getMobs() {
        return my_modele.getMobs();
    }
    
    public void inverserArmes() {
    	my_modele.inverserArmes();
    }
    
    public String getArme(int i) {
    	return my_modele.getArme(i);
    }
    
	public int getCurrentMap() {
		return my_modele.getCurrentMap();
	}
	
	public boolean isSalleBoss() {
		return my_modele.isSalleBoss();
	}
    
}
