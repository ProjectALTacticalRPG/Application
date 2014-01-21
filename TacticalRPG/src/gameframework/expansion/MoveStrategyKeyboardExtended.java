package gameframework.expansion;

import java.awt.Point;
import java.awt.event.KeyEvent;

import gameframework.base.MoveStrategyKeyboard;

public class MoveStrategyKeyboardExtended extends MoveStrategyKeyboard {
	
	@Override
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_LEFT 
				|| keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_DOWN)
			speedVector.setDirection(new Point(0, 0));
	}
}
