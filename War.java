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
		
		System.out.printf("�z����l�]�w�G\r\n" + 
				"���aHP�G%d�A�t���@��Z�������O�G%d�B�[���Z�������O�G%d�B�j�O�Z�������O�G%d�APet�����O�G %d\n"
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
			System.out.println("\n�п��: 1.���� 2.���m 3.����: ");							//input select
			scanner = new Scanner(System.in);
			int select = scanner.nextInt();
			
			switch(select) {
				case ATTACK:
				case DEFEND:
					//if select DEFEND, there are 2 event
					int chance = (int)(random.nextFloat() * 2);
					if(chance % 2 == 1) {
						if(select ==  DEFEND) {
							System.out.println("�ۧ���¡�F!");
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
					System.out.printf("���a�������%d�I ! \n",IntStream.of(npcDamage).sum());					
					for(int i = 0; i < player.getequipment().size(); i++) {
						System.out.printf("[%s�y���F%d�I���ˮ`]",player.getequipment().get(i).getClass().getSimpleName(),npcDamage[i]);
					}
					System.out.println();
									
					//count npc's hp
					if(IntStream.of(npcDamage).sum() > npc.getHp()) {
						System.out.printf("���ˤF���I\n");
						battleEnd = true;
						break;
					}
					else {
						System.out.printf("��⪺��q %d �ܦ� %d\n",(int)npc.getHp(),(int)npc.getHp()-IntStream.of(npcDamage).sum());						
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
					System.out.printf("���������a %d �I! \n",playerDamage);
					
					//count player's hp
					if(playerDamage > player.getHp()) {
						System.out.printf("���a�Q���ˤF�I\n");
						battleEnd = true;
						break;
					}
					else {
						System.out.printf("���a����q %d �ܦ� %d\n",(int)player.getHp(),(int)player.getHp()-playerDamage);
						
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
