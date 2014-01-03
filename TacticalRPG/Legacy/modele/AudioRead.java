package modele;
import java.applet.*;

public class AudioRead implements AudioClip {

    private AudioClip my_musique_fond    = Applet.newAudioClip(getClass().getResource("../musiques/music.mid"));
    private AudioClip my_musique_donjon  = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Dungeon.wav"));
    private AudioClip my_musique_boss    = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Boss.wav"));
    private AudioClip my_musique_low     = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_LowHealth.wav"));
    private AudioClip my_musique_sword   = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Sword.wav"));
    private AudioClip my_musique_die     = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Die.wav"));
    private AudioClip my_musique_hit     = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Hit.wav"));
    private AudioClip my_musique_hurt    = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Hurt.wav"));
    private AudioClip my_musique_rubis   = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Get_Rupee.wav"));
    private AudioClip my_musique_coeur   = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Get_Heart.wav"));
    private AudioClip my_musique_stairs  = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Stairs.wav"));
    private AudioClip my_musique_getit   = Applet.newAudioClip(getClass().getResource("../musiques/LOZ_Fanfare.wav"));
    private AudioClip[] my_playlist;
    private static final int MAX = 12;
    
    // On initialise la playlist
    public AudioRead() {
    	creerPlayList();
    	my_playlist[0].loop();
    }
    
    public void creerPlayList() {
    	// On créer un tableau de titres (playlist) afin de les passer comme l'on souhaite au modèle
    	my_playlist     = new AudioClip[MAX];
    	my_playlist[0]  = my_musique_fond;
    	my_playlist[1]  = my_musique_low;
    	my_playlist[2]  = my_musique_sword;
    	my_playlist[3]  = my_musique_die;
    	my_playlist[4]  = my_musique_hit;
    	my_playlist[5]  = my_musique_hurt;
    	my_playlist[6]  = my_musique_rubis;
    	my_playlist[7]  = my_musique_coeur;
    	my_playlist[8]  = my_musique_boss;
    	my_playlist[9]  = my_musique_donjon;
    	my_playlist[10] = my_musique_stairs;
    	my_playlist[11] = my_musique_getit;
    }

    // Renvoi un élément en particulier de la playlist
    public AudioClip getPlayListElement(int i) {
    	return my_playlist[i];
    }
    
    // Fonction pour arreter toutes les musiques
    public void stopAll() {
    	for(int i = 0; i < MAX; i++) {
    		my_playlist[i].stop();
    	}
    }
    
    public void stop() {}

    public void play() {}

    public void loop() {}

}
