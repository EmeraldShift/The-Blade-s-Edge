package com.lithia.mmo.entity;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector3f;

import com.lithia.mmo.entity.ai.AICommandMoveForward;
import com.lithia.mmo.entity.ai.AIController;

public class EntityTest extends EntityLiving
{
	
	public EntityTest(AIController controller)
	{
		super(0.1f, controller);
		
		_position = new Vector3f(0, 0, 0);
	}
	
	public void draw()
	{
		glColor3f(1.0f, 1.0f, 1.0f);
		
		glBegin(GL_QUADS);
		
		glVertex3f(1.0f,	 1.0f,	-1.0f);
		glVertex3f(-1.0f,	 1.0f,	-1.0f);
		glVertex3f(-1.0f,	 1.0f,	 1.0f);
		glVertex3f(1.0f,	 1.0f,	 1.0f);

		glVertex3f(1.0f,	-1.0f,	 1.0f);
		glVertex3f(-1.0f,	-1.0f,	 1.0f);
		glVertex3f(-1.0f,	-1.0f,	-1.0f);
		glVertex3f(1.0f,	-1.0f,	-1.0f);
		
		glVertex3f(1.0f,	 1.0f,	 1.0f);
		glVertex3f(-1.0f,	 1.0f,	 1.0f);
		glVertex3f(-1.0f,	-1.0f,	 1.0f);
		glVertex3f(1.0f,	-1.0f,	 1.0f);
		
		glVertex3f(1.0f,	-1.0f,	-1.0f);
		glVertex3f(-1.0f,	-1.0f,	-1.0f);
		glVertex3f(-1.0f,	 1.0f,	-1.0f);
		glVertex3f(1.0f,	 1.0f,	-1.0f);
		
		glVertex3f(-1.0f,	 1.0f,	 1.0f);
		glVertex3f(-1.0f,	 1.0f,	-1.0f);
		glVertex3f(-1.0f,	-1.0f,	-1.0f);
		glVertex3f(-1.0f,	-1.0f,	 1.0f);
		
		glVertex3f(1.0f,	 1.0f,	-1.0f);
		glVertex3f(1.0f,	 1.0f,	 1.0f);
		glVertex3f(1.0f,	-1.0f,	 1.0f);
		glVertex3f(1.0f,	-1.0f,	-1.0f);
		
		glEnd();
	}
	
	public void update()
	{
		_controller.executeCommands(this);
	}
}
