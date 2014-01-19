package utils;

import java.util.ArrayList;

import units.Group;
import units.GroupMember;
import units.MoblinProxy;
import units.FighterWithShield;
import units.FighterWithSword;


public class VisitorImpl implements Visitor {

	private int infantryMen = 0;
	private int horseMen = 0;
	private int level = 0;
	
	@Override
	public void visit(Group army) {
		
		System.out.println(tab()+"Visite un nouveau groupe armé");
		
		if(level == 0){
			infantryMen = 0;
			horseMen = 0;
		}
		
		level++;
		
		ArrayList<GroupMember> members = army.getArmyMembers();
		for(GroupMember a : members){
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
	public void visit(FighterWithSword sw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FighterWithShield sh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MoblinProxy im) {
		System.out.println(tab()+"Visite un InfantryMan");
		infantryMen++;
	}

	
	private String tab(){
		String tab ="";
		for(int i = 0; i < level; i++)
			tab+="\t";
		return tab;
	}
}
