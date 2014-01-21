package gameframework.expansion;

import java.awt.Point;
import java.awt.event.KeyEvent;

import gameframework.base.MoveStrategyKeyboard;

public class MoveStrategyKeyboardExtended extends MoveStrategyKeyboard {

	private int movX;
	private int movY;

	@Override
	public void keyPressed(KeyEvent event) {
		move(event.getKeyCode(), 1);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		move(event.getKeyCode(), 0);
	}
	
	private void move(int keycode, int value) {
		switch (keycode) {
		case KeyEvent.VK_RIGHT:
			movX = value;
			break;
		case KeyEvent.VK_LEFT:
			movX = -value;
			break;
		case KeyEvent.VK_UP:
			movY = -value;
			break;
		case KeyEvent.VK_DOWN:
			movY = value;
			break;
		}
		
		speedVector.setDirection(new Point(movX, movY));
	}
}
