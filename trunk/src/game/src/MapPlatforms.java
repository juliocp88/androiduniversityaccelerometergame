package game.src;

import game.src.Platform.platformTypes;

import java.util.Random;
import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MapPlatforms
{

	int x , y = 0;
	int sizeXView , sizeYView;
	Vector<Platform> listPlatform;
	Paint paint;
	Random rand;
	float moveY = 0;
	float speedMoveUP = 5.0f;
	float count = 0;
	final int startPlatforms = 40;
	final int randPlatforms = (int) (startPlatforms * .4f);
	private float yMoved = 0;

	public MapPlatforms(int sizeXView_, int sizeYView_)
	{
		sizeXView = sizeXView_;
		sizeYView = sizeYView_;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(1);
		rand = new Random();
		listPlatform = new Vector<Platform>();
		randPlatform(-800, Properties.getScreenSize().y, startPlatforms);
	}

	public void onUpdate(long difftime)
	{
		float moveMapStep = 0;
		if (moveY > 0)
		{
			moveMapStep = speedMoveUP * (difftime / 1000.0f);
			yMoved += moveMapStep;
		}
		moveY -= moveMapStep;
		if (listPlatform.size() < randPlatforms)
		{
			count = 0;
			randPlatform(-800, 0, randPlatforms);
		}
		for (int i = 0; i < listPlatform.size(); i++)
		{
			Platform platform = listPlatform.elementAt(i);
			platform.onUpdate(difftime);
			platform.y += moveMapStep;
			if (!platform.alive)
			{
				listPlatform.remove(i);
				i--;
			}
		}
	}

	public float getMovedY()
	{
		return yMoved;
	}

	public void moveMap(MainCharacter character)
	{
		if (character.Y < Properties.getScreenSize().y * .5f && moveY <= 100)
		{
			float diffY = Properties.getScreenSize().y * .5f - (float) character.Y;
			y += diffY;
			moveY = diffY;
			count += diffY;
			speedMoveUP = moveY * 1.3f;
		} else
		{
			moveY = 0;
		}
	}

	public void draw(Canvas canvas)
	{
		for (Platform plat : listPlatform)
		{
			plat.Draw(canvas, paint);
		}

	}

	public void randPlatform(int start, int end, int blockCount)
	{
		Random rnd = new Random(System.currentTimeMillis());
		int diff = end - start;
		for (int i = 0; i < blockCount; i++)
		{
			int randY = rnd.nextInt(diff);
			Platform plat = new Platform(rand.nextInt(sizeXView - 50), (start + randY), randPlatType());
			listPlatform.add(plat);
		}

	}

	platformTypes randPlatType()
	{
		switch (rand.nextInt(10))
		{
		case 1:
			return platformTypes.OneJump;
		case 3:
			return platformTypes.Breakable;
		case 5:
			return platformTypes.Movable;
		default:
			return platformTypes.Normal;
		}

	}

	public void checkCharacterCollision(MainCharacter character)
	{
		for (Platform platform : listPlatform)
		{
			if (character.handleCollisionWithPlatform(platform))
			{
				break;
			}
		}
	}

}
