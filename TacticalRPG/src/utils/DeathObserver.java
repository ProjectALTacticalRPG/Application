package utils;

import arena.graphics.LinkedEntity;

public interface DeathObserver {
	public void update(LinkedEntity l);
	public void update(LinkedEntity l, int sound);
}
