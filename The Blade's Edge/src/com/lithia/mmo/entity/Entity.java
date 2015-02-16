package com.lithia.mmo.entity;

import static org.lwjgl.opengl.GL11.*;

import com.lithia.mmo.Renderable;

public abstract class Entity extends Renderable
{
	public float _yaw;
	public float _pitch;
	protected int _displayList = -1;
	
	public void init()
	{
	}
	
	public void render()
	{
		if(_displayList == -1)
		{
			_displayList = glGenLists(1);
			glNewList(_displayList, GL_COMPILE);
			glDisable(GL_TEXTURE_2D);
			
			draw();
			
			glEnable(GL_TEXTURE_2D);
			glEndList();
		}
		
		glPushMatrix();
		glTranslatef(_position.x, _position.y, _position.z);
		glRotatef(_yaw, 0, 1, 0);
		glRotatef(_pitch, 1, 0, 0);

		glCallList(_displayList);
		
		glPopMatrix();
	}
	
	protected void updateDisplayList()
	{
		_displayList = -1;
		glDeleteLists(_displayList, 1);
	}
	
	public abstract void draw();
	public abstract void update();
}
