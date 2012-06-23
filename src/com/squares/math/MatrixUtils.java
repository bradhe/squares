package com.squares.math;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class MatrixUtils {
	public static void print(float[] matrix) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.format("%f ", matrix[(i * 4) + j]);
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static Matrix4f fromVector(Vector3f vector) {
		Matrix4f matrix = new Matrix4f();
		matrix.m30 = vector.x;
		matrix.m31 = vector.y;
		matrix.m32 = vector.z;
		matrix.m33 = 1;
		return matrix;
	}
	
	public static Vector3f toVector(Matrix4f matrix) {
		Vector3f vector = new Vector3f();
		toVector(matrix, vector);
		return vector;
	}
	
	public static Vector3f toVector(Matrix4f matrix, Vector3f vector) {
		vector.x = matrix.m30;
		vector.y = matrix.m31;
		vector.z = matrix.m32;
		return vector;
	}
	
	public static void mul(Matrix4f left, Vector3f right, Vector3f result) {
		Matrix4f matrix = fromVector(right); 
		Matrix4f.mul(left, matrix, matrix);
		toVector(matrix, result);
	}
}
