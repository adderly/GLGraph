package com.example.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Cylinder extends GOColored
{
	private float radius = 0.6f;
	private float height = 0.4f;
	private int amountOfSides = 64;
	
	List<GraphObject> triangles = new ArrayList<GraphObject>();
	
	public Cylinder(float _radius,float _height)
	{
		
		radius =_radius;
		height = _height;
		int i = 0;
		double angle = 0.0f;
		float[] cx = new float[64];
		float[] cy = new float[64];
		
		for (i = 0; i < 64; i++)
		{
		    angle = 360 * i / 63;  // Or perhaps 2 * PI * i / 63
		    cx[i] = (float) Math.sin(angle) *radius;
		    cy[i] = (float) Math.cos(angle)* radius;
		}

		float[] tmp= new float[9];
		float[] tmp1= new float[9];
		for (i = 0; i < 63; i++)
		{
//		    v0 = Vertex(cx[i], cy[i], 0);
//		    v1 = Vertex(cx[i + 1], cy[i + 1], 0);
//		    v2 = Vertex(cx[i], cy[i], 1);
//		    v3 = Vertex(cx[i + 1], cy[i + 1], 1);
		    //first triangle
			   tmp[0] = cx[i];
			   tmp[1] = cy[i];
			   tmp[2] = 0;
			   tmp[3] = cx[i+1];
			   tmp[4] = cy[i+1];
			   tmp[5] = 0;
			   tmp[6] = cx[i];
			   tmp[7] = cy[i];
			   tmp[8] = 1;
		    //second triangle
			   tmp1[0] = cx[i+1];
			   tmp1[1] = cy[i+1];
			   tmp1[2] = 0;
			   tmp1[3] = cx[i+1];
			   tmp1[4] = cy[i+1];
			   tmp1[5] = 1;
			   tmp1[6] = cx[i];
			   tmp1[7] = cy[i];
			   tmp1[8] = 1;
			   
		    
			float[] tmpQ = new float[12];


			   tmpQ[0] = cx[i];
			   tmpQ[1] = 0;
			   tmpQ[2] = cy[i];
			   
			   tmpQ[3] = cx[i+1];
			   tmpQ[4] = 0;
			   tmpQ[5] = cy[i+1];
			   
			   tmpQ[6] = cx[i];
			   tmpQ[7] = height;
			   tmpQ[8] = cy[i];
			   
			   tmpQ[9] = cx[i+1];
			   tmpQ[10] = height;
			   tmpQ[11] =  cy[i+1];
//		    DrawTriangle(v0, v1, v2);
//		    DrawTriangle(v1, v3, v2);

		    Triangle tr1 = new Triangle();
		    tr1.setVertex(tmp);
		    Triangle tr2 = new Triangle();
		    tr2.setVertex(tmp1);
		    Plane p = new Plane();
		    p.setVertices(tmpQ);
		    triangles.add(p);		
		    
		    //triangles.add(tr1);
		    //triangles.add(tr2);
		    // If you have it:  DrawQuad(v0, v1, v3, v2);
		}	
	}	

	@Override
	public void draw(GL10 gl) {
		GraphObject triangle = null;
		Iterator<GraphObject> it = triangles.iterator();
		while(it.hasNext()){
			triangle = it.next();
			triangle.draw(gl);
		}
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
