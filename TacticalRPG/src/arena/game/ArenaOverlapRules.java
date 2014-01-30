package arena.game;

import java.awt.Point;

import arena.graphics.KeatonVisual;
import arena.graphics.LinkVisual;
import arena.graphics.LinkedEntity;
import arena.graphics.OctorockVisual;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class ArenaOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected MoveBlockerChecker moveBlocker;
	static final int INVULNERABLE_DURATION = 10;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public void setMoveBlockerChecker(MoveBlockerChecker moveBlocker){
		this.moveBlocker = moveBlocker;
	}

	public void overlapRule(LinkVisual link, OctorockVisual octo){
		if(link.isAttacking())
			damage(octo, link);
		else
			damage(link, octo);
	}
	
	public void overlapRule(OctorockVisual octo, LinkVisual link){
		if(link.isAttacking())
			damage(octo, link);
		else
			damage(link, octo);
	}
	
	public void overlapRule(LinkVisual link, KeatonVisual keat){
		if(link.isAttacking())
			damage(keat, link);
		else
			damage(link, keat);
	}
	
	public void overlapRule(KeatonVisual keat, LinkVisual link){
		if(link.isAttacking())
			damage(keat, link);
		else
			damage(link, keat);
	}
	
	public void damage(LinkedEntity damaged, LinkedEntity damager){
		if(damaged.isVulnerable()){
			damaged.parry(damager.strike());
			damaged.setInvulnerable(INVULNERABLE_DURATION);
			int i = 50;
			Point p = damaged.getLastDirection();
			
			while(!moveBlocker.moveValidation(damaged, new SpeedVectorDefaultImpl(p, i)) && i > 0){
				i--;
			}
			
			Point pl = damaged.getPosition();
			
			pl.setLocation(pl.x + (-p.x*i), pl.y + (-p.y*i));
		}
	}
}
