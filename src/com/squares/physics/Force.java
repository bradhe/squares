package com.squares.physics;

import org.lwjgl.util.vector.Vector3f;

import com.squares.SceneNode;

public abstract class Force {
	private long startingTick;
	private long currentTick;
	
	public long getStartingTick() {
		return startingTick;
	}

	public void setStartingTick(long startingTick) {
		this.startingTick = startingTick;
	}

	public long getCurrentTick() {
		return currentTick;
	}

	public void setCurrentTick(long currentTick) {
		this.currentTick = currentTick;
	}

	public SceneNode getSceneNode() {
		return sceneNode;
	}

	public void setSceneNode(SceneNode sceneNode) {
		this.sceneNode = sceneNode;
	}

	private SceneNode sceneNode;
	
	public Force(SceneNode node, long startingTick) {
		this.sceneNode = node;
		this.startingTick = startingTick;
		this.currentTick = startingTick;
	}
	
	public void update(long ticks) {
		this.currentTick += ticks;
	}
	
	/**
	 * Calculate and apply the current force to the element.
	 */
	public abstract Vector3f calculate();
}
