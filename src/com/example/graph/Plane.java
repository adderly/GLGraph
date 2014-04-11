package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.graph.Graph.Color.c;

/**
 * 	Rectangle 
 * */
public class Plane extends GOColored
{
	private float[] vertices ={
		      -1.0f, -1.0f,  0.0f,  // 0. left-bottom
		       1.0f, -1.0f,  0.0f,  // 1. right-bottom
		      -1.0f,  1.0f,  0.0f,  // 2. left-top
		       1.0f,  1.0f,  0.0f   // 3. right-top		
		      //-1.0f,  1.0f,  -1.0f,  // 2. left-top
		      // 1.0f,  1.0f,  0.0f   // 3. right-top		
		};
	
	public Plane()
	{
		setColor(c.RED);
			

		ByteBuffer  vb = ByteBuffer.allocateDirect(vertices.length*4);
		vb.order(ByteOrder.nativeOrder());
		vertexBuffer = vb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	
	/**
	 * 	
	 * */
	public Plane(float[] v,float zDepth)
	{
		setColor(c.WHITE);
		setVertex(v, zDepth);
	}	
	
	public void setVertices(float[] vertexs){
		vertices = vertexs;
		ByteBuffer  vb = ByteBuffer.allocateDirect(vertices.length*4);
		vb.order(ByteOrder.nativeOrder());
		vertexBuffer = vb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	/**
	 * 
	 * */
	public void setVertex(float[] v, float zdeph)
	{		
		vertices = new float[v.length*2]; //double the amount of points for the strip
		float x = 0.0f;
		float y = 0.0f;
		float z = 0.0f;
		// fill the float array, for
		for(int n = 0 ; n < v.length /3;n+=3)
		{
			
				vertices[n] = v[n];
				vertices[n+1] = v[n+1];
				vertices[n+2] = v[n+2];
				
				vertices[n+3] = v[n];
				vertices[n+4] = v[n+1];
				vertices[n+5] = zdeph;
				n+=3;
		}		
		
		ByteBuffer bf = ByteBuffer.allocateDirect(4*vertices.length);
		bf.order(ByteOrder.nativeOrder());
		vertexBuffer = bf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);		
	}	
	
	@Override
	public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		//gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		gl.glColor4x(1, 0, 0, 1);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}

}
