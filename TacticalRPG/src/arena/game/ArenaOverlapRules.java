package arena.game;

import java.awt.Point;

import arena.graphics.BulletVisual;
import arena.graphics.KeatonVisual;
import arena.graphics.LinkVisual;
import arena.graphics.LinkedEntity;
import arena.graphics.OctorockVisual;
import arena.graphics.SwordVisual;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.OverlapRulesApplierDefaultImpl;

/*
 * Classe d'extension pour les règles d'overlap du jeu
 */
public class ArenaOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected MoveBlockerChecker moveBlocker;
	static final int INVULNERABLE_DURATION = 20;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public void setMoveBlockerChecker(MoveBlockerChecker moveBlocker){
		this.moveBlocker = moveBlocker;
	}

	public void overlapRule(LinkVisual link, OctorockVisual octo){
		damage(link, octo);
	}
	
	public void overlapRule(OctorockVisual octo, LinkVisual link){
		damage(link, octo);
	}
	
	public void overlapRule(LinkVisual link, KeatonVisual keat){
		damage(link, keat);
	}
	
	public void overlapRule(KeatonVisual keat, LinkVisual link){
		damage(link, keat);
	}
	
	public void overlapRule(SwordVisual sword, OctorockVisual octo){
		LinkedEntity owner = sword.getOwner();
		if(sword.isAttacking())
			damage(octo, owner);
	}
	
	public void overlapRule(SwordVisual sword, KeatonVisual keat){
		LinkedEntity owner = sword.getOwner();
		if(sword.isAttacking())
			damage(keat, owner);
	}
	
	public void overlapRule(LinkVisual link, BulletVisual bullet){
		LinkedEntity owner = bullet.getOwner();
		damage(link, owner);
		bullet.hasHit();
	}
	
	public void damage(LinkedEntity damaged, LinkedEntity damager){
		if(damaged.isVulnerable()){
			damaged.parry(damager.strike());
			damaged.setInvulnerable(INVULNERABLE_DURATION);
			int i = 50;
			Point p = damager.getLastDirection();
			
			while(!moveBlocker.moveValidation(damaged, new SpeedVectorDefaultImpl(p, i)) && i > 0){
				i--;
			}
			
			Point pl = damaged.getPosition();
			
			pl.setLocation(pl.x + (p.x*i), pl.y + (p.y*i));
		}
	}
}
