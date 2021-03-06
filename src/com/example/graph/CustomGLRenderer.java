package com.example.graph;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;


public class CustomGLRenderer implements GLSurfaceView.Renderer
{

	public volatile float[] camTranslation = {-1.0f,-0.0f, -2.5f};
	public volatile float rotation = -40f; //intensity of rotation
	public volatile float xRotation = -0.2f;
	public volatile float yRotation = -0.2f;
	public volatile float zRotation = 0.4f;
	Context context; //application context
	private static Graph graph;
	Triangle triangle;
	Line line;
	
	public CustomGLRenderer(Context appContext)
	{
		this.context = appContext;
		setGraph(new Graph());
		triangle =  new Triangle();
		line = new Line();
		line.setVertex(-0.0f, 0, 0, 0.5f, 0, 0);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		 gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
	      gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
	      gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
	      gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
	      gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
	      gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
	      gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance		
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int height, int width)
	{
		if (height == 0) height = 1;   // To prevent divide by zero
	      float aspect = (float)width / height;
	   
	      // Set the viewport (display area) to cover the entire window
	      gl.glViewport(0, 0, width, height);
	  
	      // Setup perspective projection, with aspect ratio matches viewport
	      gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
	      gl.glLoadIdentity();                 // Reset projection matrix
	      // Use perspective projection
	      GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
	  
	      gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
	      gl.glLoadIdentity();                 // Reset		
	}

	
	@Override
	public void onDrawFrame(GL10 gl) 
	{
	      gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

	      gl.glLoadIdentity();
	      gl.glTranslatef(camTranslation[0], camTranslation[1], camTranslation[2]);
	      gl.glRotatef(rotation, xRotation, yRotation, zRotation);
	     // gl.glRotatef(-25.9f, -150.0f, -60.5f, 0);
	      //triangle.draw(gl);
	      
	      //line.draw(gl);
	      
	      if(!Graph.locked)
	    	  getGraph().draw(gl);	      
	}

	public float getAngle() {
		// TODO Auto-generated method stub
		return yRotation;
	}

	public void setAngle(float f) {
		yRotation = f;		
	}

	public static Graph getGraph() {
		return graph;
	}

	public static void setGraph(Graph graph) {
		CustomGLRenderer.graph = graph;
	}


}
