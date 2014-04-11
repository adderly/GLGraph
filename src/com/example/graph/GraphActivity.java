package com.example.graph;

import com.example.graph.Graph.Color.c;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * 
 * */
public class GraphActivity extends Activity 
{
	private GLView glView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  
	    glView = new GLView(this);           // Allocate a GLSurfaceView
	    this.hideSystemUI();
	    setContentView(R.layout.activity_graph);
	    
	    
		
		RelativeLayout layout =  (RelativeLayout) findViewById(R.id.gamelayout);
		RelativeLayout.LayoutParams params = new 
				RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.addView(glView, params);
		

		

		Graph graph = glView.getRenderer().getGraph();
		Cylinder c = new Cylinder(0.1f,0.7f); //pass size here
		c.setRadius(0.8f);//not working 
		c.setHeight(0.7f);//not working
		graph.addGObject(c);
		testPoint();
	}
	
	
	/**
	 * Test method
	 * */
	public void testPoint()
	{
		Line line1 = new Line();
		line1.setVertex(-1.0f, 0, 0, 1.0f,1.0f, 0);
		line1.setColor(c.GREEN);//set the color 
		//glView.getRenderer().getGraph().addLine(line1); // add a line
		Graph graph = glView.getRenderer().getGraph();
		graph.pointSize = 4.0f;
		float y = 0.0f;
		float x = 0.0f;
		for(int n = 0, index = 0;n<350;n++){
			y+= 0.02;
			x+= 0.03;
			graph.addPoint(x, y, 0.0f);//add points
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//testPoint();
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onPause()
	{
	      super.onPause();
	      glView.onPause();	
	}
	
    @Override
    protected void onResume() {
       super.onResume();
       glView.onResume();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph, menu);
		return true;
	}
	
	private void hideSystemUI()
    {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
		glView.setSystemUiVisibility(
				glView.SYSTEM_UI_FLAG_LAYOUT_STABLE 
                | glView.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | glView.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | glView.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | glView.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | glView.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_graph,
					container, false);
			return rootView;
		}
	}

}
