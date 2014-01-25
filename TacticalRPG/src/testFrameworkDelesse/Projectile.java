package testFrameworkDelesse;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

public class Projectile extends GameMovable implements Drawable, GameEntity,
Overlappable {

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub
		
	}
}
