package units;

import utils.Visitor;

public class HorseManProxy extends SoldierProxy {
	public HorseManProxy(){
		super(new HorseMan());
	}
	
	public HorseManProxy(String name){
		super(new HorseMan(name));
	}
	
	public HorseManProxy(String name, int health, int damage, int parry){
		super(new HorseMan(name, health, damage, parry));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
