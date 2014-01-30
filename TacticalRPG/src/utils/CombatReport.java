package utils;

import units.Wave;
import units.FighterProxy;

public class CombatReport implements CustomObserver{

	private int nbDeath;
	
	@Override
	public void update(FighterProxy s, String message) {
		
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
	public void update(Wave a) {
		if(!a.isAlive()){
			System.out.println("Un group de " + a.getWaveType()+ " a été éliminé.");
		}
	}

	@Override
	public void update(FighterProxy s) {
		if(!s.isAlive()){
			System.out.println("Le soldat " + s.getName()+" est mort.");
			nbDeath+=1;
		}
	}

}
