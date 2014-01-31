package arena.game;

import gameframework.game.GameUniverse;

import java.util.ArrayList;

import arena.graphics.ProjectileVisual;

public class ProjectileManager {

	private ArrayList<ProjectileVisual> projectiles;
	private GameUniverse universe;
	
	public ProjectileManager(GameUniverse universe){
		this.universe = universe;
		projectiles = new ArrayList<ProjectileVisual>();
	}
	
	public void checkProjectiles(){
		for(ProjectileVisual p : projectiles){
			if(p.isStopped()){
				universe.removeGameEntity(p);
			}
		}
	}
	
	public void addProjectile(ProjectileVisual p){
		if(!projectiles.contains(p)){
			projectiles.add(p);
			universe.addGameEntity(p);
		}
	}
}
