package com.squares.physics;

import org.lwjgl.util.vector.Vector3f;

import com.squares.SceneNode;

public class GravityForce extends ConstantForce {
	public final float GRAVITY_CONSTANT = 0.9f;
	
	public GravityForce(SceneNode node, long startingTick) {
		super(node, startingTick);
	}

	@Override
	public Vector3f calculate() {
		float t = (getCurrentTick() - getStartingTick()) / 1000.0f;
		float a = GRAVITY_CONSTANT * t;
		
		if(a < -0.1f) {
			a = -0.1f;
		}
		
		// -1.0f == downward.
		float y = -1.0f * (float)Math.pow(a, 2.0);
		return new Vector3f(0, y, 0);
	}
}
