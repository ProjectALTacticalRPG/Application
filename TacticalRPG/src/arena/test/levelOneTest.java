package arena.test;

import static org.junit.Assert.*;
import gameframework.expansion.GameExtendedImpl;
import gameframework.game.GameLevel;

import java.util.ArrayList;

import org.junit.Test;

import arena.game.GameLevelOne;

public class levelOneTest {
	  
		@Test
		public void testGame() {
			GameExtendedImpl g = new GameExtendedImpl(1366, 768);
			g.setFrameParametersToDefault();
			g.frameName("The Legend Of Zelda : Arena");
			
			ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
			
			GameLevelOne g1=new GameLevelOne(g);
			
			
			levels.add(g1);
			
			assertEquals(levels.size(),1);		//Verifie si le niveau a été ajouté
			
			/*g.setFrameParametersToDefault();
			g.setLevels(levels);
			g.start();*/
			
				
			
		}
	
	
}
