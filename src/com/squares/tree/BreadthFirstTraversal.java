package com.squares.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.squares.RootSceneNode;
import com.squares.SceneNode;

public abstract class BreadthFirstTraversal implements Traversal {

	@Override
	public List<SceneNode> findAll(SceneNode startNode) {
		List<SceneNode> list = new ArrayList<SceneNode>();
		
		SceneNode root = findRoot(startNode);
		
		if(root == null) {
			return list;
		}
		
		Stack<SceneNode> stack = new Stack<SceneNode>();
		addNodes(stack, root.getChildren());
		
		while(!stack.empty()) {
			SceneNode node = stack.pop();
			
			if(isMatch(node, startNode)) {
				list.add(node);
			}
			
			addNodes(stack, node.getChildren());
		}
		
		return list;
	}
	
	private void addNodes(Stack<SceneNode> stack, List<SceneNode> nodes) {
		for(SceneNode node : nodes) {
			stack.push(node);
		}
	}

	protected SceneNode findRoot(SceneNode startNode) {
		if(startNode == null) {
			return null;
		}
		
		if(startNode instanceof RootSceneNode) {
			return startNode;
		}
		
		return findRoot(startNode.getParent());
	}
	
	protected abstract boolean isMatch(SceneNode child, SceneNode startNode);
}
