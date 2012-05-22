package game.src;

import android.graphics.Point;

public class Properties {
	
	private static Point screenSize = new Point(0, 0);
	
	public static void setScreenSize(int width, int height) {
		screenSize.x = width;
		screenSize.y = height;
	}
	
	public static Point getScreenSize() {
		return screenSize;
	}

}
