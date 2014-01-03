package modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Modele extends Observable implements Observer {

	private Link my_personnage = new Link(120,120,"","",1,1,4);
	private AudioRead my_audio;
	private Maps my_map;
	private Maps [][] my_map_world = new Maps[3][5];
	private Maps [][] my_map_dungeon = new Maps[3][3];
	private boolean my_fin_jeu = false;
	// On verifie si link vient juste de passer un teleporteur interieur<->exterieur
	private boolean my_just_change = false;
	// Hauteur et largeur de Link
	private static final int LARGEUR = 30;
	private static final int HAUTEUR = 32;

	public Modele() {
		my_personnage.addObserver(this);
		my_audio = new AudioRead();
		for(int i=0;i<3;i++)
			for(int j=0;j<5;j++)
				my_map_world[i][j]=new Maps(i,j,0);
		my_map = my_map_world[my_personnage.getMy_map_x()][my_personnage.getMy_map_y()];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				my_map_dungeon[i][j] = new Maps(i,j,1);
	}
	
	// Fonction des teleportations interieur<->exterieur
	public void specialTeleportation(int a) {
		int changeMusique = -1;
		// Si on est sur la carte monde
		if(getCurrentMap()==0) {
			switch(a) {
				// Teleporteur numero 2 (1 etant deja pris par les blocs non traversables
				case 2:
					my_audio.getPlayListElement(10).play();
					// On affecte la carte correspondante du donjon
					my_map = my_map_dungeon[0][2];
					// On place link
					my_personnage.setMy_map_x(-1);
					my_personnage.setMy_map_y(-2);
					my_personnage.setMy_x(250);
					my_personnage.setMy_y(330);
					// On change les musiques
					changeMusique = 2;
					break;
				// Teleporteur numero 3
				case 3:
					my_audio.getPlayListElement(10).play();
					my_map = my_map_dungeon[0][1];
					my_personnage.setMy_map_x(0);
					my_personnage.setMy_map_y(-2);
					my_personnage.setMy_x(250);
					my_personnage.setMy_y(330);
					changeMusique = 2;
					break;
				// Teleporteur numero 4
				case 4:
					my_audio.getPlayListElement(10).play();
					my_map = my_map_dungeon[1][2];
					my_personnage.setMy_map_x(0);
					my_personnage.setMy_map_y(2);
					my_personnage.setMy_x(250);
					my_personnage.setMy_y(330);
					changeMusique = 1;
					break;
			}
		} else {
			switch(a) {
				// De meme qu'avant sauf que ce sont les teleporteurs du donjon/cave vers l'exterieur
				case 2:
					my_map = my_map_world[1][4];
					my_personnage.setMy_map_x(1);
					my_personnage.setMy_map_y(2);
					my_personnage.setMy_x(137);
					my_personnage.setMy_y(50);
					changeMusique = 0;
					break;
				case 3:
					my_map = my_map_world[0][3];
					my_personnage.setMy_map_x(0);
					my_personnage.setMy_map_y(2);
					my_personnage.setMy_x(240);
					my_personnage.setMy_y(45);
					changeMusique = 0;
					break;
				case 4:
					my_map = my_map_world[1][0];
					my_personnage.setMy_map_x(0);
					my_personnage.setMy_map_y(-2);
					my_personnage.setMy_x(235);
					my_personnage.setMy_y(145);
					changeMusique = 0;
					break;
			}
		}
		if(changeMusique == 0) {
			// Musique du monde
			my_audio.getPlayListElement(9).stop();
			my_audio.getPlayListElement(0).loop();
		}
		else if(changeMusique == 1) {
			// Musique du donjon
			my_audio.getPlayListElement(0).stop();
			my_audio.getPlayListElement(9).loop();
		}
		else if(changeMusique == 2) {
			// Cave = aucune musique
			my_audio.getPlayListElement(0).stop();
		}
		my_just_change = true;
	}

	// Fonction de mouvement de Link
	public void bougerLink(int direction) {
		// Si il peut se deplacer dans la direction souhaitee
		if(my_map.canDeplace(direction, getLinkX(), getLinkY(), LARGEUR, HAUTEUR)) {
			int loot;
			// On recupere le contenu de la case
			int a = my_map.getContenuCase(direction, getLinkX()+LARGEUR/2, getLinkY(), LARGEUR, HAUTEUR);
			// On effectue le deplacement
			my_personnage.setMy_direction_regard(direction,2);
			// Si on ne vient pas juste d'utiliser un teleporteur alors les teleporteurs sont actifs (cela evite les teleportations infinies)
			if(!my_just_change)
				specialTeleportation(a);
			// On creer les objets qui doivent etre cree dans la carte actuelle (par exemple l'epee)
			getSpecialObjets();
			// Si on est sur une case 0 cela veut dire que l'on est plus sur un teleporteur donc on passe a faux
			if(a == 0)
				my_just_change = false;
			// On verifie si Link rentre en contact avec un monstre
			verifTouche(direction);
			// On fait ramasser les objets a link
			if((loot = my_map.ramasseObjet(getLinkX(), getLinkY(), LARGEUR, HAUTEUR)) != 0) {
				switch(loot) {
				case 1: // Rubis
					my_personnage.setMy_rubbies(1);
					my_audio.getPlayListElement(6).play();
					break;
				case 2: // Coeur de sante
					my_personnage.setVie(2);
					my_audio.getPlayListElement(7).play();
					break;
				case 3: // Bombe
					my_personnage.setMy_bombs(1);
					break;
				case 4: // Deblocage bombe
					my_personnage.setMyBombsUnlock();
					break;
				case 5: // Epee
					my_personnage.setMySwordUnlock();
					my_audio.getPlayListElement(11).play();
					my_personnage.setMy_just_get_sword();
					break;
				case 6: // Cle
					my_personnage.setMy_keys(1);
					break;
				case 7: // Triforce
					my_audio.getPlayListElement(11).play();
					my_personnage.setMy_just_get_sword();
					my_fin_jeu = true;
					break;
				}
				// On update la vue
				update(this, this);
			}
		}
		// Si on change la carte (estTeleporteur = slide de carte, on reste dans le monde ou dans le donjon)
		if(my_map.estTeleporteur(getLinkX(), getLinkY(), direction, LARGEUR, HAUTEUR)) {
			switch(direction) {
			case 1:
				changeMap(-1,0);
				break;
			case 2:
				changeMap(0,-1);
				break;
			case 3:
				changeMap(0,1);
				break;
			case 4:
				changeMap(1,0);
				break;
			}
			// On dit a la vue que l'on souhaite un changement de carte
			my_map.setLoad();
		}
	}

	// Fonction de deplacement des monstres
	public void bougerMonstres() {
		int direction;
		// Pour chaque monstre
		for(int i=0; i< my_map.getArray().size(); i++) {
			// Si ils ont effectue tous les deplacements qu'ils doivent faire au minimum (pour eviter le changement de direction toutes les 0,07s)
			if(my_map.getMy_mobs(i).getMy_nb_deplacement_direction() == 0) {
				// On donne une nouvelle direction aleatoire
				direction = ((int) (Math.random()*4))+1;
				// On regarde si il peut se deplacer dans la direction
				if(my_map.canDeplace(direction, my_map.getMy_mobs(i).getMy_x(), my_map.getMy_mobs(i).getMy_y(), my_map.getMy_mobs(i).getLargeur(), my_map.getMy_mobs(i).getHauteur()))
					my_map.getMy_mobs(i).setMy_direction_regard(direction,5);
				// On incremente le nombre de deplacement dans cette direction de 1 et on lui donne comme direction actuelle
				my_map.getMy_mobs(i).addNbDeplaceDirection();
				my_map.getMy_mobs(i).setMy_nb_deplacement_direction();
				my_map.getMy_mobs(i).setDirection(direction);
			} else {
				// Sinon on continu dans la meme direction
				// Si il peut se deplacer il le fait
				if(my_map.canDeplace(my_map.getMy_mobs(i).getDirection(), my_map.getMy_mobs(i).getMy_x(), my_map.getMy_mobs(i).getMy_y(), my_map.getMy_mobs(i).getLargeur(), my_map.getMy_mobs(i).getHauteur()))
					my_map.getMy_mobs(i).setMy_direction_regard(my_map.getMy_mobs(i).getDirection(),5);
				// On incremente le compteur de deplacement dans cette direction
				my_map.getMy_mobs(i).setMy_nb_deplacement_direction();
				// Si le nombre de pas qu'il doit faire dans cette direction est atteint on remet a 0 le compteur ce qui permettra un nouveau random de direction au prochain tour
				if(my_map.getMy_mobs(i).getNbDeplaceDirection() > my_map.getMy_mobs(i).getMyNbMaxDepl())
					my_map.getMy_mobs(i).resetMy_nb_deplacement_direction();
			}
			// On verifie si le monstre touche Link
			verifTouche(my_map.getMy_mobs(i).getDirection());
		}
		// On update la vue
		update(this, this);
	}

	// Fonction qui met a jour la vue quand on lui demande
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers(this);
	}

	// Fonction de changement de carte (interieur<->interieur ou exterieur<->exterieur)
	public void changeMap(int x, int y) {
		// On change la postion de Link
		my_personnage.setMy_map_x(x);
		my_personnage.setMy_map_y(y);
		// On detruit tous les objets de la carte qu'on quitte
		my_map.deleteAllObjects();
		// On affecte la bonne carte
		if(getCurrentMap()==0)
			my_map = my_map_world[my_personnage.getMy_map_x()][my_personnage.getMy_map_y()];
		else
			my_map = my_map_dungeon[my_personnage.getMy_map_x()][my_personnage.getMy_map_y()];
		// On met la musique du boss en entrant si on est dans la bonne piece
		if(isSalleBoss()) {
			my_audio.getPlayListElement(9).stop();
			my_audio.getPlayListElement(8).loop();
		}
	}

	// Modification de la vie et changements sonores en consequence
	public void setVie(int valeur) {
		my_personnage.setVie(valeur);
		// Si la vie de Link descend en dessous ou est egale a 1 coeur alors on joue le bip sinon on l'arrete
		if(my_personnage.getVie() <= 2 && my_personnage.getVie() > 0)
			my_audio.getPlayListElement(1).loop();
		else if(my_personnage.getVie() > 2)
			my_audio.getPlayListElement(1).stop();
		else {
			makeLinkDeath();
		}
		update(this, this);
	}
	
	// Retourne plus rapidement les valeurs numeriques a la vue
	public int[] getVars() {
		int[] tab = new int[8];
		tab[0] = my_personnage.getMy_rubbies(); 
		tab[1] = my_personnage.getMy_keys(); 
		tab[2] = my_personnage.getMy_bombs();
		tab[3] = my_personnage.getVie();
		tab[4] = my_personnage.getVieMax();
		tab[5] = my_map.getLoad();
		tab[6] = my_personnage.getMy_just_get_sword();
		tab[7] = my_personnage.getMy_just_get_triforce();
		return tab;
	}

	// Fonction qui inverse les armes en A et en B (utilisable par Ctrl dans le jeu)
	public void inverserArmes() {
		String arme_a = my_personnage.getMy_arme_a();
		my_personnage.setMy_arme_a(my_personnage.getMy_arme_b());
		my_personnage.setMy_arme_b(arme_a);
	}

	// Fonction d'attaque de Link
	public void linkAttaque(String attaque) {
		// Si on attaque a l'epee
		if((attaque == "a" && my_personnage.getMy_arme_a()=="sword") || (attaque == "b" && my_personnage.getMy_arme_b()=="sword")) {
			// On joue le bruit
			my_audio.getPlayListElement(2).play();
			// Pour chacun des monstres de la zone
			for(int i=0; i < my_map.getArray().size(); i++) {
				// Si on le touche
				if(toucheMob(i)) {
					// On decremente sa vie
					my_map.getMy_mobs(i).setVie(2);
					// Si il meurt et que ce n'est pas un boss
					if(my_map.getMy_mobs(i).getVie() <= 0 && my_map.getMy_mobs(i).getTypeMonstre()!=8 && my_map.getMy_mobs(i).getTypeMonstre()!=9) {
						// On fait un nombre aleatoire qui decide si il lache un objet
						int rand = (int) (Math.random()*4);
						int min = 3;
						// On lui permet de lacher une bombe si on les a debloquees
						if(my_personnage.getMy_bombs_unlock() == 1)
							min = 4;
						if(rand < min && rand > 0) {
							getObjets().add(new FormesFixes(rand, my_map.getMy_mobs(i).getMy_x(), my_map.getMy_mobs(i).getMy_y()));
						}
						// On supprime le monstre de la carte
						my_map.getArray().remove(i);
					} else if(my_map.getMy_mobs(i).getVie() <= 0 && my_map.getMy_mobs(i).getTypeMonstre()==8) {
						my_map.creerObjet(6);
						// On supprime le demi boss de la carte
						my_map.getArray().remove(i);
					} else if(my_map.getMy_mobs(i).getVie() <= 0 && my_map.getMy_mobs(i).getTypeMonstre()==9) {
						my_map.creerObjet(7);
						// On supprime le boss de la carte
						my_map.getArray().remove(i);
					}
					// Bruit quand on touche un monstre
					my_audio.getPlayListElement(4).play();
				}
			}
		}
	}

	// Fonction servant a bouger link lors d'une transition de carte
	public void bougerLinkTransMap(int x, int y) {
		my_personnage.setMy_x(my_personnage.getMy_x()+x);
		my_personnage.setMy_y(my_personnage.getMy_y()+y);
	}

	// Fonction qui verifie si on touche un monstre avec un coup d'epee
	public boolean toucheMob(int i) {
		boolean retour = false;
		switch(my_personnage.getMy_direction_regard()) {
			case 1: // gauche
				if(my_personnage.getMy_x()-24 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() && my_personnage.getMy_x()-24 >= my_map.getMy_mobs(i).getMy_x()
						&& ( (my_personnage.getMy_y()+20 >= my_map.getMy_mobs(i).getMy_y() && my_personnage.getMy_y()+20 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur() ) 
								|| (my_personnage.getMy_y()+14 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur() && my_personnage.getMy_y()+14 >= my_map.getMy_mobs(i).getMy_y() )))
					retour = true;
				break;
			case 2: // haut
				if( ((my_personnage.getMy_x()+8 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() && my_personnage.getMy_x()+8 >= my_map.getMy_mobs(i).getMy_x() )
						|| (my_personnage.getMy_x()+14 >= my_map.getMy_mobs(i).getMy_x() && my_personnage.getMy_x()+14 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() ) )
						&& my_personnage.getMy_y()-22 >= my_map.getMy_mobs(i).getMy_y() && my_personnage.getMy_y()-22 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur())
					retour = true;
				break;
			case 3: // bas
				if(((my_personnage.getMy_x()+14 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() && my_personnage.getMy_x()+14 >= my_map.getMy_mobs(i).getMy_x() )
						|| (my_personnage.getMy_x()+20 >= my_map.getMy_mobs(i).getMy_x() && my_personnage.getMy_x()+20 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() ) )
						&& my_personnage.getMy_y()+54 >= my_map.getMy_mobs(i).getMy_y() && my_personnage.getMy_y()+54 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur())
					retour = true;
				break;
			case 4: // droite
				if(my_personnage.getMy_x()+54 <= my_map.getMy_mobs(i).getMy_x()+my_map.getMy_mobs(i).getLargeur() && my_personnage.getMy_x()+54 >= my_map.getMy_mobs(i).getMy_x()
						&&( (my_personnage.getMy_y()+20 >= my_map.getMy_mobs(i).getMy_y() && my_personnage.getMy_y()+20 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur() ) 
								|| (my_personnage.getMy_y()+14 <= my_map.getMy_mobs(i).getMy_y()+my_map.getMy_mobs(i).getHauteur() && my_personnage.getMy_y()+14 >= my_map.getMy_mobs(i).getMy_y() )))
					retour = true;
				break;
		}
		return retour;
	}

	// Fonction qui verifie si un monstre est en contact avec link
	public void verifTouche(int direction) {
		// Si il touche
		if(my_map.toucheMonstre(getLinkX(), getLinkY(), LARGEUR, HAUTEUR)) {
			int recule = 60;
			// Empeche link de pouvoir reculer dans un mur
			// On verifie donc si l'espace de recule possible (maximum 30) et on le baisse tant que cela le fait reculer dans un mur
			// Il sera donc colle au mur si un monstre le fait reculer
			switch(direction) {
			case 1:
				while(recule > 0 && !my_map.canDeplace(direction, getLinkX()+recule, getLinkY(), 30, 32))
					recule--;
				my_personnage.setMy_x(getLinkX()+recule);
				break;
			case 2:
				while(recule > 0 && !my_map.canDeplace(direction, getLinkX(), getLinkY()+recule, 30, 32))
					recule--;
				my_personnage.setMy_y(getLinkY()+recule);
				break;
			case 3:
				while(recule > 0 && !my_map.canDeplace(direction, getLinkX(), getLinkY()-recule, 30, 32))
					recule--;
				my_personnage.setMy_y(getLinkY()-recule);
				break;
			case 4:
				while(recule > 0 && !my_map.canDeplace(direction, getLinkX()-recule, getLinkY(), 30, 32))
					recule--;
				my_personnage.setMy_x(getLinkX()-recule);
				break;
			}
			// On fait perdre la vie a Link
			my_personnage.setVie(-1);
			// On joue le son de Link qui se fait toucher
			my_audio.getPlayListElement(5).play();
		}
	}
	
	// Fonction capable de creer des objets speciaux (epee, cle, fragment de coeur, etc...) dans la carte voulue
	public void getSpecialObjets() {
		if(getCurrentMap()==1 && getLinkMapX()==0 && getLinkMapY()==2 && my_personnage.getMy_sword_unlock() == 0) {
			boolean epee=false;
			for(int i=0;i<my_map.getObjets().size();i++)
				if(my_map.getObjets().get(i).getType()==5)
					epee=true;
			if(!epee)
				my_map.creerObjet(5);
		}
	}

	public ArrayList<FormesFixes> getObjets() {
		return my_map.getObjets();
	}

	// Mise en scene de la mort de Link
	public void makeLinkDeath() {
		my_audio.stopAll();
		my_audio.getPlayListElement(3).play();
		my_map.deleteAllMonsters();
		my_map.deleteAllObjects();
		my_fin_jeu = true;
	}

    // Fonction qui renvoit si on est dans la salle d'un boss
    public boolean isSalleBoss() {
    	if(getCurrentMap()==1 && getLinkMapX()==1 && getLinkMapY()==0) {
    		return true;
    	}
    	else if(getCurrentMap()==1 && getLinkMapX()==2 && getLinkMapY()==0) {
    		return true;
    	}
    	else
    		return false;
    }
	
	public boolean stopGame() {
		return my_fin_jeu;
	}

	public String getArme(int i) {
		if(i == 1)
			return my_personnage.getMy_arme_a();
		else
			return my_personnage.getMy_arme_b();
	}

	// Tous les gets et les set
	public int getLinkMapX() {
		return my_personnage.getMy_map_x();
	}

	public int getLinkMapY() {
		return my_personnage.getMy_map_y();
	}

	public int getDirectionLink() {
		return my_personnage.getMy_direction_regard();
	}

	public int getLinkX() {
		return my_personnage.getMy_x();
	}

	public int getLinkY() {
		return my_personnage.getMy_y();
	}

	public ArrayList<Mobs> getMobs() {
		return my_map.getArray();
	}

	public void setMy_nb_deplacement_direction() {
		my_personnage.setMy_nb_deplacement_direction();
	}

	public int getMy_nb_deplacement_direction() {
		return my_personnage.getMy_nb_deplacement_direction();
	}

	public void resetMy_nb_deplacement_direction() {
		my_personnage.resetMy_nb_deplacement_direction();
	}

	public void setLoad() {
		my_map.setLoad();
	}

	public int getCurrentMap() {
		return my_map.getTypeMap();
	}
	
	public void setJustGetSword() {
		my_personnage.setMy_just_get_sword();
	}
	
	public void setJustGetTri() {
		my_personnage.setMy_just_get_triforce();
	}
}