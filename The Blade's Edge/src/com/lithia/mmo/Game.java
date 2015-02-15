package com.lithia.mmo;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.lithia.mmo.world.Player;
import com.lithia.mmo.world.World;

public class Game
{
	private World _world;
	private Player _player;
	
	public void create() throws LWJGLException
	{
		Display.setDisplayMode(Config.DISPLAY_MODE);
		Display.setTitle(Config.TITLE);
		Display.create();
		
		Keyboard.create();
		Mouse.create();
		Mouse.setGrabbed(true);
		
		init();
		resizeDisplay();
	}
	
	private void init()
	{
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_FOG);
		
		glFogf(GL_FOG_DENSITY, 0.1f);
		glFogi(GL_FOG_START, 128);
		glFogi(GL_FOG_END, 1024);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		World.init();
		
		_world = new World();
		_player = new Player(_world);
		_world.setPlayer(_player);
	}
	
	private void resizeDisplay()
	{
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		gluPerspective(67.0f, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1024f);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public void run()
	{
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			update();
			render();
		}
	}
	
	private void update()
	{
		_player.update();
	}
	
	private void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		_player.render();
		_world.render();
		
		Display.update();
		Display.sync(60);
	}
}
