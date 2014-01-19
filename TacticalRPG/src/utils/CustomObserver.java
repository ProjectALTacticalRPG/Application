package utils;

import units.Group;
import units.FighterProxy;

public interface CustomObserver {

	public void update(FighterProxy s, String message);
	public void update(Group a);
	public void update(FighterProxy s);
}
