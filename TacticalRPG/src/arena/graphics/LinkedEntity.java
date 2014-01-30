package arena.graphics;

import java.awt.Point;
import java.util.ArrayList;

import units.FighterProxy;
import units.WaveMember;
import utils.DeathObserver;
import gameframework.base.SpeedVector;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

public abstract class LinkedEntity extends GameMovable implements GameEntity, WaveMember{

	protected FighterProxy linkWith;
	protected Point lastDirection = new Point(0, 0);
	private ArrayList<DeathObserver> observers = new ArrayList<DeathObserver>();
	protected int vulnerableTimer = 0;
	
	public void notifyObservers() {
		ArrayList<DeathObserver> temp = new ArrayList<DeathObserver>(observers);

		for(DeathObserver obs : temp)
			obs.update(this);
	}
	
	public void notifyObservers(int sound){
		ArrayList<DeathObserver> temp = new ArrayList<DeathObserver>(observers);

		for(DeathObserver obs : temp)
			obs.update(this, sound);
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
	
	public int getMaximumHealth(){
		return linkWith.getMaximumHealth();
	}
	
	@Override
	public void parry(int attack){
		linkWith.parry(attack);
		notifyObservers();
	}
	
	@Override
	public int strike(){
		notifyObservers(arena.game.AudioRead.HIT);
		return linkWith.strike();
	}
	
	
	@Override
	public SpeedVector getSpeedVector() {
		Point dir = super.getSpeedVector().getDirection();
		if(dir.x != 0 || dir.y != 0){
			lastDirection = (Point) dir.clone();
		}
		return super.getSpeedVector();
	}
	
	public Point getLastDirection(){
		return lastDirection;
	}
	

	public boolean isVulnerable() {
		return (vulnerableTimer <= 0);
	}
	
	public void setInvulnerable(int timer) {
		vulnerableTimer = timer;
	}
}
