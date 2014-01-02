package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import units.Army;
import units.InfantryManProxy;
import units.PlaneProxy;
import units.SoldierProxy;

public class TestUnits {

	@Test
	public void test() {
		Army a1 = new Army();
		Army a2 = new Army();
		Army a3 = new Army();
		
		SoldierProxy s1 = new InfantryManProxy();
		SoldierProxy s2 = new PlaneProxy();
		
		assertFalse(a1.addMember(s1));
		
		ArrayList<InfantryManProxy> soldiers = new ArrayList<InfantryManProxy>();
		ArrayList<InfantryManProxy> soldiers2 = new ArrayList<InfantryManProxy>();
		
		for(int i = 0; i < 10; ++i){
			soldiers.add(new InfantryManProxy());
		}
		
		for(int i = 0; i < soldiers.size(); ++i){
			soldiers.get(i).addGun();
		}
		
		for(int i = 0; i < 8; ++i){
			soldiers2.add(new InfantryManProxy());
		}
		
		for(int i = 0; i < soldiers2.size(); ++i){
			soldiers2.get(i).addGun();
		}
		
		s1.addGun();
		s2.addGun();
		s1.addRocket();
		
		assertTrue(a1.addMember(s1));
		assertEquals("InfantryManWithGun", a1.getType());
		assertFalse(a1.addMember(s2));
		
		for(int i = 0; i < soldiers.size(); ++i){
			assertTrue(a2.addMember(soldiers.get(i)));
		}
		
		for(int i = 0; i < soldiers2.size(); ++i){
			assertTrue(a3.addMember(soldiers2.get(i)));
		}
		
		assertFalse(a1.addMember(a2));
		assertTrue(a1.addMember(a3));
	}

}
