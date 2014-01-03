package vue;

import java.awt.event.*; 
import javax.swing.JOptionPane;
import controleur.Controleur;

public class EventClavier implements KeyListener {

    private Controleur my_controleur;
    private AfficheLink my_link;
    private boolean my_key_down  = false;
    private boolean my_a_down    = false;
    private boolean my_b_down    = false;
    private boolean my_ctrl_down = false;
    private int my_key = 0;
    
    public EventClavier(Controleur controleur, AfficheLink link) {
    	my_controleur = controleur;
        my_link = link;
    }

    public void keyPressed(KeyEvent event) {
    	// Si on appui sur un nouvelle touche elle sera nouvellement attribuee
    	if(event.getKeyCode() != my_key) {
    		my_key = event.getKeyCode();
    		my_key_down = true;
    	}
    }

    public void keyReleased(KeyEvent event) {
    	// Si on relache la touche qui a ete appuyee on change le code touche sinon c'est que l'on a pas relache la derniere touche utilisee
    	// Cela evite les arrets de Link et permet un mouvement fluide
        if(event.getKeyCode() == my_key) {
            my_key_down = false;
            my_a_down = false;
            my_b_down = false;
            my_ctrl_down = false;
            my_key = 0;
        }
    }

    public void keyTyped(KeyEvent event) { }

    public void actionLoop() {
        if(my_key_down) {
            switch(my_key) {
            	case 17: // Controle
            		if(!my_ctrl_down) {
	            		my_controleur.inverserArmes();
	            		my_ctrl_down = true;
            		}
            		break;
                case 37: // Gauche
                    my_controleur.bougerLink(1);
                    my_link.setSprite();
                    break;
                case 38: // Haut
                    my_controleur.bougerLink(2);
                    my_link.setSprite();
                    break;
                case 39: // Bas
                    my_controleur.bougerLink(4);
                    my_link.setSprite();
                    break;
                case 40: // Droite
                    my_controleur.bougerLink(3);
                    my_link.setSprite();
                    break;
                case 65: // A
                	if(!my_a_down) {
                		my_controleur.attaqueLink("a");
                		my_a_down = true;
            			if(my_controleur.getArme(1) == "sword")
            				my_link.setSprite(0);
                	}
                    break;
                case 90: // B (=Z)
                	if(!my_b_down) {
                		my_controleur.attaqueLink("b");
            			my_b_down = true;
            			if(my_controleur.getArme(0) == "sword")
            				my_link.setSprite(0);
            		}
                    break;
                case 109: // !Triche! : + (pave num)
                    my_controleur.setLinkVie(-1);
                    my_key_down = false;
                    my_link.setSprite();
                    break;
                case 107: // !Triche! : - (pave num)
                    my_controleur.setLinkVie(1);
                    my_key_down = false;
                    my_link.setSprite();
                    break;
                case 112: // F1
                	JOptionPane.showMessageDialog(null, "Touches :\nA = A\nB = Z\nCtrl = Inverser arme en A et arme en B\nF1 = Aide", "Aide", 1);
                	break;
            }
        }
    }

}
