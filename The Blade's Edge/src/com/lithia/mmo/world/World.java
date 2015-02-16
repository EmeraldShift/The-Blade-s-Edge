package com.lithia.mmo.world;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.lithia.mmo.Renderable;
import com.lithia.mmo.Sprite;
import com.lithia.mmo.entity.Entity;
import com.lithia.mmo.entity.EntityTest;
import com.lithia.mmo.entity.ai.AICommandMoveForward;
import com.lithia.mmo.entity.ai.AIController;

public class World extends Renderable
{
	private static Texture spriteSheet;
	
	private Player _player;
	private int _displayList = -1;
	
	private Perlin _perlin;
	private Entity _test;
	
	public World()
	{
		AIController controller = new AIController();
		AICommandMoveForward move = new AICommandMoveForward();
		
		controller.addCommand(move, "move");
		
		_perlin = new Perlin(System.currentTimeMillis());
		_test = new EntityTest(controller);
	}
	
	public void setPlayer(Player _player)
	{
		this._player = _player;
		_player.setNoise(_perlin);
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
	
	public void updateWorld()
	{
		_test.update();
	}
	
	public void render()
	{
		if(_displayList == -1)
		{
			_displayList = glGenLists(1);
			
			
			glNewList(_displayList, GL_COMPILE);
			glBegin(GL_QUADS);

			for(int x = 0; x < 80; x++)
			{
				for(int z = 0; z < 80; z++)
				{
					Vector2f tex = Sprite.getTexCoordAt(new Random().nextInt(8), 0);

					glTexCoord2f(tex.x, tex.y);
					glVertex3f(x, _perlin.getTerrainHeightAt(x, z), z);
					glTexCoord2f(tex.x + Sprite.SPRITE_SIZE, tex.y);
					glVertex3f(x + 1, _perlin.getTerrainHeightAt(x + 1, z), z);
					glTexCoord2f(tex.x + Sprite.SPRITE_SIZE, tex.y + Sprite.SPRITE_SIZE);
					glVertex3f(x + 1, _perlin.getTerrainHeightAt(x + 1, z + 1), z + 1);
					glTexCoord2f(tex.x, tex.y + Sprite.SPRITE_SIZE);
					glVertex3f(x, _perlin.getTerrainHeightAt(x, z + 1), z + 1);
				}
			}
			
			glEnd();
			glEndList();
		}
		
		_test.render();
		
		glCallList(_displayList);
	}
	
}
