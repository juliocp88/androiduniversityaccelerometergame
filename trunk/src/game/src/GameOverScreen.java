package game.src;

import game.base.BaseCanvas;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameOverScreen extends BaseCanvas
{
	private int score = 0;
	Paint defaultPaint;
	Paint paintText;

	public GameOverScreen(AndroidAccelerometerGameActivity _activity, float _score)
	{
		super(_activity);
		score = (int) _score;
		defaultPaint = new Paint();
		paintText = new Paint();
		paintText.setTextSize(40);
		paintText.setColor(Color.RED);
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(Images.backgroundGameOver.getImage(), Images.backgroundGameOver.getArea(), Properties.getScreenArea(), defaultPaint);
		canvas.drawText("Score:    " + score, Properties.getScreenSize().x * .5f - 130, Properties.getScreenSize().y * .4f, paintText);
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
		getGame().changeScreen(new MenuScreen(getBaseActivity()));
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
