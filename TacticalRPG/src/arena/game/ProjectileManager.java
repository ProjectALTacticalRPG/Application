package arena.game;

import gameframework.game.GameUniverse;

import java.util.ArrayList;

import arena.graphics.ProjectileVisual;


public class ProjectileManager {

	private ArrayList<ProjectileVisual> projectiles;
	private GameUniverse universe;
	private boolean mutex = false;
	
	public ProjectileManager(GameUniverse universe){
		this.universe = universe;
		projectiles = new ArrayList<ProjectileVisual>();
		projectiles.clear();
	}
	
	public void checkProjectiles(){
		mutex = true;
		ArrayList<ProjectileVisual> toBrowse = new ArrayList<ProjectileVisual>(projectiles);
		for(ProjectileVisual p : toBrowse){
			if(p.isStopped()){
				universe.removeGameEntity(p);
				projectiles.remove(p);
				p = null;
			}
		}
		mutex = false;
	}
	
	public void addProjectile(ProjectileVisual p){
		if(!projectiles.contains(p)){
			projectiles.add(p);
			universe.addGameEntity(p);
		}
	}
	
	public boolean getMutex(){
		return mutex;
	}
}
