package units;

import utils.Visitor;

public class TankProxy extends SoldierProxy{


	public TankProxy(){
		super(new Tank());
	}
	
	public TankProxy(String name){
		super(new Tank(name));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
