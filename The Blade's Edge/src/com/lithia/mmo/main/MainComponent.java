package com.lithia.mmo.main;

import com.lithia.mmo.Game;

public class MainComponent
{
	public static void main(String[] args)
	{
		Game game = new Game();
		
		try
		{
			game.create();
			game.run();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
