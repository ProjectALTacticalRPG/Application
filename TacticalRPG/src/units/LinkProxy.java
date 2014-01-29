package units;

public class LinkProxy extends FighterProxy {
	
	public LinkProxy(){
		super(new Link());
	}
	
	public LinkProxy(String name){
		super(new Link(name));
	}
	
	public LinkProxy(String name, int health, int damage, int parry){
		super(new Link(name, health, damage, parry));
	}
	
	public LinkProxy(int health, int damage, int parry){
		super(new Link(health, damage, parry));
	}
}
