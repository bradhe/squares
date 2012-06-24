package com.squares.physics;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class ForceList extends ArrayList<Force> {
	public Vector3f sum() {
		Vector3f result = new Vector3f(0, 0, 0);
	
		for(Force force : this) {
			Vector3f.add(result, force.calculate(), result);
		}
		
		return result;
	}
}
