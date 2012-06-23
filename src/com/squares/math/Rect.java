package com.squares.math;

public class Rect {
	public float x;
	public float y;
	public float width;
	public float height;
	
	public Rect(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean overlaps(Rect rect) {
		System.out.format("This:\t%s\n", this);
		System.out.format("Rect:\t%s\n", rect);
		System.out.println();
		
		if(this.left() > rect.right() || this.right() < rect.left() || this.top() < rect.bottom() || this.bottom() > rect.top()) {
			return false;
		}
		else if(this.bottom() < rect.top()) {
			return true;
		}
		else {
			return true;
		}
	}
	
	public float left() {
		return x;
	}
	
	public float right() {
		return x + width;
	}
	
	public float top() {
		return y;
	}
	
	public float bottom() {
		return y - height;
	}
	
	public String toString() {
		return String.format("[(%f, %f), (%f, %f)]", top(), left(), bottom(), right());
	}
}
