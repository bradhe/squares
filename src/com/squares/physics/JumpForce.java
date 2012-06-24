package com.squares.physics;

import org.lwjgl.util.vector.Vector3f;

import com.squares.SceneNode;

public class JumpForce extends Force {

	public JumpForce(SceneNode node, long startingTick) {
		super(node, startingTick);
	}

	@Override
	public Vector3f calculate() {
		float y = 0.05f * (this.getCurrentTick() / this.getStartingTick());
		
		return new Vector3f(0, y, 0);
	}

}
