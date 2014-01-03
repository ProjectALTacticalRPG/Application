package vue;
 
import javax.swing.*;

public class Fenetre extends JFrame {
    
	// serialVersionUID pour empecher le warning
	private static final long serialVersionUID = 1L;
	private Vue my_vue;

	// Creation de la fenetre
    public Fenetre(Panneau pan, Vue vue) {
		my_vue = vue;
		this.setVisible(true);
		this.setTitle("The Legend Of Zelda");
		this.setSize(545, 503);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(pan);
	    this.setVisible(true);
		this.setResizable(false);
    }

    public void actionLoop() {
        my_vue.actionLoop();
    }

}
