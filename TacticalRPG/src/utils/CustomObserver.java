package utils;

import units.Wave;
import units.FighterProxy;

public interface CustomObserver {

	public void update(FighterProxy s, String message);
	public void update(Wave a);
	public void update(FighterProxy s);
}
