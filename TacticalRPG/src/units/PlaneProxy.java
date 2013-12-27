package units;

import utils.Visitor;

public class PlaneProxy extends SoldierProxy {

	public PlaneProxy(){
		super(new Plane());
	}
	
	public PlaneProxy(String name){
		super(new Plane(name));
	}
	
	public PlaneProxy(String name, int health, int damage, int parry){
		super(new Plane(name, health, damage, parry));
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
