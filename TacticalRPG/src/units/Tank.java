package units;

import utils.Visitor;

class Tank extends AbstractSoldier{

	//TODO equilibrer valeurs
	public Tank(){
		setHealth(1);
		setDamage(1);
		setParry(0);
		setName("Tank");
	}
	
	public Tank(String name){
		setHealth(1);
		setDamage(1);
		setParry(0);
		setName(name);
	}

	@Override
	public int strike() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void parry(int attack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
