package game.src;

import game.base.BaseCanvas;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class MenuScreen extends BaseCanvas {
	Paint paintText;
	public MenuScreen(AndroidAccelerometerGameActivity _activity) {
		super(_activity);
		paintText = new Paint();
		paintText.setColor(Color.RED);
		paintText.setTextSize(40);
	}
	
	public void onDraw(Canvas canvas) {
		canvas.drawText("Tap to begin", Properties.getScreenSize().x * .5f - 50, Properties.getScreenSize().y * .5f, paintText);
	}
	
	public void onUpdate(long difftime) {
	}
	
	public void onSensorChanged(SensorEvent event) {
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		getGame().changeScreen(new GameScreen(getBaseActivity()));
		return false;
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	
}
