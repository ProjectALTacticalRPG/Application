package units;

import java.util.ArrayList;

import utils.CustomObservable;
import utils.CustomObserver;
import utils.Visitable;
import utils.Visitor;

public class Army extends CustomObservable implements ArmyMember, Visitable {
	public static final int MAX_UNIT = 10;
	private ArrayList<ArmyMember> members = new ArrayList<ArmyMember>();
	private String name;
	private String type;
	private boolean unused = true;
	
	
	public Army(){
		
	}
	
	public Army(String name){
		this.name = name;
	}
	
	@Override
	public void parry(int damage) {
		int dmgPerArmy = damage / members.size();
		
		for(ArmyMember a: members){
			a.parry(dmgPerArmy);
		}
		
		ArrayList<ArmyMember> membersTemp = new ArrayList<ArmyMember>();
		for(ArmyMember a: members){
			if(a.isAlive())
				membersTemp.add(a);
		}
		
		members = membersTemp;
	    notifyObservers(this);
	}

	@Override
	public int strike() {
		int damage = 0;
		
		for(ArmyMember a : members){
			damage += a.strike();
		}
		return damage;
	}
	
	public boolean addMember(ArmyMember member){
		if(members.size() >= MAX_UNIT)
			return false;
		else if(!checkMember(member)){
			if(members.isEmpty())
				type = member.getType();
			else if(!member.getType().equals(type))
				return false;
			
			members.add(member);
			unused = false;
			return true;
		}
		return false;
	}
	
	public void removeMember(ArmyMember army){
		members.remove(army);
	}

	/*
	@Override
	public void addSword() {
		for(ArmyMember a: members)
			a.addSword();
	}

	@Override
	public void addShield() {
		for(ArmyMember a : members)
			a.addShield();
	}

	@Override
	public void removeSword() {
		for(ArmyMember a : members)
			a.removeSword();
	}

	@Override
	public void removeShield() {
		for(ArmyMember a : members)
			a.removeShield();
	}*/

	@Override
	public int getHealth() {
		
		int health = 0;
		
		for(ArmyMember a : members){
			health += a.getHealth();
		}
		
		return health;
	}
	
	public ArrayList<ArmyMember> getArmyMembers(){
		return new ArrayList<ArmyMember>(members);
	}

	@Override
	public boolean isAlive() {
		if(unused)
			return true;
		else
			return (members.size() > 0) ? true : false;
	}
	
	public boolean checkMember(ArmyMember member){
		boolean result = false;
		for(ArmyMember a : members){
			if(a instanceof Army){
				result = ((Army) a).checkMember(member);
			}
			else if(a == member){
				return true;
			}
		}
		return result;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public String getName(){
		return name;
	}
	
	public void addObs(CustomObserver o){
		this.addObserver(o);
		for(ArmyMember a : members){
			if(a instanceof SoldierProxy)
				((SoldierProxy) a).addObserver(o);
			else if(a instanceof Army)
				((Army)a).addObs(o);
		}
	}

	@Override
	public void addGun() {
		for(ArmyMember a : members)
			a.addGun();
	}

	@Override
	public void addRocket() {
		for(ArmyMember a : members)
			a.addRocket();
	}

	@Override
	public String getType() {
		return type;
	}
}
