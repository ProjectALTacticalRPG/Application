package units;

import utils.Visitor;

public class Tank extends AbstractSoldier{

	public Tank(String name, int health, int damage, int parry) {
		setHealth(health);
		setDamage(damage);
		setParry(parry);
		setName(name);
	}
	
	//TODO equilibrer valeurs
	public Tank(){
		setHealth(50);
		setDamage(1);
		setParry(5);
		setName("Tank");
	}
	
	public Tank(String name){
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
