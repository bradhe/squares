package com.squares.math;

import org.lwjgl.util.vector.Vector3f;

public class VectorUtils {
	public float x;
	public float y;
	public float z;
	
	/**
	 * Returns true if `left` cancels `right.` Returns false otherwise.
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean canceled(Vector3f left, Vector3f right) {
		return(oppositeSigns(left.x, right.x) || oppositeSigns(left.y, right.y) || oppositeSigns(left.z, right.z));
	}
	
	private static boolean oppositeSigns(float x, float y) {
		return (x + y) != (Math.abs(x) + Math.abs(y));
	}
}
