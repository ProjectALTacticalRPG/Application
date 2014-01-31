package arena.graphics;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import java.awt.Graphics;
import java.awt.Rectangle;

import arena.game.Weapon;

public abstract class ProjectileVisual extends GameMovable implements Drawable, GameEntity,
Overlappable, Weapon {

	protected DrawableImage shadow;
	protected boolean movable = true;
	protected String prev;

	public abstract void draw(Graphics g);
	public abstract void oneStepMoveAddedBehavior();
	public abstract Rectangle getBoundingBox();
	public abstract boolean isStopped();
}
