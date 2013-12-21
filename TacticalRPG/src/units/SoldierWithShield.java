package units;

import utils.Visitor;


public class SoldierWithShield extends EquippedSoldier{

	private int itemDefense;
	
	public SoldierWithShield(int itemDefense, Soldier s) throws Exception {
		super(s);
		this.itemDefense = itemDefense;
	}
	
	public SoldierWithShield(Soldier s) throws Exception {
		super(s);
		this.itemDefense = 20;
	}
	
	@Override
	public int strike() {
		return super.strike();
	}

	@Override
	public void parry(int attack) {
		int effAttack = attack - itemDefense;
		if(effAttack > 0)
			super.parry(effAttack);
		else
			System.out.println("Attaque parée");
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
