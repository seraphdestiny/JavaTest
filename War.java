package ntou.cs.java2019.hw3;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;

public class War
{
	private static Scanner scanner;
	
	public static final int ATTACK = 1;
	public static final int DEFEND = 2;
	public static final int END = 3;

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
		hp = (int)(150 +  (random.nextFloat() * 50));									//HP:150~199
		Weapon playerWeapon[] = new Weapon[3];											//4 different select weapon
		playerWeapon[0] = new NormalWeapon( (int)(20 + (random.nextFloat() * 40)) ,0);	//NormalWeapon:20~59
		playerWeapon[1] = new DoubleWeapon( (int)(25 + (random.nextFloat() * 35)) ,0);	//DoubleWeapon:25~59
		playerWeapon[2] = new PowerWeapon( (int)(30 + (random.nextFloat() * 30)) ,0);	//PowerWeapon:30~59
		Pet p = new Pet( (int)(50 + (random.nextFloat() * 40)));						//Pet:50~99
		
		ArrayList<ATK> equipment = new ArrayList<ATK>(4);								//4 weapon insert a equipment
		equipment.add(playerWeapon[0]);
		equipment.add(playerWeapon[1]);
		equipment.add(playerWeapon[2]);
		equipment.add(p);
		
		player = new Character(hp, equipment);
		
		System.out.printf("您的初始設定：\r\n" + 
				"玩家HP：%d，配戴一般武器攻擊力：%d、加倍武器攻擊力：%d、強力武器攻擊力：%d，Pet攻擊力： %d\n"
				,(int)hp,(int)playerWeapon[0].getOffense(),(int)playerWeapon[1].getOffense(),(int)playerWeapon[2].getOffense(),p.getMaxAttack());
			
		//set npc data
		Weapon npcWeapon = new NormalWeapon( (int)(20 + (random.nextFloat() * 40)) ,0);	//NormalWeapon:20~59		
		ArrayList<ATK> equipment2 = new ArrayList<ATK>();								//1 weapon insert a equipment
		equipment2.add(npcWeapon);
		npc = new Character(1000 - hp, equipment2);										//npc'HP:1000 - player's HP
			
	}

	public void battle ()
	{
		boolean battleEnd = false;														//decide whether battle is over
		
		while(battleEnd != true) {
			System.out.println("\n請選擇: 1.攻擊 2.防禦 3.結束: ");							//input select
			scanner = new Scanner(System.in);
			int select = scanner.nextInt();
			
			switch(select) {
				case ATTACK:
				case DEFEND:
					//if select DEFEND, there are 2 event
					int chance = (int)(random.nextFloat() * 2);
					if(chance % 2 == 1) {
						if(select ==  DEFEND) {
							System.out.println("自我療癒了!");
						}
					}
					
					//player's round					
					int[] npcDamage = new int[player.getequipment().size()];
					//ATTACK : count player's attacks					
					if(select ==  ATTACK) {
						for(int i = 0; i < player.getequipment().size(); i++) {
							npcDamage[i] = (int)player.getequipment().get(i).attack();
						}
					}
					//DEFEND : not attack					
					else if(select == DEFEND) {

					}
					//show damage of player's attacks
					System.out.printf("玩家攻擊對手%d點 ! \n",IntStream.of(npcDamage).sum());					
					for(int i = 0; i < player.getequipment().size(); i++) {
						System.out.printf("[%s造成了%d點的傷害]",player.getequipment().get(i).getClass().getSimpleName(),npcDamage[i]);
					}
					System.out.println();
									
					//count npc's hp
					if(IntStream.of(npcDamage).sum() > npc.getHp()) {
						System.out.printf("擊倒了對手！\n");
						battleEnd = true;
						break;
					}
					else {
						System.out.printf("對手的血從 %d 變成 %d\n",(int)npc.getHp(),(int)npc.getHp()-IntStream.of(npcDamage).sum());						
					}
					npc.setHp(npc.getHp() - IntStream.of(npcDamage).sum());

					
					//npc's round
					//count npc's attack
					int playerDamage = (int)npc.getequipment().get(0).attack();
					//ATTACK : normal damage
					if(select ==  ATTACK) {
						
					}
					//DEFEND : player guard or heal
					else if(select == DEFEND) {
						if(chance % 2 == 1) {
							playerDamage = -playerDamage;
						}
						else {						
							playerDamage = playerDamage / 2;
						}
					}
					//show damage of npc's attacks
					System.out.printf("對手攻擊玩家 %d 點! \n",playerDamage);
					
					//count player's hp
					if(playerDamage > player.getHp()) {
						System.out.printf("玩家被擊倒了！\n");
						battleEnd = true;
						break;
					}
					else {
						System.out.printf("玩家的血從 %d 變成 %d\n",(int)player.getHp(),(int)player.getHp()-playerDamage);
						
					}
					player.setHp(player.getHp() - playerDamage);
					
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Unknown input!");
			}
		}
	}
}
