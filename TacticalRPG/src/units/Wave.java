package units;

import gameframework.game.GameUniverse;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import arena.graphics.LinkedEntity;

import utils.CustomObservable;
import utils.CustomObserver;
import utils.Visitable;
import utils.Visitor;

public class Wave extends CustomObservable implements WaveMember, Visitable {

	private ArrayList<WaveMember> members = new ArrayList<WaveMember>();
	private boolean unused = true;
	private int waveStartTime;
	private int spriteSize;
	private GameUniverse universe;
	
	public Wave(ArrayList<WaveMember> monsters, int startTime, int spSize, GameUniverse universe) {
		this.members = new ArrayList<WaveMember>(monsters);
		waveStartTime = startTime;
		spriteSize = spSize;
		this.universe = universe;
	}
	
	public int getWaveLength() {
		return members.size();
	}

	public int getWaveStartTime() {
		return waveStartTime;
	}
	
	public String getWaveType() {
		return members.get(0).getClass().getSimpleName().replace("Visual", "").toLowerCase();
	}
	
	public boolean addMember(WaveMember wave){
		if(!checkMember(wave)){
			members.add(wave);
			unused = false;
			return true;
		}
		return false;
	}
	
	public void removeMember(WaveMember wave){
		members.remove(wave);
	}

	public int getHealth() {
		int health = 0;
		
		for(WaveMember a : members){
			health += a.getHealth();
		}
		return health;
	}
	
	public ArrayList<WaveMember> getWaveMembers(){
		return new ArrayList<WaveMember>(members);
	}

	public boolean isAlive() {
		if(unused)
			return true;
		else
			return (members.size() > 0) ? true : false;
	}
	
	public boolean checkMember(WaveMember member){
		boolean result = false;
		for(WaveMember a : members){
			if(a instanceof Wave){
				result = ((Wave) a).checkMember(member);
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
	
	public void addObs(CustomObserver o){
		this.addObserver(o);
		for(WaveMember a : members){
			if(a instanceof FighterProxy)
				((FighterProxy) a).addObserver(o);
			else if(a instanceof Wave)
				((Wave)a).addObs(o);
		}
	}
	
	public void initWave(ArrayList<Rectangle> spawns) {
		for(WaveMember m : members){
			//On effectue cette manipulation pour eviter de devalider le pointeur sur la position de l'entit� dans le driver de d�placement
			if(m instanceof LinkedEntity){
				LinkedEntity le = (LinkedEntity) m;
				le.getPosition().setLocation(randomSpawn(spawns)); 
				universe.addGameEntity(le);
			}
			else if(m instanceof Wave){
				((Wave) m).initWave(spawns);
			}
		}
	}

	private Point randomSpawn(ArrayList<Rectangle> spawns) {
		Random rdm = new Random();
		int i = rdm.nextInt(spawns.size());
		Rectangle r = spawns.get(i);
		int x = 0, y = 0;
		x = r.x+rdm.nextInt(r.width)-spriteSize;
		y = r.y+rdm.nextInt(r.height)-spriteSize;
		return new Point(x, y);
	}

	@Override
	public int getMaximumHealth() {
int health = 0;
		
		for(WaveMember a : members){
			health += a.getMaximumHealth();
		}
		return health;
	}
}
