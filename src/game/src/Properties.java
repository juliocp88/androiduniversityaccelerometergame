package game.src;

import android.graphics.Point;
import android.graphics.RectF;

public class Properties {
	
	private static Point screenSize = new Point(0, 0);
	private static RectF screenArea;
	public static void setScreenSize(int width, int height) {
		screenSize.x = width;
		screenSize.y = height;
		screenArea = new RectF(0, 0, width, height);
	}
	
	public static Point getScreenSize() {
		return screenSize;
	}
	
	public static RectF getScreenArea(){
		return screenArea;
	}

}
