package com.example.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;


/**
 * This class will draw simple graphics in the screen.
 * */
public class Graph 
{
	private float[] gridDistance = {100,100,100};//drawing distance of the grid
	//objects to be drawn
	private List<GraphObject> _drawables = new ArrayList<GraphObject>();
	
	private Grid grid;
	private Point point;
	
	public static float[] _previewsVertex = {0,0f,0,0f,0,0f};
	
	//points of beggining and end of the line
	private float[] linesPoints = 
	{
		0,0f,0,0f,0,0f,
		0,0f,0,0f,0,0f
	};
	
	public Graph()
	{
		grid = new Grid();		
		point = new Point();
		point.setVertex(0.55f, 0, 0);
		addTestPoints();
	}
	
	/**
	 * 
	 * */
	public void setGraphPoints(float[] points,int vertexAmount,float scaleMin,float scaleMax)
	{
		for(int n = 0 ; n < vertexAmount;n++){
			
		}
	}	
	
	
	private void addTestPoints()
	{
		float y = 0.0f;
		float x = 0.0f;
		_previewsVertex[0] = -0.0f;
		_previewsVertex[1] = 0.0f;
		_previewsVertex[2] = 0.0f;
		for(int n = 0;n<150;n++){
			y+= 0.03;
			x+= 0.05;
			addPoint(x, y, 0.0f);
			addLine(_previewsVertex[0], _previewsVertex[1], _previewsVertex[2], x, y, 0.0f);

			_previewsVertex[0] = x;
			_previewsVertex[1] = y;
			_previewsVertex[2] = 0.0f;
		}
		Log.wtf("DRAWABLES", "AMOUNT = "+_drawables.size());
		
	}
	
	public void addLine(float x1,float y1,float z1,float x2,float y2,float z2)
	{
		Line line = new Line();
		line.setVertex(x1, y1, z1, x2, y2, z2);
		_drawables.add(line);
	}
	
	public void addPoint(float x,float y,float z)
	{
		Point point = new Point();
		point.setVertex(x, y, z);
		_drawables.add(point);
	}
	
	
	/***/	
	public void draw(GL10 gl)
	{
		grid.draw(gl);
		
		point.draw(gl);
		
		Iterator<GraphObject> it = _drawables.iterator();
		while(it.hasNext()){
			GraphObject obj = it.next();
			obj.draw(gl);
		}
	}
	
	
	/**
	 * Simple interface for the objects to be drawn.
	 * */
	public interface GraphObject{
		public void draw(GL10 gl);
	}
	
	public static class Color
	{
		public static float[] colors = 
			{  // Colors of the 5 vertices in RGBA
			      0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
			      0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
			      0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
			      0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
			      1.0f, 0.0f, 0.0f, 1.0f   // 4. red
		   };
		public static enum c{
			RED,GREEN,BLUE,BLACK,WHITE
		};
			  
	}
	
}
