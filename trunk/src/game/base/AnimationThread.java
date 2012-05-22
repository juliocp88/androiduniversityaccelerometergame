package game.base;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class AnimationThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private Entity panel;
	private boolean run = false;
	
	public AnimationThread(SurfaceHolder surfaceHolder, Entity panel) {
		this.surfaceHolder = surfaceHolder;
		this.panel = panel;
		panel.onInitialize();
	}
	
	public void setRunning(boolean value) {
		run = value;
	}
	
	private long timer;
	private long lastTime = 0;
	private long lastSecond = 0;
	private int fps = 0;
	private int fpsCount = 0;
	@Override
	public void run() {
		
		Canvas c;
		lastSecond = System.currentTimeMillis();
		lastTime = lastSecond;
		while (run) {
			c = null;
			panel.onUpdate(timer);
			try {
				c = surfaceHolder.lockCanvas(null);
				panel.onDraw(c);
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			timer = System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (lastSecond / 1000 != lastTime / 1000) {
				lastSecond = lastTime;
				fps = fpsCount;
				fpsCount = 0;
			} else {
				fpsCount++;
			}
		}
	}
	
	public int getFPS() {
		return fps;
	}
}