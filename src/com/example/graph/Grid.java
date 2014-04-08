package com.example.graph;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.example.graph.Graph.Color.c;


/**
 * 	Draws the grid of the graphics.
 * */
public class Grid extends GOColored
{
	private ByteBuffer vertexBuffer;
	private FloatBuffer indices;
	
	float graphLimit = 1; //the limit of drawing distance in screen	
	
	private List<Line> lines = new ArrayList<Line>(); 
	
	public Grid()
	{
		Line line1 = new Line();
		line1.setVertex(-1.0f, 0, 0, 1.0f, 0, 0);
		lines.add(line1);

		Line line2 = new Line();
		line2.setVertex(-0.0f, -1.0f, 0, 0.0f, 1.0f, 0);
		lines.add(line2);
		
		Line line3 = new Line();
		line3.setVertex(-0.0f, 0, 1.0f, 0, 0.0f, -1.0f);
		//line3.setColor(c.RED);
		lines.add(line3);
		setGridLines(true);
	}
	
	/**
	 * Set the lines for the drawing space.
	 * */
	public void setGridLines(boolean what)
	{
		Line line3 = null;
		for(float n = -1.0f;n < 1;n+=0.05){
			line3 = new Line();
			line3.setVertex(n, 0, 1.0f, n, 0.0f, -1.0f);
			line3.setColor(c.RED);
			lines.add(line3);
		}
	}
	
	/**
	 * Draw method of every drawable object.
	 * */
	public void draw(GL10 gl)
	{		
		Line line = null;
		Iterator<Line> it = lines.iterator();
		while(it.hasNext())
		{
			line = it.next();
			line.draw(gl);
		}
	}
}
