package ntou.cs.java2019.hw3;

public class PowerWeapon extends Weapon{

	public PowerWeapon(double offense, double defense) {
		super(offense, defense);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double attack() {
		return super.getOffense()*1.2;	//attack * 1.2
	}
}
