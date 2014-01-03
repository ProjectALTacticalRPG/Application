package units;

import java.lang.reflect.Constructor;

import utils.CustomObservable;
import utils.Visitor;


public class SoldierProxy extends CustomObservable implements ArmyMember {

	private Soldier soldier;

	public SoldierProxy(Soldier s){
		soldier = s;
	}


	@Override
	public int strike() {
		return soldier.strike();
	}

	@Override
	public void parry(int attack) {
		
	    notifyObservers(this);
	}

	
	//**********************************************
	//Solution introspection
	
	public Object upgrade(Class<?> upgrade){
		try {
			Constructor<?> constr = upgrade.getConstructor(Soldier.class);
			
			soldier = (Soldier) constr.newInstance(soldier);
			return soldier;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(Object obj){

		Soldier previous = null;
		Soldier it = soldier;
		boolean found = false;

		while(it instanceof EquippedSoldier && !found){ 
			if(it == (Soldier)obj){
				if(previous == null)
					soldier = ((EquippedSoldier) it).soldier;
				else
					((EquippedSoldier) previous).soldier = ((EquippedSoldier) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedSoldier) it).soldier;
			}
		}
	}
	
	//Solution introspection
	//**********************************************
		
	
	

	//**********************************************
	//Solution standard
	
	public int nbItems(){
		
		Soldier it = soldier;
		int nb = 0;
		
		while(it instanceof EquippedSoldier){ 
			nb++;
			it = ((EquippedSoldier) it).soldier;
		}
		return nb;
	}
	
	
	public void addSword(){
		
		try {
			soldier = new SoldierWithSword(soldier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addShield(){
		
		try {
			soldier = new SoldierWithShield(soldier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void removeSword(){

		Soldier previous = null;
		Soldier it = soldier;
		boolean found = false;
		while(it instanceof EquippedSoldier && !found){ 
			
			if(it instanceof SoldierWithSword){
				if(previous == null)
					soldier = ((EquippedSoldier) it).soldier;
				else
					((EquippedSoldier) previous).soldier = ((EquippedSoldier) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedSoldier) it).soldier;
			}
		}
	}
	
	public void removeShield(){
		
		Soldier previous = null;
		Soldier it = soldier;
		boolean found = false;
		while(it instanceof EquippedSoldier && !found){ 
			if(it instanceof SoldierWithShield){
				if(previous == null)
					soldier = ((EquippedSoldier) it).soldier;
				else
					((EquippedSoldier) previous).soldier = ((EquippedSoldier) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedSoldier) it).soldier;
			}
		}
	}
	
	//Solution standard
	//**********************************************
		
	@Override
	public int getHealth() {
		return soldier.getHealth();
	}
	
	@Override
	public String getName(){
		
		return soldier.getName();
	}


	@Override
	public boolean isAlive() {
		return soldier.isAlive();
	}


	@Override
	public void accept(Visitor visitor) {
		
	}
}
