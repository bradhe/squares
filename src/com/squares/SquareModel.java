package com.squares;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.squares.math.Rect;
import com.squares.physics.Force;
import com.squares.physics.ForceList;
import com.squares.physics.GravityForce;
import com.squares.physics.JumpForce;

public class SquareModel extends Model {
	private long accum = 0l;
	private double lastOffset = 0.0;
	private ForceList forces;
	private Texture texture;
	
	public SquareModel() {
		super();
		this.addChild(new SquareMesh(0.2f, 0.4f));
		this.forces = new ForceList();
		this.forces.add(new GravityForce(this, 0));
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/Texture1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(long ticks) {
		// Check for keyboard stuff!
		this.accum += ticks;
		super.update(ticks);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			boolean found = false;
			for(Force force : forces) {
				if(force instanceof JumpForce) {
					found = true;
				}
			}
			
			if(!found) {
				forces.add(new JumpForce(this, this.accum));
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			translate(-0.01f, 0, 0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			translate(0.01f, 0, 0);
		}
		
		if(this.findCollisions()) {
			List<Force> toRemove = new ArrayList<Force>();
			for(Force force : forces) {
				if(force instanceof GravityForce) {
					toRemove.add(force);
				}
			}
			
			for(Force force : toRemove) {
				forces.remove(force);
			}
		}
		else {
			// Make sure there is a gravity force in here. If there isn't then we need to add one!
			boolean hasGravityForce = false;
			for(Force force : forces) {
				if(force instanceof GravityForce) {
					hasGravityForce = true;
				}
			}
			
			if(!hasGravityForce) {
				forces.add(new GravityForce(this, this.accum));
			}
		}
		
		// Apply forces...then do collision detection.
		for(Force force : forces) {
			force.update(ticks);
		}
		
		translate(forces.sum());
	}
	
	@Override
	public void render() {
		GL11.glPushMatrix();
		applyTransform();
		
		texture.bind();
		for(RenderableSceneNode renderableSceneNode : getRenderableChildren()) {
			renderableSceneNode.render();
		}
		//texture.release();

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
		translate(0, -1 * offset, 0);
	}

}
