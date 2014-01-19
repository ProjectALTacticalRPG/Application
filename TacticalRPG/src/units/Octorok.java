package units;

import utils.Visitor;

public class Octorok extends AbstractFighter{
	
		public Octorok(String name, int health, int damage, int parry) {
			setHealth(health);
			setDamage(damage);
			setParry(parry);
			setName(name);
		}
		
		public Octorok(){
			setHealth(75);
			setDamage(1);
			setParry(5);
			setName("Octorok");
		}
		
		public Octorok(String name){
			setHealth(50);
			setDamage(1);
			setParry(5);
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
