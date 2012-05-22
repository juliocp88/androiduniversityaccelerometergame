package game.base;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View.OnTouchListener;

public abstract class DrawablePanel 
 extends GLSurfaceView
 implements SurfaceHolder.Callback, Entity, SensorEventListener, OnTouchListener {
	
	private AnimationThread thread;
	public DrawablePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		this.thread = new AnimationThread(getHolder(), this);
	}
	
	 @Override
	 public void onDraw(Canvas canvas) {
	 }
	
	public AnimationThread getThread() {
		return thread;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	    boolean retry = true;
	    thread.setRunning(false);
	    while (retry) {
	        try {
	            thread.join();
	            retry = false;
	        } catch (InterruptedException e) {
	            // we will try it again and again...
	        }
	    }			
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.hardware.SensorEventListener#onSensorChanged(android.hardware
	 * .SensorEvent)
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}


}