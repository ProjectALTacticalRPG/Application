package units;

public abstract class AbstractSoldier implements Soldier {
	
	private int damage;
	private int health;
	private int parry;
	private int weapons = 0;
	private String name;
	private int previousDmg;
	
	public int getDamage(){
		return damage;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getParry(){
		return parry;
	}
	
	public String getName(){
		return name;
	}
	
	public int getWeaponNumber(){
		return weapons;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setParry(int parry){
		this.parry = parry;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void addWeapon(){
		weapons++;
	}
	
	public void deleteWeapon(){
		weapons--;
	}
	
	public boolean isAlive(){
		return health > 0;
	}
	
	public int getPreviousDmg(){
		return previousDmg;
	}
	
	public void setPreviousDmg(int previousDmg){
		this.previousDmg = previousDmg;
	}
}
