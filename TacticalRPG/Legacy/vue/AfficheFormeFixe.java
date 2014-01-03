package vue;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import controleur.Controleur;
import modele.*;

public class AfficheFormeFixe {

	// On precharge les images
    private Image my_img_rubby = new ImageIcon(getClass().getResource("../sprites/Items/Rupy.gif")).getImage();
    private Image my_img_coeur = new ImageIcon(getClass().getResource("../sprites/Items/life.gif")).getImage();
    private Image my_img_bomb  = new ImageIcon(getClass().getResource("../sprites/Items/bomb.png")).getImage();
    private Image my_img_epee  = new ImageIcon(getClass().getResource("../sprites/Items/sword.png")).getImage();
    private Image my_img_key   = new ImageIcon(getClass().getResource("../sprites/Items/Key.gif")).getImage();
    private Image my_img_tri   = new ImageIcon(getClass().getResource("../sprites/Items/triforce.gif")).getImage();
    private Controleur my_controleur;

    public AfficheFormeFixe() { }

    public void setController(Controleur controleur) {
        my_controleur = controleur;
    }
    
    // Retourne l'image demandee
    public Image getForme(int i) {
    	Image img = my_img_coeur;
    	switch(i) {
        	case 1:
                img = my_img_rubby;
        		break;
        	case 2:
        		img = my_img_coeur;
        		break;
        	case 3:
        		img = my_img_bomb;
        		break;
        	case 4:
        		break;
        	case 5:
        		img = my_img_epee;
        		break;
        	case 6:
        		img = my_img_key;
        		break;
        	case 7:
        		img = my_img_tri;
        		break;
        }
    	return img;
    }
    
    public ArrayList<FormesFixes> getObjets() {
    	return my_controleur.getObjets();
    }
    
}
