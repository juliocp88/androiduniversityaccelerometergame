package game.src;

import game.base.BaseCanvas;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

public class GameScreen extends BaseCanvas
{
	private final Paint mPaint;
	private final MainCharacter character;
	private final MapPlatforms mapPlatforms;
	private int gameOverCount = 0;
	private final Paint textPaint = new Paint();

	public GameScreen(AndroidAccelerometerGameActivity _activity)
	{
		super(_activity);
		mPaint = new Paint();
		textPaint.setTextSize(20);
		textPaint.setColor(Color.RED);
		character = new MainCharacter(Properties.getScreenSize().x * .5, Properties.getScreenSize().y - 50);
		character.setSize(new Point(Images.imageCharacter.getWidth(), Images.imageCharacter.getHeight()));
		mapPlatforms = new MapPlatforms(Properties.getScreenSize().x, Properties.getScreenSize().y);
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(Images.background.getImage(), Images.background.getArea(), Properties.getScreenArea(), mPaint);
		mapPlatforms.draw(canvas);
		character.draw(canvas);
		canvas.drawText("Score: " + (int) (character.getScore()), 15, 15, textPaint);
	}

	@Override
	public void onUpdate(long difftime)
	{
		mapPlatforms.onUpdate(difftime);
		character.update(difftime);
		mapPlatforms.checkCharacterCollision(character);
		mapPlatforms.moveMap(character);
		character.setScore(mapPlatforms.getMovedY());
		if (gameOverCount > 0)
		{
			gameOverCount -= difftime;
			if (gameOverCount <= 0)
			{
				getGame().changeScreen(new GameOverScreen(getBaseActivity(), character.getScore()));
			}
		}
		if (character.isDead() && gameOverCount <= 0)
		{
			gameOverCount = 800;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			float accX = -event.values[0] / SensorManager.GRAVITY_EARTH;
			float accY = -event.values[1] / SensorManager.GRAVITY_EARTH;
			float totAcc = (float) Math.sqrt((accX * accX) + (accY * accY));
			float tiltX = (float) Math.asin(accX / totAcc);
			float tiltY = (float) Math.asin(accY / totAcc);
			character.updateAccelerometerValues(tiltX, tiltY);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getX() < Properties.getScreenSize().x / 2)
		{
			character.updateTouchSpeed(-1.5f);
		} else
		{
			character.updateTouchSpeed(1.5f);
		}
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
