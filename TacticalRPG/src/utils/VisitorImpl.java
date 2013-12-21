package utils;

import java.util.ArrayList;

import units.Army;
import units.ArmyMember;
import units.HorseManProxy;
import units.InfantryManProxy;
import units.SoldierWithShield;
import units.SoldierWithSword;


public class VisitorImpl implements Visitor {

	private int infantryMen = 0;
	private int horseMen = 0;
	private int level = 0;
	
	@Override
	public void visit(Army army) {
		
		System.out.println(tab()+"Visite un nouveau groupe armé");
		
		if(level == 0){
			infantryMen = 0;
			horseMen = 0;
		}
		
		level++;
		
		ArrayList<ArmyMember> members = army.getArmyMembers();
		for(ArmyMember a : members){
			a.accept(this);
		}
		
		if(level >0)
			level--;
	}
	
	public int totalInfantryMen(){
		return infantryMen;
	}
	
	public int totalHorseMen(){
		return horseMen;
	}
	
	public void printSoldiers(){
		System.out.println("Nombre de InfantryMan : " + infantryMen);
		System.out.println("Nombre de HorseMan : " + horseMen);
	}

	@Override
	public void visit(SoldierWithSword sw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SoldierWithShield sh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InfantryManProxy im) {
		System.out.println(tab()+"Visite un InfantryMan");
		infantryMen++;
	}

	@Override
	public void visit(HorseManProxy hm) {
		
		System.out.println(tab()+"Visite un HorseMan");
		horseMen++;
	}
	
	private String tab(){
		String tab ="";
		for(int i = 0; i < level; i++)
			tab+="\t";
		return tab;
	}
}
