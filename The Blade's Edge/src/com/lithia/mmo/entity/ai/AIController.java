package com.lithia.mmo.entity.ai;

import java.util.HashMap;

import com.lithia.mmo.entity.EntityLiving;

public class AIController
{
	public HashMap<String, AICommand> commands;
	
	public AIController()
	{
		commands = new HashMap<String, AICommand>();
	}
	
	public void addCommand(AICommand command, String tag)
	{
		commands.put(tag, command);
	}
	
	public AICommand getCommand(String tag)
	{
		return commands.get(tag);
	}
	
	public void executeCommands(EntityLiving entity)
	{
		for(AICommand command : commands.values())
		{
			command.execute(entity);
		}
	}
}
