package game.src;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MainCharacter extends BaseCharacter{
	public MainCharacter(double X, double Y) {
		this.X = X;
		this.Y = Y;
	}
	
	@Override
	public void draw(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);
		canvas.drawBitmap(Images.imageCharacter,(float) X, (float)Y, p);
}
	@Override
	public void update(long DiffTime) {
			
	}
	
//	public void moves(int side){
//		switch (side) {
//		case 1:
//		esquerda
//	
//			break;
//
//		case 2:
//		direita	
//	
//			break;
//		}
//	}
}
