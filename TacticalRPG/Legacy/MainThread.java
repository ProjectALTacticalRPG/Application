import controleur.*; 
import modele.*;
import vue.*;

public class MainThread extends Thread {

    private Vue my_vue;
    private Controleur my_controleur;
    private Modele my_modele;
	
    // Initialisation
    public MainThread() {
    	my_modele = new Modele();
    	my_controleur = new Controleur();
        my_controleur.setModele(my_modele);
        my_vue = new Vue();
        my_vue.setAll(my_controleur);
        my_modele.addObserver(my_vue);
        my_vue.lancerVue();
    }
	
    // Fonction pour recharger une nouvelle partie en creant un nouveau modele
    public void reloadAll() {
    	my_modele = new Modele();
        my_controleur.setModele(my_modele);
        my_modele.addObserver(my_vue);
    }
    
    // Thread principal
    @SuppressWarnings("static-access")
	public void run() {
    	while(true) {
    		if(my_modele.stopGame()) {
                try{
                    this.sleep(3000);
                }
                catch(Exception e){ }
    			break;
    		}
    	}
    }
    
}
