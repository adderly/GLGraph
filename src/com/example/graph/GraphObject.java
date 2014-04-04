package com.example.graph;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Simple interface for the objects to be drawn.
 * */
public interface GraphObject{
	public void draw(GL10 gl);
}