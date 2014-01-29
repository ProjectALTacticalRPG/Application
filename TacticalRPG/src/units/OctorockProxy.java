package units;

public class OctorockProxy extends FighterProxy {
	
	public OctorockProxy(){
		super(new Octorock());
	}
	
	public OctorockProxy(String name){
		super(new Octorock(name));
	}
	
	public OctorockProxy(String name, int health, int damage, int parry){
		super(new Octorock(name, health, damage, parry));
	}
}
