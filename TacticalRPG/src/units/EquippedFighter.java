package units;


public abstract class EquippedFighter extends AbstractFighter{

	protected Fighter soldier;
	
	public EquippedFighter(Fighter s) throws Exception {
		if(!(s instanceof Link)){
			throw new Exception("Seul Link peut être upgradé.");
		}
		Fighter it = s;
		int nb = 1;
		
		while(it instanceof EquippedFighter){
			nb++;
			if(nb > 1)
				throw new Exception("Un objet déjà équipé");
			
			it = ((EquippedFighter) it).soldier;
		}
		
		soldier = s;
	}
	
	@Override
	public void parry(int attack){
		soldier.parry(attack);
	}
	
	@Override
	public int strike(){
		return soldier.strike();
	}
	
	@Override
	public int getHealth(){
		return soldier.getHealth();
	}
	
	@Override
	public int getMaximumHealth(){
		return soldier.getMaximumHealth();
	}

}
