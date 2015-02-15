package com.lithia.mmo;

import org.lwjgl.util.vector.Vector3f;

public abstract class Renderable
{
	protected Vector3f _position = new Vector3f();
	
	public abstract void render();
	
	public void update()
	{
		
	}
}
