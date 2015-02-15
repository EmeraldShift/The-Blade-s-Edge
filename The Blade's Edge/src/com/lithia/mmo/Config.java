package com.lithia.mmo;

import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

public class Config
{
	public static final DisplayMode DISPLAY_MODE = new DisplayMode(1280, 720);
	
	public static final String TITLE = "The Blade's Edge";
	public static final String VERSION = "0.01a";
	
	public static final float MOVEMENT_SPEED = 0.15f;
	public static final float GRAVITY = 0.01f;
	public static final float PLAYER_HEIGHT = 1.7f;
	
	public static final Vector3f SPAWN_LOCATION = new Vector3f(0.0f, 12.0f, 0.0f);

	public static final float JUMP_VELOCITY = 0.5f;
}
