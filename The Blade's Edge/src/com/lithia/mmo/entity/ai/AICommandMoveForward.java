package com.lithia.mmo.entity.ai;

import org.lwjgl.util.vector.Vector3f;

import com.lithia.mmo.Config;
import com.lithia.mmo.entity.EntityLiving;

public class AICommandMoveForward extends AICommand
{
	private Vector3f _move;
	
	public AICommandMoveForward()
	{
		super(COMMAND_TYPE.MOVE);
		
		_move = new Vector3f();
	}
	
	public void execute(EntityLiving entity)
	{
		float x = (float) Math.sin(Math.toRadians(entity._yaw)) * entity._moveSpeed;
		float z = (float) Math.cos(Math.toRadians(entity._yaw)) * entity._moveSpeed;
		
		entity._yaw += 2;
		
		entity.move(x, z);
	}
	
}
