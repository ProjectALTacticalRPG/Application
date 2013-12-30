package tests;

import org.junit.Test;
import units.Army;
import units.InfantryMan;
import units.SoldierProxy;

public class TestUnits {

	@Test
	public void test() {
		Army a1 = new Army();
		
		SoldierProxy s1 = new SoldierProxy(new InfantryMan());
		s1.addGun();
		
		s1.addRocket();
		
		a1.addMember(s1);
	}

}
