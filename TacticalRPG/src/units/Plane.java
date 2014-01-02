package units;

import utils.Visitor;

class Plane extends AbstractSoldier{
	
	//TODO equilibrer les valeurs
	public Plane(){
		setHealth(1);
		setDamage(1);
		setParry(0);
		setName("Infantry man");
	}
	
	public Plane(String name){
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
	public int parry(int attack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
