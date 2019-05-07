package ntou.cs.java2019.hw3;

import java.util.Random;

public class Pet implements ATK
{
	int maxAttack;
	
	public Pet (int maxAttack)
	{
		if (maxAttack > 0)
		{
			this.maxAttack = maxAttack;
		}	
	}
	@Override
	public double attack() {
		// TODO Auto-generated method stub
		return 0;
	}
}
