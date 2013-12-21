package utils;

import units.Army;
import units.SoldierProxy;

public class CombatReport implements CustomObserver{

	private int nbDeath;
	
	@Override
	public void update(SoldierProxy s, String message) {
		
		if(message != null)
			System.out.println(message);
		if(!s.isAlive()){
			System.out.println("Le soldat " + s.getName()+" est mort.");
			nbDeath+=1;
		}
	}
	
	public int getNbDeath(){
		return nbDeath;
	}

	@Override
	public void update(Army a) {
		if(!a.isAlive()){
			System.out.println("Le groupe " + a.getName()+ " a été éliminé.");
		}
	}

	@Override
	public void update(SoldierProxy s) {
		if(!s.isAlive()){
			System.out.println("Le soldat " + s.getName()+" est mort.");
			nbDeath+=1;
		}
	}

}
