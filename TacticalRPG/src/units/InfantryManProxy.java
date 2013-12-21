package units;

import utils.Visitor;

public class InfantryManProxy extends SoldierProxy {
	
	public InfantryManProxy(){
		super(new InfantryMan());
	}
	
	public InfantryManProxy(String name){
		super(new InfantryMan(name));
	}
	
	public InfantryManProxy(String name, int health, int damage, int parry){
		super(new InfantryMan(name, health, damage, parry));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
