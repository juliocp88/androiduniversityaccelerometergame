package game.base;

import game.src.AndroidAccelerometerGameActivity;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameThread extends DrawablePanel {
	
	private AndroidAccelerometerGameActivity activityReference;
	private BaseCanvas lastCanvas;
	private BaseCanvas currentCanvas;
	
	public GameThread(AndroidAccelerometerGameActivity context) {
		super(context);
		activityReference = context;
		activityReference.setSensorListener(this);
	}
	
	public AndroidAccelerometerGameActivity getBaseActivity() {
		return activityReference;
	}

	public void changeScreen(BaseCanvas newScreen) {
		lastCanvas = currentCanvas;
		currentCanvas = newScreen;
	}
	
	public void reloadLastScreen() {
		changeScreen(lastCanvas);
	}

	public void onInitialize() {
	}
	
	public void onDraw(Canvas canvas) {
		currentCanvas.onDraw(canvas);
	}
	
	public void onUpdate(long difftime) {
		currentCanvas.onUpdate(difftime);
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		currentCanvas.onAccuracyChanged(sensor, accuracy);
	}
	
	public void onSensorChanged(SensorEvent event) {
		currentCanvas.onSensorChanged(event);
	}

	public boolean onTouch(View v, MotionEvent event) {
		return currentCanvas.onTouch(v, event);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return currentCanvas.onTouchEvent(event);
	}

	public int getFPS() {
		return getThread().getFPS();
	}

}
