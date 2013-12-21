package utils;

import units.Army;
import units.SoldierProxy;

public interface CustomObserver {

	public void update(SoldierProxy s, String message);
	public void update(Army a);
	public void update(SoldierProxy s);
}
