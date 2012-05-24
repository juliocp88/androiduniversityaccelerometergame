package game.src;

import game.base.ImagePlus;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Platform {
	
	int x,y;
	int sizeX,sizeY;
	public ImagePlus imgPlatform;
	boolean alive;
	int broken =0;
	Paint paint;
	int typePlatForm;
	boolean change=false;
	
	int testeTime =0;
	
	// type 0 = plataforma normal // type 1 = platadorma que some // type 2 = plataforma quebravel //type 3 = platadorma movel // 
	
	public Platform(int x_,int y_, int typePlatForm_){
		creat(typePlatForm_);
		x=x_;
		y=y_;
		sizeX = imgPlatform.getWidth();
		sizeY = imgPlatform.getHeight();
		alive=true;
		typePlatForm = typePlatForm_;

	}
	
	public void onUpdate(long difftime){
		
		if(y>=Properties.getScreenSize().y){
			alive=false;
		}

		if(typePlatForm==2){
			
			if(broken==1){
				imgPlatform = Images.imagePlatThreeBroken;
				broken=2;
			}else if(broken==2){
				y+=50*difftime/1000;
			}
		}else if(typePlatForm==3){
			
			if(change){
				x-=20*difftime/1000;
			}else{
				x+=20*difftime/1000;
			}
			if((x+sizeX+5)>Properties.getScreenSize().x){
				change=true;
			}
			if(x<=5){
				change=false;
			}
		}
	}
	
	public void Draw(Canvas canvas,Paint paint){
		
		 canvas.drawBitmap(imgPlatform.getImage(), x, y, paint);
	}
	
	public void creat(int index){
		switch (index) {
		case 0:
			imgPlatform = Images.imagePlatOne;
		break;
		case 1:
			imgPlatform = Images.imagePlatTwo;
		break;
		case 2:
			imgPlatform = Images.imagePlatThree;
		break;
		case 3:
			imgPlatform = Images.imagePlatFour;
		break;
		default:
			break;
		}
		
	}
	
	

}
