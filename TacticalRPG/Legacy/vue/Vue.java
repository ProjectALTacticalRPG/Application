package vue;

import controleur.Controleur;
import java.util.Observer;
import java.util.Observable;

public class Vue implements Observer {
    private Panneau my_conteneur;
    private Fenetre my_fenetre;
    private Controleur my_controleur;
    private EventClavier my_event;
    private AfficheLink my_link;
    private AfficheMap my_map;
    private AfficheThread my_thread;
    private AfficheNPC my_npc;
    private AfficheFormeFixe my_objets;
    private MonstresThread my_monstres_thread;

    // Constructeur de la vue
    public Vue() {
        my_link = new AfficheLink();
        my_map = new AfficheMap();
        my_npc = new AfficheNPC();
        my_objets = new AfficheFormeFixe();
        my_monstres_thread = new MonstresThread();
        my_conteneur = new Panneau(my_link, my_map, my_npc, my_objets);
        my_fenetre = new Fenetre(my_conteneur,this);
        my_thread = new AfficheThread(my_fenetre);
    }

    // Fonction permettant de lier tous les éléments ensembles
    public void setAll(Controleur controleur) {
    	my_controleur = controleur;
        my_event = new EventClavier(my_controleur,my_link);
        my_link.setThread(my_thread);
        my_link.setPanneau(my_conteneur);
        my_link.setController(my_controleur);
        my_map.setAll(my_controleur, my_conteneur, my_thread, my_monstres_thread);
        my_map.setPos(my_controleur.getLinkMapX()*(-545), my_controleur.getLinkMapY()*(-374));
        my_npc.setController(my_controleur);
        my_objets.setController(my_controleur);
        my_monstres_thread.setController(my_controleur);
        my_thread.setController(my_controleur);
        my_thread.setMap(my_map);
        my_conteneur.addKeyListener(my_event);
        my_conteneur.setFocusable(true);
        my_conteneur.requestFocus();
        my_conteneur.setLayout(null);
    }

    // Quand le modele ou link est mis à jour, on repaint le panneau
    public void update(Observable o, Object arg) {
        my_conteneur.repaint();
    }

    // On lance le thread de la vue
    public void lancerVue() {
    	my_thread.start();
        my_monstres_thread.start();
    }

    public void actionLoop() {
        ((EventClavier) my_conteneur.getKeyListeners()[0]).actionLoop();
    }
}
