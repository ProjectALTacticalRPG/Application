package units;

import utils.Visitor;

public class MoblinProxy extends FighterProxy {
	
	public MoblinProxy(){
		super(new Moblin());
	}
	
	public MoblinProxy(String name){
		super(new Moblin(name));
	}
	
	public MoblinProxy(String name, int health, int damage, int parry){
		super(new Moblin(name, health, damage, parry));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
