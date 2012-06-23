package com.squares.tree;

import java.util.List;

import com.squares.SceneNode;

public interface Traversal {
	public List<SceneNode> findAll(SceneNode startNode);
}
