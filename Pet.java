package ntou.cs.java2019.hw3;

import java.util.Random;

public class Pet implements ATK
{
	int maxAttack;
	private Random random;
	
	public Pet (int maxAttack)
	{
		if (maxAttack > 0)
		{
			this.maxAttack = maxAttack;
		}	
	}
	
	public int getMaxAttack() {
		return this.maxAttack;
	}
	
	@Override
	public String toString ()
	{
		return "Pet [maxAttack=" + maxAttack +"]";
	}	
	
	@Override
	public double attack() {
		random = new Random();
		int attack = (int)(1 + (random.nextFloat() * maxAttack));	// 1 ~ maxAttack
		return attack;
	}
}
