package utils;

import units.Group;
import units.MoblinProxy;
import units.FighterWithShield;
import units.FighterWithSword;


public interface Visitor {
	public void visit(Group army);
	public void visit(MoblinProxy im);
	public void visit(FighterWithSword sw);
	public void visit(FighterWithShield sh);
	public void printSoldiers();
}
