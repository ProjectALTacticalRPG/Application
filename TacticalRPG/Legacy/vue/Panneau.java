package vue;

import java.awt.*; 

import javax.swing.*; 

public class Panneau extends JPanel {

	// Serial versionuid
	private static final long serialVersionUID = 1L;
	private AfficheLink my_link;
    private AfficheMap my_map;
    private AfficheNPC my_npcs;
    private AfficheFormeFixe my_objets;
    // On precharge les images en les instanciant
    private Image my_img_interface      = new ImageIcon(getClass().getResource("../sprites/Maps/top_interface.jpg")).getImage();
    private Image my_img_vie_left       = new ImageIcon(getClass().getResource("../sprites/Link/half-left.gif")).getImage();
    private Image my_img_vie_right      = new ImageIcon(getClass().getResource("../sprites/Link/half-right.gif")).getImage();
    private Image my_img_vie_zero_left  = new ImageIcon(getClass().getResource("../sprites/Link/half-left-dead.gif")).getImage();
    private Image my_img_vie_zero_right = new ImageIcon(getClass().getResource("../sprites/Link/half-right-dead.gif")).getImage();
    // Font
    private Font my_font;
    // Taille prise par l'interface
    private static final int TOP = 101;

    public Panneau(AfficheLink link, AfficheMap map, AfficheNPC npcs, AfficheFormeFixe objets) {
        my_link = link;
        my_map = map;
        my_npcs = npcs;
        my_objets = objets;
        createFont();
    }
    
    // La fonction permet d'affecter le nouveau font une seule fois ce qui est moins lourd en mémoire
    public void createFont() {
        my_font = new Font("Courier", Font.BOLD, 24);
        try {	             
        	my_font = Font.createFont(0, getClass().getResourceAsStream("../sprites/arcapedi.ttf")).deriveFont(Font.BOLD,23F);
        }
        catch (Exception e) { }
    }

    public void paintComponent(Graphics g) {
    	int align = 390;
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    try {
	        // Si la vie de link est inferieure a 0 on affiche ce qui est necessaire pour la mort
	        if(my_link.getVars(3) <= 0) {
	            g2d.setColor(Color.black);
	            g2d.fillRect(0, 0, 545, 503);
	            // Affichage de link mort
	            g2d.drawImage(my_link.ImgLinkMort(), my_link.getPosX(), my_link.getPosY(), this);
	        } else {
	        	// Sinon on affiche la carte normale
	            g2d.drawImage(my_map.getMap(), my_map.getPosX(), my_map.getPosY()+TOP, this);
	            // Si link n'est pas en train de changer de zone alors on affiche les monstres et les differents objets
	            if(my_link.getVars(5) != 1) {
	            	if(my_npcs.getMobs().size() != 0)
		            for(int i=0; i < my_npcs.getMobs().size(); i++) {
		                g2d.drawImage(my_npcs.getNPC(i), my_npcs.getMobs().get(i).getMy_x(), my_npcs.getMobs().get(i).getMy_y()+TOP, this);
		            }
	            	if(my_objets.getObjets().size() != 0)
		            for(int i=0; i < my_objets.getObjets().size(); i++) { 
		            	g2d.drawImage(my_objets.getForme(my_objets.getObjets().get(i).getType()), my_objets.getObjets().get(i).getMy_x(), my_objets.getObjets().get(i).getMy_y()+TOP, this);
		            }
	            }
	            // Affichage de link
	            g2d.drawImage(my_link.getImg(), my_link.getPosX(), my_link.getPosY(), this);
	            // Affichage du plafond des portes dans les donjons
	            if(my_map.getCurrentMap() == 1)
	            	g2d.drawImage(my_map.getOverDungeon(), my_map.getPosX(), my_map.getPosY()+TOP, this);
	            g2d.drawImage(my_img_interface, 0, 0, this);
	            g.setFont(my_font);
	            g2d.setColor(Color.white);
	            g2d.drawString(String.valueOf(my_link.getVars(0)), 220, 32);
	            g2d.drawString(String.valueOf(my_link.getVars(1)), 220, 62);
	            g2d.drawString(String.valueOf(my_link.getVars(2)), 220, 82);
	            g2d.drawImage(my_link.getImgArme(0), 272, 35, this);
	            g2d.drawImage(my_link.getImgArme(1), 325, 35, this);
	            // On affiche les demi coeurs en alternant gauche et droite
	            for(int i=0; i < my_link.getVars(4); i++) {
	                if(i < my_link.getVars(3) && i%2==0) {
	                    g2d.drawImage(my_img_vie_left, align, 50, this);
	                }
	                else if(i < my_link.getVars(3) && i%2==1) {
	                    g2d.drawImage(my_img_vie_right, align, 50, this);
	                    align += 20;
	                }
	                else if(i%2==0){
	                    g2d.drawImage(my_img_vie_zero_left, align, 50, this);
	                }
	                else {
	                    g2d.drawImage(my_img_vie_zero_right, align, 50, this);
	                    align += 20;
	                }
	            }
	            // Affichage de la position sur la minimap
	            g2d.setColor(Color.green);
	            g2d.fillRect(my_map.getPosX()/(-545)*17+110, my_map.getPosY()/(-374)*10+30, 7, 7);
	        }
	    } catch(Exception e) {}
    }
}
