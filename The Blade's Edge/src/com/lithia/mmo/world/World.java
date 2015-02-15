package com.lithia.mmo.world;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.lithia.mmo.Renderable;
import com.lithia.mmo.Sprite;

public class World extends Renderable
{
	private static Texture spriteSheet;
	
	private Player _player;
	private int _displayList = -1;
	
	public void setPlayer(Player _player)
	{
		this._player = _player;
	}
	
	public static void init()
	{
		try
		{
			spriteSheet = TextureLoader.getTexture("PNG", ResourceLoader.getResource("tiles.png").openStream(), GL_NEAREST);
			spriteSheet.bind();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void render()
	{
		if(_displayList == -1)
		{
			_displayList = glGenLists(1);
			
			Vector2f tex = Sprite.getTexCoordAt(0, 0);
			
			glNewList(_displayList, GL_COMPILE);
			glBegin(GL_QUADS);
			glTexCoord2f(tex.x, tex.y);
			glVertex3f(-16, -1, 16);
			glTexCoord2f(tex.x + Sprite.SPRITE_SIZE, tex.y);
			glVertex3f(16, -1, 16);
			glTexCoord2f(tex.x + Sprite.SPRITE_SIZE, tex.y + Sprite.SPRITE_SIZE);
			glVertex3f(16, -1, -16);
			glTexCoord2f(tex.x, tex.y + Sprite.SPRITE_SIZE);
			glVertex3f(-16, -1, -16);
			glEnd();
			glEndList();
		}
		
		glCallList(_displayList);
	}
	
}
