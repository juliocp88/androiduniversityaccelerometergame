package game.base;

import game.src.AndroidAccelerometerGameActivity;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public abstract class BaseCanvas {
	
	private AndroidAccelerometerGameActivity activity;
	
	public BaseCanvas(AndroidAccelerometerGameActivity _activity) {
		activity = _activity;
	}
	
	public AndroidAccelerometerGameActivity getBaseActivity() {
		return activity;
	}
	
	public GameThread getGame() {
		return activity.getGame();
	}

	public abstract void onDraw(Canvas canvas);
	public abstract void onUpdate(long difftime);
	public abstract void onSensorChanged(SensorEvent event);
	public abstract boolean onTouchEvent(MotionEvent event);
	
	public abstract void onAccuracyChanged(Sensor sensor, int accuracy);
	
	public abstract boolean onTouch(View v, MotionEvent event);

}
