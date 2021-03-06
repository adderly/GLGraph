package com.example.graph;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView
{
	private static final float TOUCH_SCALE_FACTOR  = -0.1f;

    float mPreviousX = 0;
	float mPreviousY = 0;
	public CustomGLRenderer renderer;
	
	public GLView(Context context) {
		super(context);
		renderer = new CustomGLRenderer(context);
		setRenderer(renderer);
	}
	
	public CustomGLRenderer getRenderer(){
		return renderer;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
	    // MotionEvent reports input details from the touch screen
	    // and other input controls. In this case, you are only
	    // interested in events where the touch position changed.

	    float x = e.getX();
	    float y = e.getY();

		switch (e.getAction()) {
	        case MotionEvent.ACTION_MOVE:

	            float dx = x - mPreviousX;
	            float dy = y - mPreviousY;

	            // reverse direction of rotation above the mid-line
	            if (y > getHeight() / 2) {
	              dx = dx * -1 ;
	            }

	            // reverse direction of rotation to left of the mid-line
	            if (x < getWidth() / 2) {
	              dy = dy * -1 ;
	            }

	            renderer.setAngle(
	            		renderer.getAngle() +
	                    ((dx + dy) * TOUCH_SCALE_FACTOR ) );  // = 180.0f / 320
	            requestRender();
	    }

	    mPreviousX = x;
	    mPreviousY = y;
	    return true;
	}
}
