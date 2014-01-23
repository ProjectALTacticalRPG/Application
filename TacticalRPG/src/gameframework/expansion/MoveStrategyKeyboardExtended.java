package gameframework.expansion;

import java.awt.Point; 
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import gameframework.base.MoveStrategyKeyboard;

public class MoveStrategyKeyboardExtended extends MoveStrategyKeyboard {

	private int movX;
	private int movY;
	private ArrayList<Integer> enterCode = new ArrayList<Integer>();
	private int[] konamiCode = {38,38,40,40,37,39,37,39,66,65};

	@Override
	public void keyPressed(KeyEvent event) {
		move(event.getKeyCode(), 1);
		if(enterCode.size()==0 && event.getKeyCode()==38) {
			enterCode.add(38);
		} else if(event.getKeyCode()==konamiCode[(enterCode.size())]) {
			enterCode.add(event.getKeyCode());
		} else {
			enterCode.clear();
		}
		if(enterCode.size()==10) {
			System.out.println("Konami Code entered !");
			enterCode.clear();
		}
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
