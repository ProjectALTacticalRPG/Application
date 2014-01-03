package vue;

import javax.swing.*;
import java.awt.*;

import controleur.Controleur;

public class AfficheLink {

	// On precharge les images
    private Image my_img_left1        = new ImageIcon(getClass().getResource("../sprites/Link/link_gauche_mvt1.png")).getImage();
    private Image my_img_left2        = new ImageIcon(getClass().getResource("../sprites/Link/link_gauche_mvt2.png")).getImage();
    private Image my_img_front1       = new ImageIcon(getClass().getResource("../sprites/Link/link_face_mvt1.png")).getImage();
    private Image my_img_front2       = new ImageIcon(getClass().getResource("../sprites/Link/link_face_mvt2.png")).getImage();
    private Image my_img_right1       = new ImageIcon(getClass().getResource("../sprites/Link/link_droit_mvt1.png")).getImage();
    private Image my_img_right2       = new ImageIcon(getClass().getResource("../sprites/Link/link_droit_mvt2.png")).getImage();
    private Image my_img_back1        = new ImageIcon(getClass().getResource("../sprites/Link/link_dos_mvt1.png")).getImage();
    private Image my_img_back2        = new ImageIcon(getClass().getResource("../sprites/Link/link_dos_mvt2.png")).getImage();
    private Image my_img_a_back       = new ImageIcon(getClass().getResource("../sprites/Link/link_a_back.png")).getImage();
    private Image my_img_a_left       = new ImageIcon(getClass().getResource("../sprites/Link/link_a_left.png")).getImage();
    private Image my_img_a_right      = new ImageIcon(getClass().getResource("../sprites/Link/link_a_right.png")).getImage();
    private Image my_img_a_front      = new ImageIcon(getClass().getResource("../sprites/Link/link_a_front.png")).getImage();
    private Image my_img_justGetSword = new ImageIcon(getClass().getResource("../sprites/Link/Link_Get_Sword.gif")).getImage();
    private Image my_img_justGetTri   = new ImageIcon(getClass().getResource("../sprites/Link/Link_Get_Triforce.gif")).getImage();
    private Image my_img_mort         = new ImageIcon(getClass().getResource("../sprites/Link/link.gif")).getImage();
    private Image my_img_epee         = new ImageIcon(getClass().getResource("../sprites/Items/sword.png")).getImage();
    private Image my_img_bomb         = new ImageIcon(getClass().getResource("../sprites/Items/bomb.png")).getImage();
    private Image my_img;
    private Controleur my_controleur;
    private AfficheThread my_thread;
    private Panneau my_pan;
    private int my_diff_x;
    private int my_diff_y;

    public AfficheLink() {
        my_img = my_img_left1;
    }

    public void setController(Controleur controller) {
        my_controleur = controller;
    }

    public void setThread(AfficheThread the_thread) {
        my_thread = the_thread;
    }

    public void setPanneau(Panneau pan) {
        my_pan = pan;
    }

