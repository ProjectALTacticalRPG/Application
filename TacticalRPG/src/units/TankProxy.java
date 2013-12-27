package units;

import utils.Visitor;

public class TankProxy extends SoldierProxy{


	public TankProxy(){
		super(new Tank());
	}
	
	public TankProxy(String name){
		super(new Tank(name));
	}
	
	public TankProxy(String name, int health, int damage, int parry){
		super(new Tank(name, health, damage, parry));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
