package vue;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;
import controleur.Controleur;
import modele.*;

public class AfficheNPC {
    
    private Image my_img_chauve_souris = new ImageIcon(getClass().getResource("../sprites/Ennemis/chauvesouris_rouge.gif")).getImage();    
    private Image my_img_octorok_back  = new ImageIcon(getClass().getResource("../sprites/Ennemis/Octorok_-_Red_Back.gif")).getImage();    
    private Image my_img_octorok_front = new ImageIcon(getClass().getResource("../sprites/Ennemis/Octorok_-_Red_Front.gif")).getImage();    
    private Image my_img_octorok_left  = new ImageIcon(getClass().getResource("../sprites/Ennemis/Octorok_-_Red_Left.gif")).getImage();    
    private Image my_img_octorok_right = new ImageIcon(getClass().getResource("../sprites/Ennemis/Octorok_-_Red_Right.gif")).getImage();    
    private Image my_img_moblin_back   = new ImageIcon(getClass().getResource("../sprites/Ennemis/Moblin_-_Red_Back.gif")).getImage();    
    private Image my_img_moblin_front  = new ImageIcon(getClass().getResource("../sprites/Ennemis/Moblin_-_Red_Front.gif")).getImage();    
    private Image my_img_moblin_left   = new ImageIcon(getClass().getResource("../sprites/Ennemis/Moblin_-_Red_Left.gif")).getImage();    
    private Image my_img_moblin_right  = new ImageIcon(getClass().getResource("../sprites/Ennemis/Moblin_-_Red_Right.gif")).getImage();        
    private Image my_img_demi_boss     = new ImageIcon(getClass().getResource("../sprites/Ennemis/Boss/demi_boss.gif")).getImage();      
    private Image my_img_boss          = new ImageIcon(getClass().getResource("../sprites/Ennemis/Boss/ganondorf.gif")).getImage();
    private Controleur my_controleur;

    public AfficheNPC() { }

    public void setController(Controleur controleur) {
        my_controleur = controleur;
    }

    // Fonction qui retourne l'image du monstre que l'on veut dans le bon type et dans la bonne direction
    public Image getNPC(int i) {
    	// On assigne une image par defaut
    	Image img = my_img_octorok_back;
    	int type = my_controleur.getMobs().get(i).getTypeMonstre();
    	int direction = my_controleur.getMobs().get(i).getDirection();
    	if(type==1) {
    		img = my_img_chauve_souris;
    	}
    	else if(type==2){
	    	switch(direction) {
	    		case 1: // gauche
	    			img = my_img_moblin_left;
	    			break;
	    		case 2: // haut
	    			img = my_img_moblin_back;
	    			break;
	    		case 3: // bas
	    			img = my_img_moblin_front;
	    			break;
	    		case 4: // droite
	    			img = my_img_moblin_right;
	    			break;  		
	    	}
    	}
    	else if(type==3){
	    	switch(direction) {
	    		case 1:
	    			img = my_img_octorok_left;
	    			break;
	    		case 2:
	    			img = my_img_octorok_back;
	    			break;
	    		case 3:
	    			img = my_img_octorok_front;
	    			break;
	    		case 4:
	    			img = my_img_octorok_right;
	    			break;
	    	}
    	}
    	else if(type==8){
    		img = my_img_demi_boss;
    	}
    	else if(type==9){
    		img = my_img_boss;
    	}
    	return img;
    }

    public ArrayList<Mobs> getMobs() {
        return my_controleur.getMobs();
    }
    
}
