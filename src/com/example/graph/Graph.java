package com.example.graph;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;


/**
 * This class will draw simple graphics in the screen.
 * */
public class Graph 
{
	private float[] gridDistance = {100,100,100};//drawing distance of the grid
	//objects to be drawn
	private List<GraphObject> _drawables = new ArrayList<GraphObject>();
	
	private Grid grid;
	
	//points of beggining and end of the line
	private float[] linesPoints = 
	{
		0,0f,0,0f,0,0f,
		0,0f,0,0f,0,0f
	};
	
	public Graph()
	{
		grid = new Grid();		
	}
	
	/**
	 * 
	 * */
	public void setGraphPoints(float[] points,int vertexAmount,float scaleMin,float scaleMax)
	{
		for(int n = 0 ; n < vertexAmount;n++){
			
		}
	}	
	
	/***/	
	public void draw(GL10 gl)
	{
		grid.draw(gl);
	}
	
	
	/**
	 * Simple interface for the objects to be drawn.
	 * */
	public interface GraphObject{
		public void draw(GL10 gl);
	}
	
}
