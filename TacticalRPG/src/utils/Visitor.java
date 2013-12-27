package utils;

import units.Army;
import units.InfantryManProxy;
import units.PlaneProxy;
import units.SoldierWithShield;
import units.SoldierWithSword;
import units.TankProxy;


public interface Visitor {
	public void visit(Army army);
	public void visit(InfantryManProxy im);
	public void visit(SoldierWithSword sw);
	public void visit(SoldierWithShield sh);
	public void visit(TankProxy tank);
	public void visit(PlaneProxy plane);
	public void printSoldiers();
}
