package units;


public abstract class EquippedSoldier extends AbstractSoldier{

	protected Soldier soldier;
	
	public EquippedSoldier(Soldier s) throws Exception {
		Soldier it = s;
		int nb = 1;
		
		while(it instanceof EquippedSoldier){
			nb++;
			if(nb > 2)
				throw new Exception("Deux objets déjà équipés");
			
			it = ((EquippedSoldier) it).soldier;
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
