package utils;

import java.util.ArrayList;

import units.Wave;
import units.FighterProxy;

//Non utilisée dans ce jeu
public class CustomObservable {

	private ArrayList<CustomObserver> observers = new ArrayList<CustomObserver>();

	public void notifyObservers(FighterProxy s, String message) {

		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);

		for(CustomObserver obs : temp)
			obs.update(s, message);
	}

	public void notifyObservers(Wave a) {
		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);
		for(CustomObserver obs : temp)
			obs.update(a);
	}
	

	public void notifyObservers(FighterProxy s) {
		ArrayList<CustomObserver> temp = new ArrayList<CustomObserver>(observers);

		for(CustomObserver obs : temp)
			obs.update(s);
	}
	
	public void addObserver(CustomObserver o){
		observers.add(o);
	}

}
