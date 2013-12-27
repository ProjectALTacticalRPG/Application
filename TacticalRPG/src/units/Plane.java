package units;

import utils.Visitor;

public class Plane extends AbstractSoldier{

	public Plane(String name, int health, int damage, int parry) {
		setHealth(health);
		setDamage(damage);
		setParry(parry);
		setName(name);
	}
	
	//TODO equilibrer les valeurs
	public Plane(){
		setHealth(50);
		setDamage(1);
		setParry(5);
		setName("Infantry man");
	}
	
	public Plane(String name){
		setHealth(50);
		setDamage(1);
		setParry(5);
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
