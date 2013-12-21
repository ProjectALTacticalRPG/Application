package utils;

import java.util.ArrayList;

import units.Army;
import units.SoldierProxy;

public class CustomObservable {

	private ArrayList<CustomObserver> observers = new ArrayList<CustomObserver>();

	public void notifyObservers(SoldierProxy s, String message) {

		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);

		for(CustomObserver obs : temp)
			obs.update(s, message);
	}

	public void notifyObservers(Army a) {
		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);
		for(CustomObserver obs : temp)
			obs.update(a);
	}
	

	public void notifyObservers(SoldierProxy s) {
		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);

		for(CustomObserver obs : temp)
			obs.update(s);
	}
	
	public void addObserver(CustomObserver o){
		observers.add(o);
	}

}
