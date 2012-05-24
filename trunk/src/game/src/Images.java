package game.src;

import game.base.ImagePlus;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Images {
	public static ImagePlus background;
	public static ImagePlus imagePlatOne,imagePlatTwo,imagePlatThree,imagePlatThreeBroken,imagePlatFour, imageCharacter;
	
	public static void LoadAll(AssetManager manager) {
		background = loadImage("background.png", manager);
		imagePlatOne =  loadImage("imgPlatFormOne.png", manager);
		imagePlatTwo =  loadImage("imgPlatFormTwo.png", manager);
		imagePlatThree =  loadImage("imgPlatFormThree.png", manager);
		imagePlatThreeBroken =  loadImage("imgPlatFormThreeBroken.png", manager);
		imagePlatFour =  loadImage("imgPlatFormFour.png", manager);
		imageCharacter = loadImage("character.PNG", manager);
	}

	private static ImagePlus loadImage(String string, AssetManager manager) {
		ImagePlus tmp = new ImagePlus();
		tmp.loadImage(string, manager);
		return tmp;
	}
	

	
}
