package com.squares.physics;

import org.lwjgl.util.vector.Vector3f;

import com.squares.SceneNode;

public abstract class ConstantForce extends Force {
	public ConstantForce(SceneNode node, long startingTick) {
		super(node, startingTick);
	}
}
