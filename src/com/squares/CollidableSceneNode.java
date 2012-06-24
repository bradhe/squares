package com.squares;

import java.util.List;

import org.lwjgl.util.Rectangle;

import com.squares.math.Rect;
import com.squares.tree.CollidableBreadthFirstTraversal;
import com.squares.tree.Traversal;

public abstract class CollidableSceneNode extends RenderableSceneNode {
	public void update(long ticks) {
		// See if there is a collision with anything in the scene.
		this.findCollisions();
	}

	protected boolean findCollisions() {
		// Gather all of the friendly nodes.
		CollidableBreadthFirstTraversal traversal = new CollidableBreadthFirstTraversal();

		List<SceneNode> nodes = traversal.findAll(this);
		
		// Don't care about ourselves...
		nodes.remove(this);
		
		boolean hasCollisions = false;
		
		for(SceneNode node : nodes) {
			CollidableSceneNode collidableNode = (CollidableSceneNode)node;
			
			if(isCollision(collidableNode.getRect(), this.getRect())) {
				onCollision(collidableNode);
				hasCollisions = true;
			}
		}
		
		return hasCollisions;
	}
	
	protected boolean isCollision(Rect left, Rect right) {
		return right.overlaps(left);
	}

	protected abstract Rect getRect();
	protected abstract void onCollision(CollidableSceneNode collision);
}
