package arena.graphics;

import java.util.ArrayList;

import units.FighterProxy;
import utils.DeathObserver;
import gameframework.game.GameMovable;

public abstract class LinkedEntity extends GameMovable{
	protected FighterProxy linkWith;
	private ArrayList<DeathObserver> observers = new ArrayList<DeathObserver>();

	
	public void notifyObservers() {
		ArrayList<DeathObserver> temp = new ArrayList<DeathObserver>(observers);

		for(DeathObserver obs : temp)
			obs.update(this);
	}
	
	public void addObserver(DeathObserver o){
		observers.add(o);
	}
	
	public void setFighterProxy(FighterProxy p){
		this.linkWith = p;
	}
	
	public FighterProxy getFighterProxy(){
		return linkWith;
	}
	
	public int getHealth(){
		return linkWith.getHealth();
	}
	
	public boolean isAlive(){
		return linkWith.isAlive();
	}
	
	public void parry(int attack){
		linkWith.parry(attack);
	}
	
	public int strike(){
		return linkWith.strike();
	}
}
