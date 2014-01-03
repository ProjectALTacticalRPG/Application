package vue;

import controleur.Controleur;

public class AfficheThread extends Thread {

    public Fenetre my_fenetre;
    private Controleur my_controleur;
    private AfficheMap my_map;
    
    public AfficheThread(Fenetre fenetre) {
        my_fenetre = fenetre;
    }

    public void setController(Controleur controller) {
        my_controleur = controller;
    }

    public void setMap(AfficheMap map) {
        my_map = map;
    }
    
    @SuppressWarnings("static-access")
	public void run() {
        while(true) {
            my_fenetre.actionLoop();
            try{
                this.sleep(10);
            }
            catch(Exception e){
                System.out.println(e);
            }
            // Si link doit changer de carte alors on lance le changement
            if(my_controleur.getVars(5) == 1) {
            	my_map.changerMap();
            }
        }
    }

}
