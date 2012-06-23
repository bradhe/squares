package com.squares;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

public class SquareMesh extends Mesh {
	private float width;
	private float height;
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public SquareMesh(float width, float height) {
		super();
		
		this.width = width;
		this.height = height;
		this.color = (Color)Color.GREEN;
	}

	@Override
	public void render() {
		GL11.glPushMatrix();
		
		this.applyTransform();
		
		float halfWidth = width / 2.0f;
		float halfHeight = height / 2.0f;
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor4f(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.color.getAlpha() / 255.0f);
		GL11.glVertex3f(-1 * halfWidth, -1 * halfHeight, 0);
		
		GL11.glColor4f(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.color.getAlpha() / 255.0f);
		GL11.glVertex3f(halfWidth, -1 * halfHeight, 0);
		
		GL11.glColor4f(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.color.getAlpha() / 255.0f);
		GL11.glVertex3f(halfWidth, halfHeight, 0);
		
		GL11.glColor4f(this.color.getRed() / 255.0f, this.color.getGreen() / 255.0f, this.color.getBlue() / 255.0f, this.color.getAlpha() / 255.0f);
		GL11.glVertex3f(-1 * halfWidth, halfHeight, 0);
		
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}

	@Override
	public void update(long ticks) {
		// TODO: Update animation?
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
