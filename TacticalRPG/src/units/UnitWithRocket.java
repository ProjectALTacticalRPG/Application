package units;

import utils.Visitor;

//Pour : tanks anti aerien, soldats anti char, avions bombardiers

public class UnitWithRocket extends EquippedSoldier{

	public UnitWithRocket(Soldier s) throws Exception {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
