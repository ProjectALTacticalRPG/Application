package units;

import utils.Visitor;

public class KeatonProxy extends FighterProxy {
	
	public KeatonProxy(){
		super(new Keaton());
	}
	
	public KeatonProxy(String name){
		super(new Keaton(name));
	}
	
	public KeatonProxy(String name, int health, int damage, int parry){
		super(new Keaton(name, health, damage, parry));
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
