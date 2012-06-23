package com.squares;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;

public class Game {
	public RootSceneNode scene;
	
	public void initialize() {
		try {
		    Display.setDisplayMode(new DisplayMode(800,600));
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(1);
		}
		
		GL11.glMatrixMode(GL11.GL_PROJECTION_MATRIX);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1.0f, 10.0f);
		GL11.glViewport(0, 0, 800, 600);

		this.scene = new RootSceneNode();
		
		Model model = new SquareModel();
		this.scene.addChild(model);
		model.translate(0, -0.5f, 0);
		
		FloorModel floor = new FloorModel();
		this.scene.addChild(floor);
		
		floor.translate(0.0f, -1.0f, 0.0f);
		
		// Set the position of the whole scene!
		this.scene.translate(0, 0, -1.0f);
	}
	
	protected void update(long ticks) {
		GL11.glClearColor(0.1f, 0.1f, 0.2f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		GL11.glLoadIdentity();
		
		// Just update each model I guess.
		this.scene.update(ticks);
	}
	
	protected void render() {
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		
		this.scene.render();
		
		GL11.glPopMatrix();
	}

	public void run() {
		this.initialize();
		
		long timeDelta = 0, timeStart = 0, timeStop = 0;
		
		while(continueRunning()) {
			timeStart = getTime();
			
			this.update(timeDelta);
			this.render();
			
			Display.update();
			
			Display.sync(120);
			
			timeStop = getTime();
			timeDelta = timeStop - timeStart;
		}
	}
	
	protected boolean continueRunning() {
		return !Display.isCloseRequested();
	}
	
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
