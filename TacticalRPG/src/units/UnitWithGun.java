package units;

import utils.Visitor;

//Pour : soldats de base, tanks standarts, avions de chasse
class UnitWithGun extends EquippedSoldier{

	public UnitWithGun(Soldier s) throws Exception {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
