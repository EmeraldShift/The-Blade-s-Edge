package com.lithia.mmo.entity;

import com.lithia.mmo.entity.ai.AIController;

public abstract class EntityLiving extends Entity
{
	public float _moveSpeed;
	protected AIController _controller;
	
	public EntityLiving(float moveSpeed, AIController controller)
	{
		this._moveSpeed = moveSpeed;
		this._controller = controller;
	}

	public void update()
	{
		_controller.executeCommands(this);
	}
	
	public void move(float x, float z)
	{
		_position.x += x;
		_position.z += z;
	}
	
	public void moveTo(float x, float z)
	{
		_position.x = x;
		_position.z = z;
	}
	
}
