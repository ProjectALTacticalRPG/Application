package units;


public abstract class EquippedFighter extends AbstractFighter{

	protected Fighter soldier;
	
	public EquippedFighter(Fighter s) throws Exception {
		Fighter it = s;
		int nb = 1;
		
		while(it instanceof EquippedFighter){
			nb++;
			if(nb > 2)
				throw new Exception("Deux objets déjà équipés");
			
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

}
