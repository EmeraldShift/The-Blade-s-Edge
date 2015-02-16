package com.lithia.mmo;

import org.lwjgl.util.vector.Vector2f;

public class Sprite
{
	private static final int SPRITESHEET_DIMENSION = 16;
	public static final float SPRITE_SIZE = 1 / (float) SPRITESHEET_DIMENSION;
	
	public static Vector2f getTexCoordAt(int x, int y)
	{
		return new Vector2f(x / (float) SPRITESHEET_DIMENSION, y / (float) SPRITESHEET_DIMENSION);
	}
}
