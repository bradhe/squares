package com.squares;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector3f;

import org.lwjgl.opengl.GL11;

import com.squares.math.Rect;

public class FloorModel extends Model {
	public FloorModel() {
		SquareMesh mesh = new SquareMesh(5.0f, 0.25f);
		mesh.setColor((Color)Color.YELLOW);
		this.addChild(mesh);
	}
	
	public void update(long ticks) {
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		this.applyTransform();
		
		for(RenderableSceneNode renderableSceneNode : getRenderableChildren()) {
			renderableSceneNode.render();
		}
		
		GL11.glPopMatrix();
	}

	@Override
	protected Rect getRect() {
		Vector3f absoluteVector = this.getCenter();
		SquareMesh squareMesh = (SquareMesh)getChildren().get(0);
		float halfWidth = squareMesh.getWidth() / 2.0f;
		float halfHeight = squareMesh.getHeight() / 2.0f;
		
		return new Rect(absoluteVector.x - halfWidth, absoluteVector.y + halfHeight, squareMesh.getWidth(), squareMesh.getHeight());
	}

	@Override
	protected void onCollision(CollidableSceneNode collision) {
		// TODO Auto-generated method stub
		System.out.println("Collision detected!");
	}
}