    // Fonction affectant le bon sprite
    // Link fait nb_depl_max avant d'inverser la position de ses pieds et de ses bras
    // On verifie donc a chaque mouvement si il faut inverser
    @SuppressWarnings("static-access")
	public void setSprite() {
        my_diff_x = 0;
        my_diff_y = 0;
        int nb_depl = my_controleur.getMy_nb_deplacement_direction();
        int nb_depl_max = 15;
        switch(my_controleur.getDirectionLink()) {
            case 1: // vers la gauche
            	// Si on a atteint nb_depl_max on inverse les images et on remet a 0 le compteur
                if((my_img == my_img_left1) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_left2;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_left1) && (nb_depl <= nb_depl_max))  {
                	// Sinon on incremente le nombre de pas de cette image dans cette direction
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_left2) && (nb_depl > nb_depl_max))  {
                	// De meme avec l'image du second mouvement
                    my_img = my_img_left1;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_left2) && (nb_depl <= nb_depl_max))  {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else {
                    my_img = my_img_left1;
                }
                break;
            case 2: // vers le haut
                if((my_img == my_img_back1) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_back2;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_back1) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_back2) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_back1;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_back2) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else {
                    my_img = my_img_back1;
                }
                break;
            case 3: // vers le bas
                if((my_img == my_img_front1) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_front2;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_front1) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_front2) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_front1;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_front2) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else {
                    my_img = my_img_front1;
                }
                break;
            case 4: // vers la droite
                if((my_img == my_img_right1) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_right2;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_right1) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_right2) && (nb_depl > nb_depl_max)) {
                    my_img = my_img_right1;
                    my_controleur.resetMy_nb_deplacement_direction();
                }
                else if((my_img == my_img_right2) && (nb_depl <= nb_depl_max)) {
                    my_controleur.setMy_nb_deplacement_direction();
                }
                else {
                    my_img = my_img_right1;
                }
                break;
        }
        // Si Link ramasse l'epee
        if(my_controleur.getVars(6)==1) {
        	// On garde la position dans laquelle il est
        	Image img_save_dir = my_img;
        	// On affecte l'image de ramassage d'epee
        	my_img = my_img_justGetSword;
        	// On le positionne correctement
            my_diff_y = -27;
            // On l'affiche en figeant le thread
            my_pan.repaint();
        	try {
        		my_thread.sleep(2000);
        	} catch(Exception e) {}
        	// On dit au modele que l'animation a ete effectuee pour ne plus la refaire
        	my_controleur.setJustGetSword();
        	// On remet l'image de la position dans laquelle il etait
        	my_img = img_save_dir; 
        	// On le repositionne et le reaffiche
            my_diff_y = 1;
            my_pan.repaint();
        }
        // Si Link ramasse l'epee
        if(my_controleur.getVars(6)==1) {
        	// On garde la position dans laquelle il est
        	Image img_save_dir = my_img;
        	// On affecte l'image de ramassage d'epee
        	my_img = my_img_justGetTri;
        	// On le positionne correctement
            my_diff_y = -27;
            // On l'affiche en figeant le thread
            my_pan.repaint();
        	try {
        		my_thread.sleep(2000);
        	} catch(Exception e) {}
        	// On dit au modele que l'animation a ete effectuee pour ne plus la refaire
        	my_controleur.setJustGetTri();
        	// On remet l'image de la position dans laquelle il etait
        	my_img = img_save_dir; 
        	// On le repositionne et le reaffiche
            my_diff_y = 1;
            my_pan.repaint();
        }
    }

    // Fonction servant a afficher le sprite de l'attaque a l'epee
    @SuppressWarnings("static-access")
	public void setSprite(int action) {
        switch(my_controleur.getDirectionLink()) {
            case 1:
                my_img = my_img_a_left;
                my_diff_x = -24;
                my_diff_y = 1;
                break;
            case 2:
                my_img = my_img_a_back;
                my_diff_x = 0;
                my_diff_y = -24;
                break;
            case 3:
                my_img = my_img_a_front;
                my_diff_x = 0;
                my_diff_y = 0;
                break;
            case 4:
                my_img = my_img_a_right;
                my_diff_x = 0;
                my_diff_y = 1;
                break;
        }
        my_pan.repaint();
        try {
            my_thread.sleep(200);
        } catch(Exception e){
            System.out.println(e);
        }
        setSprite();
        my_pan.repaint();
    }

    public int getPosX() {
        return my_controleur.getLinkX()+my_diff_x;
    }

    public int getPosY() {
        return my_controleur.getLinkY()+my_diff_y+100;
    }

    public Image getImg() {
        return my_img;
    }
    
    public int getVars(int i) {
    	return my_controleur.getVars(i);
    }

    public Image ImgLinkMort() {
        return my_img_mort;
    }
    
    public Image getImgArme(int i) {
    	if(my_controleur.getArme(i) == "sword")
    		return my_img_epee;
    	else if(my_controleur.getArme(i) == "bomb")
    		return my_img_bomb;
    	else
    		return null;
    }

}
