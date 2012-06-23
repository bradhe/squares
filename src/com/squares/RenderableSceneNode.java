package com.squares;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.squares.math.MatrixUtils;

public abstract class RenderableSceneNode extends SceneNode {
	public abstract void update(long ticks);
	public abstract void render();
	
	public List<RenderableSceneNode> getRenderableChildren() {
		List<RenderableSceneNode> renderableSceneNodes = new ArrayList<RenderableSceneNode>();
		
		for(SceneNode child : getChildren()) {
			if(child instanceof RenderableSceneNode) {
				renderableSceneNodes.add((RenderableSceneNode)child);
			}
		}
		
		return renderableSceneNodes;
	}
	
	public Vector3f getCenter() {
		Matrix4f absoluteTransform = this.absoluteTransform();

		Vector3f result = new Vector3f();
		MatrixUtils.mul(absoluteTransform, new Vector3f(0, 0, 0), result);
		
		return result;
	}
}
