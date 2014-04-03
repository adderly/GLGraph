package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.example.graph.Graph.GraphObject;


/**
 * 	Draws the grid of the graphics.
 * */
public class Grid implements GraphObject
{
	private ByteBuffer vertexBuffer;
	private FloatBuffer indices;
	
	float graphLimit = 1; //the limit of drawing distance in screen	
	
	private List<Line> lines = new ArrayList<Line>(); 
	
	public Grid()
	{
		Line line1 = new Line();
		line1.setVertex(-0.0f, 0, 0, 0.6f, 0, 0);
		lines.add(line1);

		Line line2 = new Line();
		line2.setVertex(-0.0f, 0, 0, 0.0f, 0.6f, 0);
		lines.add(line2);
		
		Line line3 = new Line();
		line3.setVertex(-0.0f, 0, 0, 0.2f, 0.2f, -0.6f);
		lines.add(line3);
	}
	
	public void draw(GL10 gl)
	{		
		Line line;
		Iterator<Line> it = lines.iterator();
		while(it.hasNext())
		{
			line = it.next();
			line.draw(gl);
		}
	}
}
