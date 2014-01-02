package units;

import utils.Visitable;

interface Soldier extends Visitable {
	
	public int strike();
	public int parry(int attack);
	public int getHealth();
	public String getName();
	public boolean isAlive();
	public int getPreviousDmg();
}
