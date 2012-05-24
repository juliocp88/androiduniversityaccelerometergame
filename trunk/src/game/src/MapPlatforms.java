package game.src;

import java.util.Random;
import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MapPlatforms {
	
	int x,y;
	int sizeXView,sizeYView;
	Vector<Platform> listPlatform;
	Paint paint;
	Random rand;
	public MapPlatforms(int sizeXView_ ,int sizeYView_){
		sizeXView = sizeXView_;
		sizeYView = sizeYView_;
		paint = new Paint(); 
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        rand = new Random();
        listPlatform = new Vector<Platform>();
        randPlatform(-400);
        
	}
	public void onUpdate(long difftime){
         for (int i=0;i<listPlatform.size();i++) {
        	 listPlatform.elementAt(i).onUpdate(difftime);	
			if(! listPlatform.elementAt(i).alive){
				listPlatform.remove(i);
			}
		 }  
	}
	
	public void draw(Canvas canvas){
    	for (Platform plat : listPlatform) {
    		plat.Draw(canvas, paint);
  		}
		
	}
	
	public void randPlatform(int value){
		
		for(int i=0;i<(((sizeYView)/30));i++){
			int randY = (40*(i+2));
			Platform plat = new Platform(rand.nextInt(sizeXView-50), (value+randY), randPlatType());
			listPlatform.add(plat);
		}
		
	}
	
	int randPlatType(){
		switch (rand.nextInt(10)) {
		case 1:
			return 1;
		case 3:
			return 2;
		case 5:
				
			return 3;
		default:
			return 0;
		}
		
	}

}
