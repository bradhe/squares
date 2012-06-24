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
		if(this.left() > rect.right()) {
			System.out.println("1");
			return false;
		}
		else if(this.right() < rect.left()) {
			System.out.println("2");
			return false;
		}
		else if(this.top() < rect.bottom()) {
			System.out.println("3");
			return false;
		}
		else if(this.bottom() > rect.top()) {

			System.out.format("This:\t%f\n", this.bottom());
			System.out.format("Rect:\t%f\n", rect.top());
			System.out.println();

			return false;
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
