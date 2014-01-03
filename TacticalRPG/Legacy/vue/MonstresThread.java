package vue;
import controleur.Controleur;

public class MonstresThread extends Thread {

    private Controleur my_controleur;
    private boolean moveAllowed = true;
    private int my_sleep;

    public MonstresThread() { }

    public void setController(Controleur controller) {
        my_controleur = controller;
        my_sleep = 70;
    }

    // Autorise ou non les monstres a bouger et a etre detectables par link
    public void changeAllow() {
        if(moveAllowed)
            moveAllowed = false;
        else
            moveAllowed = true;
    }

    @SuppressWarnings("static-access")
	public void run() {
         while(true) {
        	 // Si on est dans la salle d'un boss on accelere la vitesse des monstres
        	 if(my_controleur.isSalleBoss())
        		 my_sleep = 20;
        	 else
        		 my_sleep = 70;
             try{
                this.sleep(my_sleep);
             } catch (Exception e) {
                 System.out.println(e);
             }
             if(moveAllowed)
                my_controleur.bougerMonstres();
        }
    }
    


}
