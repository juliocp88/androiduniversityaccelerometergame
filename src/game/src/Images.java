package game.src;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Images {
	public static Bitmap exampleImage;
	
	public static void LoadAll(AssetManager manager) {
		exampleImage = loadImage("", manager);
	}
	
	private static Bitmap loadImage(String path, AssetManager manager) {
		Bitmap dst = null;
		try {
			dst = BitmapFactory.decodeStream(manager.open(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dst;
	}
	
	public static Rect getRectf(Bitmap image) {
		return new Rect(0, 0, image.getWidth(), image.getHeight());
	}
}
