package units;

import utils.Visitable;

public interface ArmyMember extends Visitable {
	public void parry(int damage);
	public int strike();
	public int getHealth();
	
	public void addGun();
	public void addRocket();
	public String getType();
	boolean isAlive();
	String getName();
}
