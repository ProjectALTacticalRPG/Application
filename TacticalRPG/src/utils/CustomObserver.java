package utils;

import units.Wave;
import units.FighterProxy;

//Non utilis�e dans ce jeu
public interface CustomObserver {

	public void update(FighterProxy s, String message);
	public void update(Wave a);
	public void update(FighterProxy s);
}
