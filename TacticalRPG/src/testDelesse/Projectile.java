package testDelesse;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Projectile extends GameMovable implements Drawable, GameEntity,
Overlappable {

	protected DrawableImage shadow;
	protected boolean movable = true;
	protected String prev;

	public abstract void draw(Graphics g);
	public abstract void oneStepMoveAddedBehavior();
	public abstract Rectangle getBoundingBox();
}
