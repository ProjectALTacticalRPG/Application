package testFrameworkDelesse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Click ! Un projectile partira lorsque la méthode sera implémentée");
		System.out.println("En direction des coordonnées ("+arg0.getX()+","+arg0.getY()+")");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}