package com.squares.math;

public class Vector {
	public float x;
	public float y;
	public float z;
	
	public Vector() {
		this(0, 0, 0);
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector copy() {
		return new Vector(x, y, z);
	}
	
	public Vector add(Vector right) {
		return new Vector(x + right.x, y + right.y, z + right.z);
	}
	
	public static Vector zero = new Vector(0, 0, 0);
}
