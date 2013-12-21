package utils;

import units.Army;
import units.HorseManProxy;
import units.InfantryManProxy;
import units.SoldierWithShield;
import units.SoldierWithSword;


public interface Visitor {
	public void visit(Army army);
	public void visit(InfantryManProxy im);
	public void visit(HorseManProxy hm);
	public void visit(SoldierWithSword sw);
	public void visit(SoldierWithShield sh);
	public void printSoldiers();
}
