package game.base;

import game.src.AndroidAccelerometerGameActivity;
import game.src.Properties;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameThread extends DrawablePanel {
	
	private AndroidAccelerometerGameActivity activityReference;
	private BaseCanvas lastCanvas;
	private BaseCanvas currentCanvas;
	private Paint clearCanvas;
	private RectF screenRect;
	public GameThread(AndroidAccelerometerGameActivity context) {
		super(context);
		activityReference = context;
		activityReference.setSensorListener(this);
		clearCanvas = new Paint();
		clearCanvas.setColor(Color.BLACK);
		screenRect = new RectF(0, 0, Properties.getScreenSize().x, Properties.getScreenSize().y);
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
		clearScreen(canvas);
		currentCanvas.onDraw(canvas);
	}
	
	private void clearScreen(Canvas canvas) {
		canvas.drawRect(screenRect, clearCanvas);
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
