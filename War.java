package ntou.cs.java2019.hw3;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class War
{
	public static int ATTACK = 1;
	public static int DEFEND = 2;
	public static int END = 3;

	private Character npc;
	private Character player;
	private Random random;

	public War ()
	{
		random = new Random();
		init();
	}

	private void init ()
	{
		int hp;
		
		//set player data
		hp = (int)(150 +  (random.nextFloat() * 50));			//150~199
		Weapon w[] = new Weapon[4];
		w[0] = new NormalWeapon( (int)(20 + (random.nextFloat() * 40)) ,0);	//20~59
		w[1] = new NormalWeapon( (int)(25 + (random.nextFloat() * 35)) ,0);	//25~59
		w[2] = new NormalWeapon( (int)(30 + (random.nextFloat() * 30)) ,0);	//30~59
		w[3] = new NormalWeapon( (int)(50 + (random.nextFloat() * 40)) ,0);	//50~99
		
		ArrayList<ATK> equipment = new ArrayList<ATK>(4);
		equipment.add(w[0]);
		equipment.add(w[1]);
		equipment.add(w[2]);
		equipment.add(w[3]);
		
		player = new Character(hp, equipment);
/*		
		//set npc data
		int hp2 = 1000 - hp;									//1000 - player's hp
		Weapon w2[] = new Weapon[4];
		w2[0].setOffense( 20 + (random.nextInt() * 40) );	//20~59

		
		ArrayList<ATK> equipment2 = new ArrayList<ATK>(4);
		equipment2.add(w[0]);
		player = new Character(hp, equipment);
*/		
		System.out.printf("您的初始設定：\r\n" + 
				"玩家HP：%d，配戴一般武器攻擊力：%f、加倍武器攻擊力：%f、強力武器攻擊力：%f，Pet攻擊力： %f\n"
				,hp,w[0].getOffense(),w[1].getOffense(),w[2].getOffense(),w[3].getOffense());
	}

	public void battle ()
	{
		System.out.println("請選擇: 1.攻擊 2.防禦 3.結束: ");
	}
}
