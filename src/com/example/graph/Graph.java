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
	public static float[] _previewsVertex = {0,0f,0,0f,0,0f};
	
	public static float pointSize = 2.0f; //the size of the points to draw
	
	//will be used for locking the rendering container upon modifications
	public static boolean locked = false;  
		
	public Graph()
	{
		grid = new Grid();		

		//addPlanes();
		
		//addTestPoints();
	}
	
	/**
	 * 	Set usable data for graphing as planes.  
	 * */
	public void setPlaneData(float[] vertexData,float zVertex)
	{
		float[] tmpArray = new float[12];
		Plane plane = null;

		_previewsVertex[0] = -0.0f;
		_previewsVertex[1] = 0.0f;
		_previewsVertex[2] = 0.0f;
		try
		{
			for(int n =0;n < vertexData.length; n+=3)
			{
				//first vertex
				tmpArray[0] = vertexData[n];
				tmpArray[1] = vertexData[n+1];
				tmpArray[2] = vertexData[n+2];

				//second vertex
				tmpArray[3] = vertexData[n+3];
				tmpArray[4] = vertexData[n+4];
				tmpArray[5] = vertexData[n+5];
				
				//third vertex
				tmpArray[6] = vertexData[n];
				tmpArray[7] = vertexData[n+1];
				tmpArray[8] = zVertex;

				//fourth vertex
				tmpArray[9] = vertexData[n+3];
				tmpArray[10] = vertexData[n+4];
				tmpArray[11] = zVertex;			
				

				_previewsVertex[0] = vertexData[n+3];
				_previewsVertex[1] = vertexData[n+4];
				_previewsVertex[2] = vertexData[n+5];			
				
				plane = new Plane();
				plane.setVertices(tmpArray);
				_drawables.add(plane);
			}
		}catch(Exception e){
			//TODO:
		}
	}
	
	
	public void addPlanes()
	{
		float[] generated = new float[350*3];
		float y = 0.0f;
		float x = 0.0f;
		_previewsVertex[0] = -0.0f;
		_previewsVertex[1] = 0.0f;
		_previewsVertex[2] = 0.0f;
		for(int n = 0, index = 0;n<350;n++,index+=3){
			y+= 0.03;
			x+= 0.005;
			addPoint(x, y, 0.0f);

			generated[index] = x;
			generated[index+1] = y ;//*( n % 2 == 0 ? -1:1);
			generated[index+2] = -0.8f;
			//addLine(_previewsVertex[0], _previewsVertex[1], _previewsVertex[2], x, y, 0.0f);

			_previewsVertex[0] = x;
			_previewsVertex[1] = y;//*( n % 2 == 0 ? -1:1);
			_previewsVertex[2] = 0.0f;
		}
		Log.wtf("DRAWABLES", "AMOUNT = "+_drawables.size());
		
		setPlaneData(generated, -0.5f);
		
	}
	
	
	/**
	 * Cleans the data of the current graphics.
	 * */
	public void cleanData(){
		locked = true;
		_drawables.clear();		
		locked = false;
	}
	
	/**
	 * 	Add simple points to plot.
	 *  example = [x,y,z,x1,y2,z2] (All values into one array)
	 * */
	public void setGraphPoints(float[] points)
	{
		for(int n = 0 ; n < points.length /3;n+=3)
		{
			addPoint(n, n+1, n+2);
		}
	}	
	
	private void addTestPoints()
	{
		float y = 0.0f;
		float x = 0.0f;
		for(int n = 0, index = 0;n<350;n++){
			y+= 0.03;
			x+= 0.005;
			addPoint(x, y, 0.0f);
		}
		Log.wtf("DRAWABLES POINT ", "AMOUNT = "+_drawables.size());		
	}
	
	/**
	 * 
	 * */
	public void addLine(Line line)
	{
		if(line!=null){
			_drawables.add(line);
		}
	}
	
	/**
	 * Add a line from position 1 to position 2.
	 * position1 = {x1,y1,z1}
	 * position2 = {x2,y2,z2}
	 * */
	public void addLine(float x1,float y1,float z1,float x2,float y2,float z2)
	{
		Line line = new Line();
		line.setVertex(x1, y1, z1, x2, y2, z2);
		_drawables.add(line);
	}
	
	/**
	 * Add a point in the 3d world.
	 * */
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
				
		Iterator<GraphObject> it = _drawables.iterator();
		while(it.hasNext() && !locked){
			GraphObject obj = it.next();
			obj.draw(gl);
		}
	}
	
	
	public static class Color
	{
		public static float[] colors = 
			{  // Colors of the 5 vertices in RGBA
			      0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
			      0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
			      0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
			      0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
			      1.0f, 0.0f, 0.0f, 1.0f,   // 4. red
			      1.0f, 1.0f, 1.0f, 1.0f   // 4. white
		   };
		public static enum c{
			RED,GREEN,BLUE,BLACK,WHITE
		};
			  
	}
	
}
