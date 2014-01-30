package arena.test;

import static org.junit.Assert.*;

import org.junit.Test;

import units.Keaton;
import units.Link;

public class linkTest {
	
	 @Test
     public void testConstructors() {
		 
		 /*Default*/
     	 Link l1 = new Link();
         assertEquals(l1.getName(),"Link");
         assertEquals(l1.getHealth(),40);
         assertEquals(l1.getMaximumHealth(),40);
         assertEquals(l1.getDamage(),1);
         assertEquals(l1.getParry(),5);
         
         /*Other*/
         
         Link l2 = new Link("TOTO",10,20,30);
         assertEquals(l2.getName(),"TOTO");
         assertEquals(l2.getHealth(),10);
         assertEquals(l2.getMaximumHealth(),10);
         assertEquals(l2.getDamage(),20);
         assertEquals(l2.getParry(),30);
	  }
	 
	 @Test
     public void testStrikeAndParry()
     {
     	Link l1 = new Link("TOTO",100,20,30);
     	assertEquals(l1.strike(),20);
     	
     	l1.setHealth(0);
     	assertEquals(l1.strike(),0);
     	
     	l1.setHealth(50);
     	assertEquals(l1.getHealth(),50);
     	
     	l1.parry(40);
     	assertEquals(l1.getHealth(),40);
     	
     }

}
