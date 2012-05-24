package game.src;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.hardware.SensorManager;

public class MainCharacter extends BaseCharacter
{
	private final Paint p;
	private final PointF accelerometerValues;
	private double vectorY = 0;
	private double touchSpeed = 0;

	public MainCharacter(double X, double Y)
	{
		this.X = X;
		this.Y = Y;
		accelerometerValues = new PointF(0, 0);
		p = new Paint();
		p.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(Images.imageCharacter.getImage(), (float) X, (float) Y, p);
	}

	@Override
	public void update(long difftime)
	{
		double difftimePsecond = (difftime / 1000.0f);
		double oldX = X, oldY = Y;
		vectorY -= SensorManager.GRAVITY_EARTH;
		if (accelerometerValues.x == 0)
		{
			X += (touchSpeed * 100) * difftimePsecond;
		} else
		{
			X += (accelerometerValues.x * 100) * difftimePsecond;
		}
		Y -= vectorY * difftimePsecond;
		if (handleOutOfScreen(X, Y))
		{
			X = oldX;
			Y = oldY;
		}
	}

	private boolean handleOutOfScreen(double X, double Y)
	{
		boolean leftCollision = X < 0;
		boolean rightCollision = X + getSize().x > Properties.getScreenArea().right;
		boolean topCollision = Y < 0;
		boolean bottomCollision = Y + getSize().y > Properties.getScreenArea().bottom;
		if (bottomCollision)
		{
			jump();
		}
		return leftCollision || rightCollision || bottomCollision || topCollision;
	}

	public void updateAccelerometerValues(float tiltX, float tiltY)
	{
		accelerometerValues.x = tiltX;
		accelerometerValues.y = tiltY;
	}

	public void jump()
	{
		vectorY = 500;
	}

	public boolean collidedWithRectangle(float left, float top, float right, float bottom)
	{
		return X >= left && X <= right && Y >= top && Y <= bottom;
	}

	public void updateTouchSpeed(float speed)
	{
		touchSpeed = speed;
	}

}
