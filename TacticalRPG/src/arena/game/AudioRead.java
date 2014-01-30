package arena.game;
import java.applet.*;
import java.util.ArrayList;

public class AudioRead implements AudioClip {

    private ArrayList<AudioClip> playlist;
    
    public AudioRead() {
    	try {
    		playlist = new ArrayList<AudioClip>();
    		//playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Background.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Die.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Get_Heart.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Get_Rupee.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Hit.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Hurt.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_LowHealth.wav")));
    		playlist.add(Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Sword.wav")));
    	} catch(Exception e) {
    		System.out.println("Erreur lors du chargement de la musique");
    	}
    }
    
    public AudioClip getPlaylistElement(int index) {
    	return playlist.get(index);
    }
    
    public void stopAll() {
    	for(AudioClip a:playlist)
    		a.stop();
    }
    
    public void stop() {}

    public void play() {}

    public void loop() {}

}
