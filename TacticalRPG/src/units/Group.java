package units;

import java.util.ArrayList;

import utils.CustomObservable;
import utils.CustomObserver;
import utils.Visitable;
import utils.Visitor;

public class Group extends CustomObservable implements GroupMember, Visitable {

	private ArrayList<GroupMember> members = new ArrayList<GroupMember>();
	private String name;
	private boolean unused = true;
	
	
	public Group(){
		
	}
	
	public Group(String name){
		this.name = name;
	}
	
	@Override
	public void parry(int damage) {
		int dmgPerArmy = damage / members.size();
		
		for(GroupMember a: members){
			a.parry(dmgPerArmy);
		}
		
		ArrayList<GroupMember> membersTemp = new ArrayList<GroupMember>();
		for(GroupMember a: members){
			if(a.isAlive())
				membersTemp.add(a);
		}
		
		members = membersTemp;
	    notifyObservers(this);
	}

	@Override
	public int strike() {
		int damage = 0;
		
		for(GroupMember a : members){
			damage += a.strike();
		}
		return damage;
	}
	
	public boolean addMember(GroupMember army){
		if(!checkMember(army)){
			members.add(army);
			unused = false;
			return true;
		}
		return false;
	}
	
	public void removeMember(GroupMember army){
		members.remove(army);
	}

	@Override
	public void addSword() {
		for(GroupMember a: members)
			a.addSword();
	}

	@Override
	public void addShield() {
		for(GroupMember a : members)
			a.addShield();
	}

	@Override
	public void removeSword() {
		for(GroupMember a : members)
			a.removeSword();
	}

	@Override
	public void removeShield() {
		for(GroupMember a : members)
			a.removeShield();
	}

	@Override
	public int getHealth() {
		
		int health = 0;
		
		for(GroupMember a : members){
			health += a.getHealth();
		}
		
		return health;
	}
	
	public ArrayList<GroupMember> getArmyMembers(){
		return new ArrayList<GroupMember>(members);
	}

	@Override
	public boolean isAlive() {
		if(unused)
			return true;
		else
			return (members.size() > 0) ? true : false;
	}
	
	public boolean checkMember(GroupMember member){
		boolean result = false;
		for(GroupMember a : members){
			if(a instanceof Group){
				result = ((Group) a).checkMember(member);
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
		for(GroupMember a : members){
			if(a instanceof FighterProxy)
				((FighterProxy) a).addObserver(o);
			else if(a instanceof Group)
				((Group)a).addObs(o);
		}
	}
}
