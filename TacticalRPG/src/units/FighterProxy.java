package units;

import java.lang.reflect.Constructor;

import arena.graphics.LinkedEntity;
import utils.CustomObservable;
import utils.Visitor;


public class FighterProxy extends CustomObservable implements GroupMember {

	private Fighter soldier;
	private LinkedEntity visual;

	public FighterProxy(Fighter s){
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

	
	public LinkedEntity getRepresentation(){
		return visual;
	}
	
	public void setRepresentation(LinkedEntity visual){
		this.visual = visual;
	}
	
	//**********************************************
	//Solution introspection
	
	public Object upgrade(Class<?> upgrade){
		try {
			Constructor<?> constr = upgrade.getConstructor(Fighter.class);
			
			soldier = (Fighter) constr.newInstance(soldier);
			return soldier;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(Object obj){

		Fighter previous = null;
		Fighter it = soldier;
		boolean found = false;

		while(it instanceof EquippedFighter && !found){ 
			if(it == (Fighter)obj){
				if(previous == null)
					soldier = ((EquippedFighter) it).soldier;
				else
					((EquippedFighter) previous).soldier = ((EquippedFighter) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedFighter) it).soldier;
			}
		}
	}
	
	//Solution introspection
	//**********************************************
		
	
	

	//**********************************************
	//Solution standard
	
	public int nbItems(){
		
		Fighter it = soldier;
		int nb = 0;
		
		while(it instanceof EquippedFighter){ 
			nb++;
			it = ((EquippedFighter) it).soldier;
		}
		return nb;
	}
	
	
	public void addSword(){
		
		try {
			soldier = new FighterWithSword(soldier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addShield(){
		
		try {
			soldier = new FighterWithShield(soldier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void removeSword(){

		Fighter previous = null;
		Fighter it = soldier;
		boolean found = false;
		while(it instanceof EquippedFighter && !found){ 
			
			if(it instanceof FighterWithSword){
				if(previous == null)
					soldier = ((EquippedFighter) it).soldier;
				else
					((EquippedFighter) previous).soldier = ((EquippedFighter) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedFighter) it).soldier;
			}
		}
	}
	
	public void removeShield(){
		
		Fighter previous = null;
		Fighter it = soldier;
		boolean found = false;
		while(it instanceof EquippedFighter && !found){ 
			if(it instanceof FighterWithShield){
				if(previous == null)
					soldier = ((EquippedFighter) it).soldier;
				else
					((EquippedFighter) previous).soldier = ((EquippedFighter) it).soldier;
				found = true;
			}
			else{
				previous = it;
				it = ((EquippedFighter) it).soldier;
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
