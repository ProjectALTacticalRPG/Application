import javax.swing.JOptionPane;

public class Main {
	
    private static MainThread my_thread;
    
    public static void main(String[] args) {
    	boolean continuer = true;
        my_thread = new MainThread();
        // Tant que l'on continu
	   	while(continuer) {
	        my_thread.run();
	        try {
	        	my_thread.join(); // On attend que le thread se termine = link meurt
	        } catch (InterruptedException e) { }
	    	int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous recommencer la partie ?", "Partie terminée",
	    	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    	// Si on clic sur non on quitte sinon on relance tous les parametres
	    	if(reponse==1)
	    		continuer = false;
	    	else
	    		my_thread.reloadAll();
	   	}
	   	System.exit(0);
    }
}