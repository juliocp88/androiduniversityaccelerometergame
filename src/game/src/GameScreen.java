package game.src;

import game.base.BaseCanvas;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameScreen extends BaseCanvas {
	
	public GameScreen(AndroidAccelerometerGameActivity _activity) {
		super(_activity);
	}
	
	public void onDraw(Canvas canvas) {
	}
	
	public void onUpdate(long difftime) {
	}
	
	public void onSensorChanged(SensorEvent event) {
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	
}
