package testscode.talents;

public class Statistiques {
	
	private double life;
	private double dmg;
	private double cadence_aa;
	private double armure;
	
	public Statistiques(double l, double d, double c, double a) {
		life = l;
		dmg = d;
		cadence_aa = c;
		armure = a;
	}
	
	public double getLife() {
		return life;
	}
	
	public void setLife(double life) {
		this.life = life;
	}
	
	public double getDmg() {
		return dmg;
	}
	
	public void setDmg(double dmg) {
		this.dmg = dmg;
	}
	
	public double getCadence_aa() {
		return cadence_aa;
	}
	
	public void setCadence_aa(double cadence_aa) {
		this.cadence_aa = cadence_aa;
	}
	
	public double getArmure() {
		return armure;
	}
	
	public void setArmure(double armure) {
		this.armure = armure;
	}
	
}
