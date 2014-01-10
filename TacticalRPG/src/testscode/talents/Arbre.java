package testscode.talents;

import java.awt.GridLayout;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Arbre extends JPanel {
	
	public Arbre() {
		this.setLayout(new GridLayout(1,1));
		double[] p = {1, 2, 3.5, 5 };
		ArrayList<Talent> talents = new ArrayList<Talent>();
		talents.add(new Talent("Puissance", null, "Augment la puissance de Link de 1/2/3.5/5%", 2, p, 4, 0, null));
		for(final Talent t:talents) {
			this.add(t);
			t.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if(SwingUtilities.isLeftMouseButton(e))
						t.setNbPoints(1);
					if(SwingUtilities.isRightMouseButton(e))
						t.setNbPoints(-1);
					t.setText(t.getNom() + " (" + t.getNb_points() + "/" + t.getMax_points() + ")");
				}
			});
		}
	}
	
}
