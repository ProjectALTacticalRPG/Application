package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import units.Army;
import units.InfantryManProxy;
import units.PlaneProxy;
import units.SoldierProxy;

public class TestUnits {

	@Test
	public void test() {
		Army a1 = new Army();
		
		SoldierProxy s1 = new InfantryManProxy();
		SoldierProxy s2 = new PlaneProxy();
		
		s1.addGun();
		s2.addGun();
		s1.addRocket();
		
		assertTrue(a1.addMember(s1));
		assertEquals("InfantryManWithGun", a1.getType());
		assertFalse(a1.addMember(s2));
	}

}
