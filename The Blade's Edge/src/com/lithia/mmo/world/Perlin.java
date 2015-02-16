package com.lithia.mmo.world;

import java.util.Random;

public class Perlin
{
	private static int[] noisePerm;
	
	public Perlin(long seed)
	{
		noisePerm = new int[512];
		
		Random rand = new Random(seed);
		
		for(int i = 0; i < noisePerm.length; i++)
		{
			noisePerm[i] = (int) Math.abs(rand.nextInt()) % (noisePerm.length / 2);
		}
	}
	
	public float noise(float x, float y, float z)
	{
		int ix = (int) Math.floor(x), iy = (int) Math.floor(y), iz = (int) Math.floor(z);
		float dx = x - ix, dy = y - iy, dz = z - iz;
		
		ix &= (noisePerm.length - 1);
		iy &= (noisePerm.length - 1);
		iz &= (noisePerm.length - 1);
		
		float w000 = grad(ix, iy, iz, dx, dy, dz);
		float w100 = grad(ix + 1, iy, iz, dx - 1, dy, dz);
		float w010 = grad(ix, iy + 1, iz, dx, dy - 1, dz);
		float w110 = grad(ix + 1, iy + 1, iz, dx - 1, dy - 1, dz);
		float w001 = grad(ix, iy, iz + 1, dx, dy, dz - 1);
		float w101 = grad(ix + 1, iy, iz + 1, dx - 1, dy, dz - 1);
		float w011 = grad(ix, iy + 1, iz + 1, dx, dy - 1, dz - 1);
		float w111 = grad(ix + 1, iy + 1, iz + 1, dx - 1, dy - 1, dz - 1);
		
		float wx = noiseWeight(dx), wy = noiseWeight(dy), wz = noiseWeight(dz);
		
		float x00 = lerp(wx, w000, w100);
		float x10 = lerp(wx, w010, w110);
		float x01 = lerp(wx, w001, w101);
		float x11 = lerp(wx, w011, w111);
		
		float y0 = lerp(wy, x00, x10);
		float y1 = lerp(wy, x01, x11);
		
		return lerp(wz, y0, y1);
	}
	
	private float lerp(float t, float v1, float v2)
	{
		return (1.0f - t) * v1 + t *v2;
	}
	
	private float grad(int x, int y, int z, float dx, float dy, float dz)
	{
		int h = noisePerm[noisePerm[noisePerm[x] + y] + z];
		h &= 15;
		
		float u = h < 8 || h == 12 || h == 13 ? dx : dy;
		float v = h < 4 || h == 12 || h == 13 ? dy : dz;
		
		return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
	}
	
	private float noiseWeight(float t)
	{
		float t3 = t * t * t;
		float t4 = t3 * t;
		
		return 6.0f * t4 * t - 15.0f * t4 + 10.0f * t3;
	}
	
	public float getTerrainHeightAt(float x, float z)
	{
		float res = 0.0f;
		
		res += noise(0.01f * x, 0, 0.01f * z) * 0.5f;
		res += noise(0.04f * x, 0, 0.04f * z) * 0.25;
		
		return res * 64 + 16;
	}
}
