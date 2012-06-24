package com.squares.physics;

import org.lwjgl.util.vector.Vector3f;

import com.squares.SceneNode;

public class GravityForce extends Force {
	public final float GRAVITY_CONSTANT = 0.5f;
	
	public GravityForce(SceneNode node, long startingTick) {
		super(node, startingTick);
	}

	@Override
	public Vector3f calculate() {
		float t = (getCurrentTick() - getStartingTick()) / 1000.0f;
		float a = GRAVITY_CONSTANT * t;
		
		// -1.0f == downward.
		float y = -1.0f * (float)Math.pow(a, 2.0);
		return new Vector3f(0, y, 0);
	}
}
