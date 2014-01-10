package testscode.talents;

import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Main implements Observer {
	
	private static JLabel lblLink;
	private static JLabel lblMob;
	
	public static void main(String[] args) {
		// Attention programmation comme un gros déguelasse
		JFrame f = new JFrame();
		f.setTitle("Test Arbo Talents");
		f.setVisible(true);
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		JPanel pan2 = new JPanel();
		
		f.setLayout(new BorderLayout());
		f.add(pan, BorderLayout.NORTH);
		pan.setLayout(new BorderLayout());
		
		Statistiques link = new Statistiques(50, 20, 1, 30);
		Statistiques mob = new Statistiques(30, 5, 1, 10);
		
		lblLink = new JLabel(" LINK :: Vie : " + link.getLife() + "   Dégâts : " + link.getDmg() + "   Cadence d'auto-attaque : " + link.getCadence_aa() + "   Armure : " + link.getArmure());
		lblMob = new JLabel(" MOB :: Vie : " + mob.getLife() + "   Dégâts : " + mob.getDmg() + "   Cadence d'auto-attaque : " + mob.getCadence_aa() + "   Armure : " + mob.getArmure()); 
		
		pan.add(lblLink, BorderLayout.NORTH);
		pan.add(lblMob, BorderLayout.SOUTH);
		
		Arbre a = new Arbre();
		pan.add(a, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {
		lblLink.repaint();
		lblMob.repaint();
	}

}
