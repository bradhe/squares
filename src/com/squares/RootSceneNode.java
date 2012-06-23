package com.squares;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.squares.math.Rect;

public class RootSceneNode extends CollidableSceneNode {
	public RootSceneNode() {
		this.initializeTransform();
	}
	
	@Override
	public void update(long ticks) {
		for(RenderableSceneNode node : getRenderableChildren()) {
			node.update(ticks);
		}
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		this.applyTransform();
		
		for(RenderableSceneNode node : getRenderableChildren()) {
			node.render();
		}
		
		GL11.glPopMatrix();
	}

	@Override
	public Vector3f getCenter() {
		return new Vector3f(0, 0, 0);
	}
	
	@Override
	public void applyAbsoluteTransform() {
		GL11.glLoadIdentity();
	}

	@Override
	protected Rect getRect() {
		return new Rect(0, 0, 0, 0);
	}

	@Override
	protected void onCollision(CollidableSceneNode collision) {
		return;
	}
}
