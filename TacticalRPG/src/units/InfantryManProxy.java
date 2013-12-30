package units;

import utils.Visitor;

public class InfantryManProxy extends SoldierProxy {
	
	public InfantryManProxy(){
		super(new InfantryMan());
	}
	
	public InfantryManProxy(String name){
		super(new InfantryMan(name));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
