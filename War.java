package ntou.cs.java.rpg;

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
	}

	public void battle ()
	{
	}
}
