package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.graph.Graph.Color.c;

/**
 * Point object
 * */
public class Point extends GOColored
{
	private ByteBuffer indicesBuffer;
	private FloatBuffer vertexBuffer;
	private float pointSize = 2.0f;
	
	
	public void setPointSize(float pointSize) {
		this.pointSize = pointSize;
	}

	private float[] vertices = {0.0f,0.0f,0.0f};
	private byte[] indices = {0};
	
	
	public Point()
	{
		ByteBuffer vf = ByteBuffer.allocateDirect(vertices.length*4);
		vf.order(ByteOrder.nativeOrder());
		vertexBuffer = vf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		indicesBuffer = ByteBuffer.allocateDirect(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.position(0);		
		
		setColor(c.RED);
	}
	
	public void setVertex(float x,float y,float z)
	{
		vertices[0] = x;
		vertices[1] = y;
		vertices[2] = z;

		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	public void draw(GL10 gl) 
	{
		gl.glEnable(GL10.GL_POINT_SMOOTH);
		gl.glEnable(GL10.GL_BLEND);
		gl.glPointSize(pointSize);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	      
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);     
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
	
		gl.glDrawElements(GL10.GL_POINTS, indices.length, GL10.GL_UNSIGNED_BYTE, indicesBuffer);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);		

		gl.glDisable(GL10.GL_POINT_SMOOTH);
		gl.glDisable(GL10.GL_BLEND);
	}

}
