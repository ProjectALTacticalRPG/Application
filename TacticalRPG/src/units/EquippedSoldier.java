package units;


public abstract class EquippedSoldier extends AbstractSoldier{

	protected Soldier soldier;

	public EquippedSoldier(Soldier s) throws Exception {
		if(s instanceof EquippedSoldier)
			throw new Exception("L'unit� est d�j� �quip�e");

		soldier = s;
	}

	@Override
	public int parry(int attack){
		return soldier.parry(attack);
	}

	@Override
	public int strike(){
		return soldier.strike();
	}

}
