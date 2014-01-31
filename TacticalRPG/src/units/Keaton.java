package units;

import utils.Visitor;

public class Keaton extends AbstractFighter {

	public Keaton(String name, int health, int damage, int parry) {
		setHealth(health);
		setMaximumHealth(health);
		setDamage(damage);
		setParry(parry);
		setName(name);
	}
	
	public Keaton(){
		setHealth(50);
		setMaximumHealth(50);
		setDamage(4);
		setParry(0);
		setName("Keaton");
	}
	
	public Keaton(String name){
		setHealth(50);
		setMaximumHealth(50);
		setDamage(4);
		setParry(0);
		setName(name);
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
		int dmgTaken = attack - getParry();
		if(dmgTaken < 0)
			dmgTaken = 0;
		setHealth(getHealth() - dmgTaken);
		setPreviousDmg(dmgTaken);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName(){
		return super.getName();
	}
}
