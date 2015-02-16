package com.lithia.mmo.entity.ai;

import com.lithia.mmo.entity.EntityLiving;

public abstract class AICommand
{
	protected COMMAND_TYPE type;
	
	protected enum COMMAND_TYPE
	{
		MOVE, HALT;
	}
	
	public AICommand(COMMAND_TYPE type)
	{
		this.type = type;
	}
	
	public abstract void execute(EntityLiving entity);
}
