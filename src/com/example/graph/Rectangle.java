package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


/**
 * 	Rectangle Drawable
 * */
public class Rectangle extends GOColored
{

	private FloatBuffer vertexBuffer;
	
	private float[] vertices = 
	{
      -1.0f, -1.0f,  0.0f,  // 0. left-bottom
       1.0f, -1.0f,  0.0f,  // 1. right-bottom
      -1.0f,  1.0f,  0.0f,  // 2. left-top
       1.0f,  1.0f,  0.0f   // 3. right-top
    };
	
	public Rectangle(){
		ByteBuffer  vb = ByteBuffer.allocateDirect(vertices.length*4);
		vb.order(ByteOrder.nativeOrder());
		vertexBuffer = vb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	@Override
	public void draw(GL10 gl) 
	{
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
}
