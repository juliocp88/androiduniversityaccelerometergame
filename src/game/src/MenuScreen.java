package game.src;

import game.base.BaseCanvas;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class MenuScreen extends BaseCanvas
{
	Paint defaultPaint;

	public MenuScreen(AndroidAccelerometerGameActivity _activity)
	{
		super(_activity);
		defaultPaint = new Paint();
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(Images.backgroundMenu.getImage(), Images.backgroundMenu.getArea(), Properties.getScreenArea(), defaultPaint);
	}

	@Override
	public void onUpdate(long difftime)
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		getGame().changeScreen(new GameScreen(getBaseActivity()));
		return false;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return false;
	}

}
