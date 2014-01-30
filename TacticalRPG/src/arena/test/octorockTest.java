package arena.test;

import static org.junit.Assert.*;

import org.junit.Test;

import units.Octorock;

public class octorockTest {

        @Test
        public void testConstructors() {
        	
        	/*Default*/
        	Octorock oct1 = new Octorock();
            assertEquals(oct1.getName(),"Octorock");
            assertEquals(oct1.getHealth(),30);
            assertEquals(oct1.getMaximumHealth(),30);
            assertEquals(oct1.getDamage(),1);
            assertEquals(oct1.getParry(),5);
            
            /*Other*/
            
            Octorock oct2 = new Octorock("TOTO",10,20,30);
            assertEquals(oct2.getName(),"TOTO");
            assertEquals(oct2.getHealth(),10);
            assertEquals(oct2.getMaximumHealth(),10);
            assertEquals(oct2.getDamage(),20);
            assertEquals(oct2.getParry(),30);
            
        }
        
        @Test
        public void testStrikeAndParry()
        {
        	Octorock oct1 = new Octorock("TOTO",100,20,30);
        	assertEquals(oct1.strike(),20);
        	
        	oct1.setHealth(0);
        	assertEquals(oct1.strike(),0);
        	
        	oct1.setHealth(50);
        	assertEquals(oct1.getHealth(),50);
        	
        	oct1.parry(40);
        	assertEquals(oct1.getHealth(),40);
        	
        }

}
