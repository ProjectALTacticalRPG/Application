package units;

import utils.Visitor;


public class FighterWithShield extends EquippedFighter{

	private int itemDefense;
	
	public FighterWithShield(int itemDefense, Fighter s) throws Exception {
		super(s);
		this.itemDefense = itemDefense;
	}
	
	public FighterWithShield(Fighter s) throws Exception {
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
