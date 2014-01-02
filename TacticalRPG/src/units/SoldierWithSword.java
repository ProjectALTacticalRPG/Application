package units;

import utils.Visitor;


public class SoldierWithSword extends EquippedSoldier{
	
	private int itemDmg;
	private int itemDefense;

	public SoldierWithSword(Soldier s) throws Exception {
		super(s);
		this.itemDmg = 50;
		this.itemDefense = 5;
	}
	
	public SoldierWithSword(int itemDmg, int itemDefense, AbstractSoldier s) throws Exception {
		super(s);
		this.itemDmg = itemDmg;
		this.itemDefense = itemDefense;
	}
	
	@Override
	public int strike() {
		return itemDmg + super.strike();
	}

	@Override
	public int parry(int attack) {
		
		int effAttack = attack - itemDefense;
		if(effAttack > 0)
			super.parry(effAttack);
		return 0;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return soldier.getName();
	}

}
