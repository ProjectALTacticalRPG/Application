package units;

import utils.Visitor;

public class PlaneProxy extends SoldierProxy {

	public PlaneProxy(){
		super(new Plane());
	}
	
	public PlaneProxy(String name){
		super(new Plane(name));
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
