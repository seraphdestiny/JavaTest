package ntou.cs.java2019.hw3;

public class NormalWeapon extends Weapon{

	public NormalWeapon(double offense, double defense) {
		super(offense, defense);
	}

	@Override
	public double attack() {
		return super.getOffense();
	}

}