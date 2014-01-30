package units;

import utils.Visitor;


public class FighterWithSword extends EquippedFighter{
	
	private int itemDmg;
	private int itemDefense;

	public FighterWithSword(Fighter s) throws Exception {
		super(s);
		this.itemDmg = 40;
		this.itemDefense = 5;
	}
	
	public FighterWithSword(int itemDmg, int itemDefense, AbstractFighter s) throws Exception {
		super(s);
		this.itemDmg = itemDmg;
		this.itemDefense = itemDefense;
	}
	
	@Override
	public int strike() {
		return itemDmg + super.strike();
	}

	@Override
	public void parry(int attack) {
		
		int effAttack = attack - itemDefense;
		if(effAttack > 0)
			super.parry(effAttack);
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
