package game.src;

import game.base.ImagePlus;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorManager;

public class Platform
{

	float x , y;
	int sizeX , sizeY;
	public ImagePlus imgPlatform;
	boolean alive;
	int broken = 0;
	Paint paint;
	platformTypes typePlatForm;
	boolean change = false;
	int testeTime = 0;
	private final float speed = 50;

	enum platformTypes
	{
		Normal, OneJump, Breakable, Movable
	}

	private double vectorY = 0;

	public Platform(int x_, int y_, platformTypes typePlatForm_)
	{
		create(typePlatForm_);
		x = x_;
		y = y_;
		sizeX = imgPlatform.getWidth();
		sizeY = imgPlatform.getHeight();
		alive = true;
		typePlatForm = typePlatForm_;

	}

	public void onUpdate(long difftime)
	{
		double difftimePsecond = (difftime / 1000.0f);
		if (y >= Properties.getScreenSize().y)
		{
			alive = false;
		}
		if (typePlatForm == platformTypes.Breakable)
		{
			if (broken == 2)
			{
				vectorY -= SensorManager.GRAVITY_EARTH;
				y -= vectorY * difftimePsecond;
			}
		} else if (typePlatForm == platformTypes.Movable)
		{
			if (change)
			{
				x -= speed * difftimePsecond;
			} else
			{
				x += speed * difftimePsecond;
			}
			if ((x + sizeX + 5) > Properties.getScreenSize().x)
			{
				change = true;
			}
			if (x <= 5)
			{
				change = false;
			}
		}
	}

	public void Draw(Canvas canvas, Paint paint)
	{
		canvas.drawBitmap(imgPlatform.getImage(), x, y, paint);
	}

	public void create(platformTypes typePlatForm_)
	{
		switch (typePlatForm_)
		{
		case Normal:
			imgPlatform = Images.imagePlatOne;
			break;
		case OneJump:
			imgPlatform = Images.imagePlatTwo;
			break;
		case Breakable:
			imgPlatform = Images.imagePlatThree;
			break;
		case Movable:
			imgPlatform = Images.imagePlatFour;
			break;
		default:
			break;
		}

	}

	public boolean characterJumped(MainCharacter character)
	{
		if (typePlatForm == platformTypes.Breakable)
		{
			vectorY = character.vectorY;
			broken = 2;
			imgPlatform = Images.imagePlatThreeBroken;
			return false;
		} else if (typePlatForm == platformTypes.OneJump)
		{
			alive = false;
		}
		return true;
	}

}
