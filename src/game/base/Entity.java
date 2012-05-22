package game.base;

import android.graphics.Canvas;

public interface Entity {
	void onInitialize();
	void onDraw(Canvas canvas);
	void onUpdate(long difftime);
}
