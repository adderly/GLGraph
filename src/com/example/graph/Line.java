package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Simple gl lines
 * */
public class Line implements Graph.GraphObject
{
	private ByteBuffer indicesBuffer;
	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	
	
	//vertices
	private float[] vertices =
	{
		0,0f,0,0f,0,0f,
		0,0f,0,0f,0,0f
	};
	
	private byte[] indices = {0,1}; //amount of vertex
	
	
	public Line()
	{
		ByteBuffer vf = ByteBuffer.allocateDirect(vertices.length * 4);
		vf.order(ByteOrder.nativeOrder());
		vertexBuffer = vf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		ByteBuffer cb = ByteBuffer.allocateDirect(4*Graph.Color.colors.length);
		cb.order(ByteOrder.nativeOrder());
		colorBuffer = cb.asFloatBuffer();
		colorBuffer.put(Graph.Color.colors);
		colorBuffer.position(0);
		
		indicesBuffer = ByteBuffer.allocateDirect(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.position(0);		
	}
	
	
	/**
	 *  Set The stard and end of the line.
	 * */
	public void setVertex(float x1,float y1,float z1,float x2,float y2,float z2)
	{
		vertices[0] =x1;
		vertices[1] =y1;
		vertices[2] =z1;
		vertices[3] =x2;
		vertices[4] =y2;
		vertices[5] =z2;
		
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);	
	}
	
	public void draw(GL10 gl)
	{
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		
		gl.glDrawElements(GL10.GL_LINES, indices.length, GL10.GL_UNSIGNED_BYTE, indicesBuffer);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);		
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);		
	}
	
}
