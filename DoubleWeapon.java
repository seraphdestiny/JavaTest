package ntou.cs.java2019.hw3;

import java.util.Random;

public class DoubleWeapon extends Weapon {
	private Random random;
	public DoubleWeapon(double offense, double defense) {
		super(offense, defense);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double attack() {
		random = new Random();
		int chance = (int)(random.nextFloat() * 5);
		if(chance % 5 == 1) {						//20% to double attack
			return super.getOffense() * 2;
		}
		else{
			return super.getOffense();
		}
			
	}
}
