package units;

import utils.Visitable;

public interface Soldier extends Visitable {
	
	public int strike();
	public void parry(int attack);
	public int getHealth();
	public String getName();
	public boolean isAlive();
	public int getPreviousDmg();
}
