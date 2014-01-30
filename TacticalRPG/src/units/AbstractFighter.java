package units;

public abstract class AbstractFighter implements Fighter {
	
	private int damage;
	private int currentHealth;
	private int maximumHealth;
	private int parry;
	private int weapons = 0;
	private String name;
	private int previousDmg;
	
	public int getDamage(){
		return damage;
	}
	
	public int getHealth(){
		return currentHealth;
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
		this.currentHealth = health;
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
		return currentHealth > 0;
	}
	
	public int getPreviousDmg(){
		return previousDmg;
	}
	
	public void setPreviousDmg(int previousDmg){
		this.previousDmg = previousDmg;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}
}
