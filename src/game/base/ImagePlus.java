package game.base;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ImagePlus
{
	private Bitmap image;
	private Rect area;

	public boolean loadImage(String path, AssetManager manager)
	{
		Bitmap dst = null;
		try
		{
			dst = BitmapFactory.decodeStream(manager.open(path));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		image = dst;
		createArea();
		return image != null;
	}

	private void createArea()
	{
		if (image != null)
		{
			area = new Rect(0, 0, image.getWidth(), image.getHeight());
		}
	}

	public Rect getArea()
	{
		return area;
	}

	public Bitmap getImage()
	{
		return image;
	}

	public int getWidth()
	{
		return area.width();
	}

	public int getHeight()
	{
		return area.height();
	}

}
