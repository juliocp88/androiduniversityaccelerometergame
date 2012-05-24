package game.src;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.hardware.SensorManager;

public class MainCharacter extends BaseCharacter
{
	private final Paint p;
	private final PointF accelerometerValues;
	double vectorY = 0;
	private double touchSpeed = 0;
	private float score = 0;
	private boolean dead = false;

	public MainCharacter(double X, double Y)
	{
		this.X = X;
		this.Y = Y;
		accelerometerValues = new PointF(0, 0);
		p = new Paint();
		p.setAntiAlias(true);
	}

	public void setScore(float _score)
	{
		score = _score;
	}

	public boolean isDead()
	{
		return dead;
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
		double oldY = Y;
		vectorY -= SensorManager.GRAVITY_EARTH;
		if (accelerometerValues.x == 0)
		{
			X += (touchSpeed * 100) * difftimePsecond;
		} else
		{
			X += (accelerometerValues.x * 200) * difftimePsecond;
		}
		Y -= vectorY * difftimePsecond;
		if (handleOutOfScreen(X, Y))
		{
			Y = oldY;
		}
	}

	private boolean handleOutOfScreen(double _X, double _Y)
	{
		boolean leftCollision = _X + getSize().x < 0;
		boolean rightCollision = _X > Properties.getScreenArea().right;
		boolean topCollision = _Y < 0;
		boolean bottomCollision = _Y + getSize().y > Properties.getScreenArea().bottom;
		if (bottomCollision)
		{
			if (score == 0)
			{
				jump();
			} else
			{
				dead = true;
				return false;
			}
		}
		if (leftCollision)
		{
			X = Properties.getScreenArea().right;
		} else if (rightCollision)
		{
			X = -getSize().x;
		}
		return bottomCollision || topCollision;
	}

	public void updateAccelerometerValues(float tiltX, float tiltY)
	{
		accelerometerValues.x = tiltX;
		accelerometerValues.y = tiltY;
	}

	public void jump()
	{
		vectorY = 400;
	}

	public void updateTouchSpeed(float speed)
	{
		touchSpeed = speed;
	}

	public boolean handleCollisionWithPlatform(Platform platform)
	{
		if (vectorY < 0)
		{
			RectF platformRect = new RectF(platform.x, platform.y, platform.x + platform.sizeX, platform.y + platform.sizeY * .25f);
			RectF characterRect = new RectF((float) X, (float) Y + getSize().y * .75f, (float) X + getSize().x, (float) Y + getSize().y);
			if (platformRect.intersect(characterRect))
			{
				if (platform.characterJumped(this))
				{
					jump();
				}
			}
		}
		return false;
	}

	public float getScore()
	{
		return score;
	}

}
