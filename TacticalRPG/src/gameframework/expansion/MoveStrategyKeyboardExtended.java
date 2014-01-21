package gameframework.expansion;

import java.awt.Point;
import java.awt.event.KeyEvent;

import gameframework.base.MoveStrategyKeyboard;

public class MoveStrategyKeyboardExtended extends MoveStrategyKeyboard {

	private int lastKeyPressed;

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_RIGHT:
			speedVector.setDirection(new Point(1, 0));
			lastKeyPressed = keycode;
			break;
		case KeyEvent.VK_LEFT:
			speedVector.setDirection(new Point(-1, 0));
			lastKeyPressed = keycode;
			break;
		case KeyEvent.VK_UP:
			speedVector.setDirection(new Point(0, -1));
			lastKeyPressed = keycode;
			break;
		case KeyEvent.VK_DOWN:
			speedVector.setDirection(new Point(0, 1));
			lastKeyPressed = keycode;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_LEFT 
				|| keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_DOWN){
			if(lastKeyPressed == keycode){
				speedVector.setDirection(new Point(0, 0));
			}
		}
	}
}
