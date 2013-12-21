package units;

import utils.Visitor;

public class HorseMan extends AbstractSoldier {
	
	private int previousDmg;
	
	public HorseMan(String name, int health, int damage, int parry){
		setName(name);
		setHealth(health);
		setDamage(damage);
		setParry(parry);
	}
	
	public HorseMan(){
		setName("Unnamed");
		setHealth(100);
		setDamage(5);
		setParry(10);
	}
	
	public HorseMan(String name){
		setName(name);
		setHealth(100);
		setDamage(5);
		setParry(10);
	}

	@Override
	public int strike() {
		if(getHealth()>0)
			return getDamage();
		else
			return 0;
	}

	@Override
	public void parry(int attack) {
		int damageTaken = attack - getParry();
		if(damageTaken < 0)
			damageTaken = 0;
		setHealth(getHealth() - damageTaken);
		setPreviousDmg(damageTaken);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName(){
		return super.getName();
	}

	public int getPreviousDmg(){
		return previousDmg;
	}
}
