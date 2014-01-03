package modele;
import java.io.*;
import java.util.ArrayList;

public class Maps {

	private int[][] my_map;
	private int my_load;
	private int my_nb_mob_debut = 0;
	private int my_skin_mob = 0;
	private ArrayList<Mobs> my_mobs;
	private ArrayList<FormesFixes> my_objets = new ArrayList<FormesFixes>();
	private static final int HEIGHT = 11;
	private static final int WIDTH  = 16;
	// Map type permet de differencier si la carte appartient a un donjon ou a la carte du monde
	private int my_maptype;

	public Maps(int indi, int indj, int mapid) {
		my_maptype = mapid;
		my_map = new int [HEIGHT][WIDTH];
		String path="maps/matrice";
		path += (char)(mapid+48)+"_"+(char)(indi+48)+"_"+(char)(indj+48) + ".txt";
		int colonne=0;
		int ligne=0;
		int cpt =0;
		try{
			InputStream is = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedInputStream bis = new BufferedInputStream(is);
			int a;
			while (bis.available() != 0) {
				a =bis.read();
				// Si on lit autre chose qu'un caractere de debut/fin/saut de ligne
				if(a!=13 && a!=10 && a!=32) {
					if(cpt < WIDTH*HEIGHT) {
						if(colonne==WIDTH)
						{
							colonne=0;
							ligne++;
						}
						my_map[ligne][colonne]=a -48;
						cpt++;
						colonne++;
					}
					else {
                        if(my_nb_mob_debut !=0)
                            my_skin_mob=a-48;
                        else {
                                my_nb_mob_debut=a-48;
                            }
					}
				}
			}
			// Ferme tout ce qu'on a utilise pour la lecture du fichier
			is.close();
			isr.close();
			bis.close();
			// Exceptions liees aux fichiers
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		my_mobs = new ArrayList<Mobs>();
		int x;
		int y;
		// On creer les monstres a des emplacements aleatoires si ces derniers ne sont pas des 1
		for(int i=0; i < my_nb_mob_debut; i++) {
			do {
				x = ((int) (Math.random()*120))+170;
				y = ((int) (Math.random()*185))+100;
			} while (!canPopMonstre(x,y));
			my_mobs.add(new Mobs(x, y, my_skin_mob, 2));
		}
	}

	// Fonction de vérification de deplacement
	public boolean canDeplace(int direction, int colonne, int ligne, int largeur, int hauteur) {
		boolean retour;
		int taille_bloc = 34;
		int colonne2;
		int ligne2;
		switch(direction) {
			case 1: // gauche
			colonne = (int) ((colonne+largeur)/taille_bloc);
			ligne2 = (int) ((ligne+hauteur-8)/taille_bloc);
			ligne = (int) ((ligne+14)/taille_bloc);
			if(colonne <= 0 || my_map[ligne][colonne-1] == 1 || my_map[ligne2][colonne-1] == 1)
				retour = false;
			else
				retour = true;
			break;
			case 2: // haut
				colonne2 = (int) ((colonne+largeur)/taille_bloc);
				colonne = (int) ((colonne+8)/taille_bloc);
				ligne = (int) ((ligne+hauteur+14)/taille_bloc);
				if(ligne <= 0 || ligne > 11 || my_map[ligne-1][colonne] == 1 || my_map[ligne-1][colonne2] == 1)
					retour = false;
				else
					retour = true;
				break;
			case 3: // bas
				colonne2 = (int) ((colonne+largeur)/taille_bloc);
				colonne = (int) ((colonne+8)/taille_bloc);
				ligne = (int) ((ligne-2)/taille_bloc);
				if(ligne >= 10 || my_map[ligne+1][colonne] == 1 || my_map[ligne+1][colonne2] == 1)
					retour = false;
				else
					retour = true;
				break;
			case 4: // droite
				colonne = (int) ((colonne-2)/taille_bloc);
				ligne2 = (int) ((ligne+hauteur-6)/taille_bloc);
				ligne = (int) ((ligne+14)/taille_bloc);
				if(colonne >= 15 || my_map[ligne][colonne+1] == 1 || my_map[ligne2][colonne+1] == 1)
					retour = false;
				else
					retour = true;
				break;
			default:
				retour = false;
		}
		return retour;
	}

	// Fonction qui verifie si link a atteint le bord d'une carte et qui retourne vrai si on doit changer de zone, faux sinon
	public boolean estTeleporteur(int colonne, int ligne, int direction, int largeur, int hauteur) {
		int taille_bloc = 34;
		boolean retour;
		switch(direction) {
			case 1: // gauche
			colonne = (int) ((colonne+largeur)/taille_bloc);
			if(colonne <= 0)
				retour = true;
			else
				retour = false;
			break;
			case 2: // haut
				ligne = (int) ((ligne+hauteur)/taille_bloc);
				if(ligne <= 0)
					retour = true;
				else
					retour = false;
				break;
			case 3: // bas
				ligne = (int) ((ligne-2)/taille_bloc);
				if(ligne >= 10)
					retour = true;
				else
					retour = false;
				break;
			case 4: // droite
				colonne = (int) ((colonne)/taille_bloc);
				if(colonne >= 15)
					retour = true;
				else
					retour = false;
				break;
			default:
				retour = false;
		}
		return retour;
	}

	// On verifie que le monstre peut apparaitre (pop = apparaitre) a l'endroit du random
	public boolean canPopMonstre(int x, int y) {
		boolean retour;
		if(getContenuCase(1,x,y,30,30) != 1)
			retour = true;
		else
			retour = false;
		return retour;
	}

	// Fonction de verification si link touche les montres
	public boolean toucheMonstre(int x, int y, int largeur, int hauteur) {
		int i=0;
		boolean touche = false;
		// Pour chaque monstre et tant que l'on en touche pas, on teste si un des 4 coins de Link est dans l'aire d'un monstre
		while(i < my_mobs.size() && !touche) {
			if((x >= my_mobs.get(i).getMy_x())
					&& (x <= my_mobs.get(i).getMy_x()+my_mobs.get(i).getLargeur())
					&& (y >= my_mobs.get(i).getMy_y())
					&& (y <= my_mobs.get(i).getMy_y()+my_mobs.get(i).getHauteur())) {
				touche=true;
			}
			else if((x + largeur >= my_mobs.get(i).getMy_x())
					&& (x+largeur <= my_mobs.get(i).getMy_x()+my_mobs.get(i).getLargeur())
					&& (y >= my_mobs.get(i).getMy_y())
					&& (y <= my_mobs.get(i).getMy_y()+my_mobs.get(i).getHauteur())) {
				touche=true;
			}
			else if((x >= my_mobs.get(i).getMy_x())
					&& (x <= my_mobs.get(i).getMy_x()+my_mobs.get(i).getLargeur())
					&& (y+hauteur >= my_mobs.get(i).getMy_y())
					&& (y+hauteur <= my_mobs.get(i).getMy_y()+my_mobs.get(i).getHauteur())) {
				touche=true;
			}
			else if((x + largeur >= my_mobs.get(i).getMy_x())
					&& (x+largeur <= my_mobs.get(i).getMy_x()+my_mobs.get(i).getLargeur())
					&& (y+hauteur >= my_mobs.get(i).getMy_y())
					&& (y+hauteur <= my_mobs.get(i).getMy_y()+my_mobs.get(i).getHauteur())) {
				touche=true;
			}
			else
				i++;
		}
		return touche;
	}

	// On verifie si Link est en contact avec un objet
	public int ramasseObjet(int x, int y, int largeur, int hauteur) {
		int retour = 0;
		int i;
		i = 0;
		// On verifie le milieu de chaque arrete de Link (plus precis pour ce type de contact)
		while(i < my_objets.size()) {
			if(((x+largeur/2 >= my_objets.get(i).getMy_x())
					&& (x+largeur/2 <= my_objets.get(i).getMy_x()+my_objets.get(i).getLargeur())
					&& (y >= my_objets.get(i).getMy_y())
					&& (y <= my_objets.get(i).getMy_y()+my_objets.get(i).getHauteur())) 
					|| ((x+largeur >= my_objets.get(i).getMy_x())
					&& (x+largeur <= my_objets.get(i).getMy_x()+my_objets.get(i).getLargeur())
					&& (y+hauteur/2 >= my_objets.get(i).getMy_y())
					&& (y+hauteur/2 <= my_objets.get(i).getMy_y()+my_objets.get(i).getHauteur()))
					|| ((x+largeur/2 >= my_objets.get(i).getMy_x())
					&& (x+largeur/2 <= my_objets.get(i).getMy_x()+my_objets.get(i).getLargeur())
					&& (y+hauteur >= my_objets.get(i).getMy_y())
					&& (y+hauteur <= my_objets.get(i).getMy_y()+my_objets.get(i).getHauteur()))
					|| ((x >= my_objets.get(i).getMy_x())
					&& (x <= my_objets.get(i).getMy_x()+my_objets.get(i).getLargeur())
					&& (y+hauteur/2 >= my_objets.get(i).getMy_y())
					&& (y+hauteur/2 <= my_objets.get(i).getMy_y()+my_objets.get(i).getHauteur()))) {
				retour = my_objets.get(i).getType();
				my_objets.remove(i);
				break;
			}
			else {
				i++;
			}
		}
		return retour;
	}
	
	public void creerObjet(int type) {
		my_objets.add(new FormesFixes(type, 263, 185));
	}

	// Detruit tous les objets d'une carte
	public void deleteAllObjects() {
		my_objets.removeAll(my_objets);
	}

	// Detruit tous les monstres d'une carte
	public void deleteAllMonsters() {
		my_mobs.removeAll(my_mobs);
	}

	// Change l'etat de la carte, si elle est en transition ou non
	public void setLoad() {
		if(my_load == 0)
			my_load = 1;
		else
			my_load = 0;
	}

	public int getLoad() {
		return my_load;
	}

	public ArrayList<Mobs> getArray() {
		return my_mobs;
	}

	public Mobs getMy_mobs(int i) {
		return my_mobs.get(i);
	}

	public ArrayList<FormesFixes> getObjets() {
		return my_objets;
	}

	public int getTypeMap() {
		return my_maptype;
	}
	
	// Retourne le contenu de la case en colonne/ligne donn
	public int getContenuCase(int direction, int colonne, int ligne, int largeur, int hauteur) {
		int taille_bloc = 34;
		colonne = (int) ((colonne)/taille_bloc);
		if(direction == 2)
			ligne = (int) ((ligne+hauteur)/taille_bloc);
		else
			ligne = (int) ((ligne)/taille_bloc);
		if(ligne > 10)
			ligne = 10;
		return my_map[ligne][colonne];
	}

}