package arena.game;
import java.applet.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Classe de chargement et de lecture audio
 */
public class AudioRead implements AudioClip {

    private ArrayList<AudioClip> playlist;
    private Map<Integer, AudioClip> sounds;
    public static final int DIE = 1;
    public static final int HIT = 4;
    public static final int HURT = 5;
    public static final int LOW_HEALTH = 6;
    public static final int SWORD = 7;
    
    public AudioRead() {
    	try {
    		playlist = new ArrayList<AudioClip>();
    		sounds = new HashMap<Integer, AudioClip>();
    		sounds.put(DIE, Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Die.wav")));
    		sounds.put(HIT, Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Hit.wav")));
    		sounds.put(HURT, Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Hurt.wav")));
    		sounds.put(LOW_HEALTH, Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_LowHealth.wav")));
    		sounds.put(SWORD, Applet.newAudioClip(getClass().getResource("../../ressources/sounds/LOZ_Sword.wav")));
    		
    	} catch(Exception e) {
    		System.out.println("Erreur lors du chargement de la musique");
    	}
    }
    
    public AudioClip getSoundElement(int sound) {
    	return sounds.get(sound);
    }
    
    public void stopAll() {
    	for(AudioClip a:playlist)
    		a.stop();
    }
    
    public void stop() {}

    public void play() {}

    public void loop() {}
}
