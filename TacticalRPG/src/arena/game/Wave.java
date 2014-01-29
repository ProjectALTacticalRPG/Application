package arena.game;

import gameframework.game.GameUniverse;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import arena.graphics.LinkedEntity;

public class Wave {

	private int waveStartTime;
	private boolean waveKilled;
	private int spriteSize;
	private GameUniverse universe;
	private ArrayList<LinkedEntity> monsters;
	
	public Wave(ArrayList<LinkedEntity> monsters, int startTime, int spSize, GameUniverse universe) {
		this.monsters = new ArrayList<LinkedEntity>(monsters);
		waveStartTime = startTime;
		waveKilled = false;
		spriteSize = spSize;
		this.universe = universe;
	}
	
	public void initWave(ArrayList<Rectangle> spawns) {
		for(LinkedEntity le : monsters){
			//On effectue cette manipulation pour eviter de devalider le pointeur sur la position de l'entité dans le driver de déplacement
			le.getPosition().setLocation(randomSpawn(spawns)); 
			universe.addGameEntity(le);
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

	public String getWaveType() {
		return monsters.get(0).getClass().getSimpleName().replace("Visual", "");
	}

	public int getWaveLength() {
		return monsters.size();
	}

	public int getWaveStartTime() {
		return waveStartTime;
	}
	
	public boolean getWaveKilled() {
		return waveKilled;
	}
	
}
