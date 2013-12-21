package units;

import utils.Visitable;

public interface ArmyMember extends Visitable {
	public void parry(int damage);
	public int strike();
	public int getHealth();
	
	public void addSword();
	public void addShield();
	public void removeSword();
	public void removeShield();
	boolean isAlive();
	String getName();
}
