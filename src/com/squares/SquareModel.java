package com.squares;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.lwjgl.util.vector.Vector3f;

import com.squares.math.Rect;

public class SquareModel extends Model {
	private long accum = 0l;
	private double lastOffset = 0.0;
	
	public SquareModel() {
		super();
		this.addChild(new SquareMesh(1.0f, 1.0f));
	}
	
	public void update(long ticks) {
		this.accum += ticks;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			translate(0, 0.1f, 0);
		}
		
		// Translate downwards.
		
		//double offset = 0.15 * (Math.sin((2.0 * 3.14159) * (accum / 1000.0)));
		translate(0, -0.01f, 0);
		
		// Yay.
		this.findCollisions();

		super.update(ticks);
		//lastOffset = offset;
	}
	
	@Override
	public void render() {
		GL11.glPushMatrix();
		applyTransform();
		
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

		Rect r = new Rect(absoluteVector.x - halfWidth, absoluteVector.y + halfHeight, squareMesh.getWidth(), squareMesh.getHeight());
		return r;
	}

	@Override
	protected void onCollision(CollidableSceneNode collision) {
		Rect left = getRect();
		Rect right = collision.getRect();

		float offset = left.bottom() - right.top();
		System.out.println(offset);
		translate(0, -1 * offset, 0);
	}
}
