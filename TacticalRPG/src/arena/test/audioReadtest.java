package arena.test;

import static org.junit.Assert.*;

import org.junit.Test;

import arena.game.AudioRead;

public class audioReadtest {
	
	@Test
	public void testAudio() {
		AudioRead audio=new AudioRead();
		
		assertNotNull(audio.getSoundElement(AudioRead.DIE));		    
		assertNotNull(audio.getSoundElement(AudioRead.G_HEART));
		assertNotNull(audio.getSoundElement(AudioRead.G_RUPEE));
		assertNotNull(audio.getSoundElement(AudioRead.HIT));
		assertNotNull(audio.getSoundElement(AudioRead.HURT));
		assertNotNull(audio.getSoundElement(AudioRead.LOW_HEALTH));
		assertNotNull(audio.getSoundElement(AudioRead.SWORD));
	}
	
}
