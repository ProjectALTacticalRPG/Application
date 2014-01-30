package arena.test;

import static org.junit.Assert.*;

import org.junit.Test;

import units.Keaton;

public class kaetonTest {

        @Test
        public void testConstructors() {
        	
        	/*Default*/
        	Keaton k1 = new Keaton();
            assertEquals(k1.getName(),"Keaton");
            assertEquals(k1.getHealth(),50);
            assertEquals(k1.getMaximumHealth(),50);
            assertEquals(k1.getDamage(),1);
            assertEquals(k1.getParry(),5);
            
            /*Other*/
            
            Keaton k2 = new Keaton("TOTO",10,20,30);
            assertEquals(k2.getName(),"TOTO");
            assertEquals(k2.getHealth(),10);
            assertEquals(k2.getMaximumHealth(),10);
            assertEquals(k2.getDamage(),20);
            assertEquals(k2.getParry(),30);
            
        }
        
        @Test
        public void testStrikeAndParry()
        {
        	Keaton k1 = new Keaton("TOTO",100,20,30);
        	assertEquals(k1.strike(),20);
        	
        	k1.setHealth(0);
        	assertEquals(k1.strike(),0);
        	
        	k1.setHealth(50);
        	assertEquals(k1.getHealth(),50);
        	
        	k1.parry(40);
        	assertEquals(k1.getHealth(),40);
        	
        }

}
