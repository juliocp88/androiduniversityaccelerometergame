package game.src;

import android.graphics.Canvas;

public abstract class BaseCharacter {
		public double X;
		public double Y;
		
		public boolean vivo;
		
		public abstract void update(long DiffTime);
		public abstract void draw(Canvas canvas);

}
