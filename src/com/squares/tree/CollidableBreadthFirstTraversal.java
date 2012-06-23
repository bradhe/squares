package com.squares.tree;

import com.squares.CollidableSceneNode;
import com.squares.SceneNode;

public class CollidableBreadthFirstTraversal extends BreadthFirstTraversal {

	@Override
	protected boolean isMatch(SceneNode child, SceneNode startNode) {
		return (child instanceof CollidableSceneNode) && startNode != child;
	}
	
}
