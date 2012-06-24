package com.squares.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector3f;

import com.squares.math.VectorUtils;

public class ForceList extends ArrayList<Force> {
	public Vector3f sum() {
		Vector3f result = new Vector3f(0, 0, 0);
	
		Map<Vector3f, Force> cancelableForces = new HashMap<Vector3f, Force>();
		for(Force force : this) {
			Vector3f calculation = force.calculate();
			
			if(!(force instanceof ConstantForce)) {
				cancelableForces.put(calculation, force);
			}
			Vector3f.add(result, force.calculate(), result);
		}
		
		// Now remove any cancelable ones!
		List<Force> list = new ArrayList<Force>();
		for(Vector3f vector : cancelableForces.keySet()){
			if(VectorUtils.canceled(result, vector)) {
				list.add(cancelableForces.get(vector));
			}
		}
		
		for(Force force : list) {
			this.remove(force);
		}
		
		return result;
	}
	
	@Override
	public boolean add(Force force) {
		return super.add(force);
	}
}
