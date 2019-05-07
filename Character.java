package ntou.cs.java2019.hw3;

import java.util.ArrayList;

public class Character
{
	public static double MAX_HP = 999;
	public static int DEFAULT_HP = 200;
	public static int DEFAULT_ATK = 50;

	private double hp;
	private ArrayList<ATK> equipment = new ArrayList<ATK>();

	public Character ()
	{
		setHp(DEFAULT_HP);		
		//this.equipment.add(ATK);		
	}

	public Character (double hp)
	{
		setHp(hp);	
		Weapon w;
		w = new NormalWeapon(0,0);
		equipment.add(w);
	}
	

	public Character (double hp, ArrayList<ATK> equipment)
	{
		setHp(hp);
		setequipment(equipment);
	}

	public double getHp ()
	{
		return hp;
	}

	public void setHp (double hp)
	{
		if ((0 < hp) && (hp < MAX_HP))
			this.hp = hp;
		else this.hp = 0;
	}

	public ArrayList<ATK> getequipment ()
	{
		return equipment;
	}

	public void setequipment (ArrayList<ATK> equipment)
	{
		this.equipment = equipment;
	}

	@Override
	public String toString ()
	{
		return "Character [hp=" + hp + ", equipment=" + equipment + "]";
	}
}