package game.src;

import android.graphics.Canvas;
import android.graphics.Point;

public abstract class BaseCharacter
{
	public double X;
	public double Y;
	private Point size;

	public Point getSize()
	{
		return size;
	}

	public void setSize(Point size)
	{
		this.size = size;
	}

	public boolean vivo;

	public abstract void update(long DiffTime);

	public abstract void draw(Canvas canvas);

}
