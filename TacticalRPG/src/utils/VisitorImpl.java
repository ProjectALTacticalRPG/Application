package utils;

import java.util.ArrayList;

import units.Army;
import units.ArmyMember;
import units.InfantryManProxy;
import units.PlaneProxy;
import units.SoldierWithShield;
import units.SoldierWithSword;
import units.TankProxy;


public class VisitorImpl implements Visitor {

	private int infantryMen = 0;
	private int tanks = 0;
	private int planes = 0;
	private int level = 0;
	
	@Override
	public void visit(Army army) {
		
		System.out.println(tab()+"Visite un nouveau groupe armé");
		
		if(level == 0){
			infantryMen = 0;
			tanks = 0;
			planes = 0;
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
	
	public int totalPlanes(){
		return planes;
	}
	
	public int totalTanks(){
		return tanks;
	}
	
	public void printSoldiers(){
		System.out.println("Nombre de InfantryMan : " + infantryMen);
		System.out.println("Nombre de Tank : " + tanks);
		System.out.println("Nombre de Plane : " + planes);
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
	
	private String tab(){
		String tab ="";
		for(int i = 0; i < level; i++)
			tab+="\t";
		return tab;
	}

	@Override
	public void visit(TankProxy tank) {
		System.out.println(tab()+"Visite un Tank");
		tanks++;
	}

	@Override
	public void visit(PlaneProxy plane) {
		System.out.println(tab()+"Visite un Plane");
		planes++;
	}
}
