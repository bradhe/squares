package com.squares;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class SceneNode {
	private float[] transform;
	private SceneNode parent;
	private List<SceneNode> children;
	
	public SceneNode() {
		this.transform = new float[16];
		this.children = new ArrayList<SceneNode>();
	}
	
	public SceneNode(SceneNode parent) {
		this();
		this.parent = parent;
	}
	
	public SceneNode getParent() {
		return parent;
	}

	public void setParent(SceneNode parent) {
		this.parent = parent;
	}

	public List<SceneNode> getChildren() {
		return children;
	}
	
	public void addChild(SceneNode child) {
		this.children.add(child);
		child.setParent(this);
	}

	public void setChildren(List<SceneNode> children) {
		this.children = children;
	}

	public void translate(float x, float y, float z) {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		
		this.deserializeTransformMatrix();
		GL11.glTranslatef(x, y, z);
		this.serializeTransformMatrix();
		
		GL11.glPopMatrix();
	}
	
	public void rotate(float angle, float x, float y, float z) {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		
		this.deserializeTransformMatrix();
		GL11.glRotatef(angle, x, y, z);
		this.serializeTransformMatrix();
		
		GL11.glPopMatrix();
	}
	
	public void scale(float x, float y, float z) {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		
		this.deserializeTransformMatrix();
		GL11.glScalef(x, y, z);
		this.serializeTransformMatrix();
		
		GL11.glPopMatrix();
	}
	
	/**
	 * Applies this matrix to the current matrix mode.
	 */
	public void applyTransform() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(transform.length);
		buffer.put(transform);
		buffer.rewind();
		
		GL11.glMultMatrix(buffer);
	}

	protected void initializeTransform() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		
		this.serializeTransformMatrix();
		
		GL11.glPopMatrix();
	}
	
	protected void serializeTransformMatrix() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
		GL11.glGetFloat(GL11.GL_MODELVIEW, floatBuffer);
		
		floatBuffer.rewind();
		floatBuffer.get(this.transform);
	}
	
	protected void deserializeTransformMatrix() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(transform.length);
		buffer.put(transform);
		
		buffer.rewind();
		GL11.glLoadMatrix(buffer);
	}
	
	protected Vector3f getPositionVector() {
		GL11.glPushMatrix();
		
		// Add our transform matrix on to this...
		this.deserializeTransformMatrix();
		
		// A little mysterious...
		Vector3f myCenter = new Vector3f(transform[3], transform[7], transform[11]);
		
		GL11.glPopMatrix();
		
		return myCenter;
	}
	
	public Matrix4f absoluteTransform() {
		GL11.glPushMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW_MATRIX);
		
		// Get the parent's 
		this.applyAbsoluteTransform();
		
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
		GL11.glGetFloat(GL11.GL_MODELVIEW, floatBuffer);
		
		floatBuffer.rewind();
		
		// Deserialize this to a matrix!
		Matrix4f result = new Matrix4f();
		result.load(floatBuffer);
		
		GL11.glPopMatrix();
		
		return result;
	}
	
	public void applyAbsoluteTransform() {
		if(this.parent != null) {
			this.parent.applyAbsoluteTransform();
		}
		
		this.applyTransform();
	}
	
	public abstract Vector3f getCenter();
}
