package utils;

import units.Wave;
import units.KeatonProxy;
import units.FighterWithShield;
import units.FighterWithSword;

//Non utilis�e dans ce jeu
public interface Visitor {
	public void visit(Wave army);
	public void visit(KeatonProxy im);
	public void visit(FighterWithSword sw);
	public void visit(FighterWithShield sh);
	public void printSoldiers();
}
