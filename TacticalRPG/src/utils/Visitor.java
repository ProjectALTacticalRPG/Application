package utils;

import units.Army;
import units.InfantryManProxy;
import units.SoldierWithShield;
import units.SoldierWithSword;


public interface Visitor {
	public void visit(Army army);
	public void visit(InfantryManProxy im);
	public void visit(SoldierWithSword sw);
	public void visit(SoldierWithShield sh);
	public void printSoldiers();
}
