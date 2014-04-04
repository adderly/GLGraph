package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.example.graph.Graph.Color.c;

/**
 * For 
 * */
public abstract class GOColored implements GraphObject
{

	protected FloatBuffer vertexBuffer;
	protected FloatBuffer colorBuffer;
	
	public void setColor(c color)
	{
		float[] tmpColor = new float[4];//tmp color array
		switch (color) {
		case RED:
			tmpColor[0] = 1;
			tmpColor[1] = 0;
			tmpColor[2] = 0;
			tmpColor[3] = 1;  
			break;
		case GREEN:
			tmpColor[0] = 0;
			tmpColor[1] = 1;
			tmpColor[2] = 0;
			tmpColor[3] = 1;  
			break;
		case BLUE:
			tmpColor[0] = 0;
			tmpColor[1] = 0;
			tmpColor[2] = 1;
			tmpColor[3] = 1;  
			break;
		case WHITE:
			tmpColor[0] = 1;
			tmpColor[1] = 1;
			tmpColor[2] = 1;
			tmpColor[3] = 1;  
			break;
		default:
			break;
		}
		
		ByteBuffer cb = ByteBuffer.allocateDirect(4*4);
		cb.order(ByteOrder.nativeOrder());
		colorBuffer = cb.asFloatBuffer();
		colorBuffer.put(tmpColor);
		colorBuffer.position(0);
	}
}