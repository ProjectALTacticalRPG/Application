package units;

import utils.Visitable;

public interface ArmyMember extends Visitable {
	public void parry(int damage);
	public int strike();
	public int getHealth();
	
	public void addGun();
	public void addRocket();
	boolean isAlive();
	String getName();
}
