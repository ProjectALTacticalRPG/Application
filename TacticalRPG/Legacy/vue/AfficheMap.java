package vue;

import javax.swing.*;
import java.awt.*;
import controleur.Controleur;

public class AfficheMap {

    private Image my_img_world        = new ImageIcon(getClass().getResource("../sprites/Maps/worldmap.png")).getImage();
    private Image my_img_dungeon      = new ImageIcon(getClass().getResource("../sprites/Maps/dungeonmap.png")).getImage();
    private Image my_img_dungeon_over = new ImageIcon(getClass().getResource("../sprites/Maps/over_dungeonmap.png")).getImage();
    private Controleur my_controleur;
    private Panneau my_pan;
    private int my_posaffX;
    private int my_posaffY;
    private AfficheThread my_thread;
    private MonstresThread my_monster;
    private int my_current_mapid;

    public AfficheMap() { }

    public void setPos(int x, int y) {
    	my_posaffX = x;
    	my_posaffY = y;
    }

    public void setAll(Controleur controller, Panneau pan, AfficheThread the_thread, MonstresThread monstre) {
        my_controleur = controller;
        my_pan = pan;
        my_thread = the_thread;
        my_monster = monstre;
        my_current_mapid = my_controleur.getCurrentMap();
    }
    
    // Fonction servant a retourner la bonne image de fond a la bonne position
    public Image getMap() {
    	int a = my_controleur.getCurrentMap();
    	if(a == 0) {
    		if(my_current_mapid != a) {
    			setPos(my_controleur.getLinkMapX()*(-545), my_controleur.getLinkMapY()*(-374));
    			my_current_mapid = a;
    		}
    		return my_img_world;
    	} else {
    		if(my_current_mapid != a) {
    			setPos(my_controleur.getLinkMapX()*(-545), my_controleur.getLinkMapY()*(-374));
    			my_current_mapid = a;
    		}
    		return my_img_dungeon;
    	}
    }
    
    public int getCurrentMap() {
    	return my_controleur.getCurrentMap();
    }
    
    public Image getOverDungeon() {
    	return my_img_dungeon_over;
    }

    public int getPosX() {
    	return my_posaffX;
    }

    public int getPosY() {
    	return my_posaffY;
    }
    
    // On supprime les warnings dû au sleep()
    @SuppressWarnings("static-access")
    // Fonction realisant les effets de transition des cartes (effet de slide)
	public void changerMap() {
    	// On interdit aux monstres d'exister, cela évite a link d'en rencontrer pendant la transition
        my_monster.changeAllow();
        // On prend dans quelle direction on doit faire la transition
        switch(my_controleur.getDirectionLink()) {
            case 1: //gauche
            	// On effectue une boucle for qui va deplacer link et la position de l'image de fond pour creer l'effet
            	for(int i=0; i <= 545; i++) {
	    			my_posaffX++;
	    			if(i > 45)
	    				my_controleur.bougerLinkTransMap(1,0);
	    			my_pan.repaint();
	    			try {
	    				my_thread.sleep(2);
	                } catch(Exception e){
	                    System.out.println(e);
	                }
    		}
                break;
            case 2: //haut
                for(int i=0; i <= 374; i++) {
	    			my_posaffY++;
	    			if(i > 45)
	    				my_controleur.bougerLinkTransMap(0,1);
	    			my_pan.repaint();
	    			try {
	                    my_thread.sleep(2);
	                } catch(Exception e){
	                    System.out.println(e);
	                }
                }
                break;
            case 3: //bas
            	for(int i=0; i <= 374; i++) {
	    			my_posaffY--;
	    			if(i > 45)
	    				my_controleur.bougerLinkTransMap(0,-1);
	    			my_pan.repaint();
	    			try {
	                    my_thread.sleep(2);
	    			} catch(Exception e){
	                    System.out.println(e);
	                }
                }
                break;
            case 4: //droite
                for(int i=0; i <= 545; i++) {
	    			my_posaffX--;
	    			if(i > 45)
	    				my_controleur.bougerLinkTransMap(-1,0);
	    			my_pan.repaint();
	    			try {
	                    my_thread.sleep(2);
	                } catch(Exception e){
	                    System.out.println(e);
	                }
                }
                break;
        }
        // On indique au modele que la transition est finie
    	setLoad();
    	// On autorise les monstes à la fin
        my_monster.changeAllow();
    }
    
    public void setLoad() {
    	my_controleur.setLoad();
    }
}
