package com.lithia.mmo.world;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.lithia.mmo.Config;
import com.lithia.mmo.Renderable;

public class Player extends Renderable
{
	private World _parent;
	private Vector3f _accVector = new Vector3f();
	private float _yaw;
	private float _pitch;
	private float _gravity;
	
	private Perlin _noise;
	
	public Player(World _parent)
	{
		this._parent = _parent;
		
		_position = Config.SPAWN_LOCATION;
	}
	
	public void update()
	{
		updatePosition();
	}
	
	public void setNoise(Perlin _noise)
	{
		this._noise = _noise;
	}
	
	public void render()
	{
		glRotatef(_pitch, 1, 0, 0);
		glRotatef(_yaw, 0, 1, 0);
		glTranslatef(-_position.x, -_position.y - Config.PLAYER_HEIGHT, -_position.z);
	}
	
	private void updatePosition()
	{
		_yaw += Mouse.getDX() / 10.0f;
		_pitch -= Mouse.getDY() / 10.0f;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			_accVector.z -= Math.cos(Math.toRadians(_yaw)) * Config.MOVEMENT_SPEED;
			_accVector.x += Math.sin(Math.toRadians(_yaw)) * Config.MOVEMENT_SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			_accVector.z += Math.cos(Math.toRadians(_yaw)) * Config.MOVEMENT_SPEED;
			_accVector.x -= Math.sin(Math.toRadians(_yaw)) * Config.MOVEMENT_SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			_accVector.z += Math.cos(Math.toRadians(_yaw + 90)) * Config.MOVEMENT_SPEED;
			_accVector.x -= Math.sin(Math.toRadians(_yaw + 90)) * Config.MOVEMENT_SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			_accVector.z += Math.cos(Math.toRadians(_yaw - 90)) * Config.MOVEMENT_SPEED;
			_accVector.x -= Math.sin(Math.toRadians(_yaw - 90)) * Config.MOVEMENT_SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && _gravity == 0)
		{
			_gravity += Config.JUMP_VELOCITY;
		}
		
		_gravity -= Config.GRAVITY;
		_accVector.y += _gravity;
		
		_position = Vector3f.add(_accVector, _position, null);
		_accVector = new Vector3f();
		
		float height = _noise.getTerrainHeightAt(_position.x, _position.z);
		
		if(_position.y <= height)
		{
			_gravity = 0;
			_position.y = height;
		}
	}
	
}
