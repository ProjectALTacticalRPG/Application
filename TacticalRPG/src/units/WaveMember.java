package units;

public interface WaveMember {

	public int getHealth();
	public int getMaximumHealth();
	public boolean isAlive();
	public void addSword();
	public void removeSword();
	public int strike();
	public void parry(int attack);
}
