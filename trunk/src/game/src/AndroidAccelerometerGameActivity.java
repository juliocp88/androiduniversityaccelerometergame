package game.src;

import game.base.GameThread;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class AndroidAccelerometerGameActivity extends Activity
{
	/** Called when the activity is first created. */
	private GameThread gameThread;
	private SensorManager sensorManager;
	private SensorEventListener sensorListener;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		super.onCreate(savedInstanceState);
		setKeepScreenOn(this, true);
		loadProperties();
		loadResources();
		gameThread = new GameThread(this);
		gameThread.changeScreen(new MenuScreen(this));
		setContentView(gameThread);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

	}

	private void loadResources()
	{
		Images.LoadAll(getAssets());
	}

	public void setKeepScreenOn(Activity activity, boolean keepScreenOn)
	{
		if (keepScreenOn)
		{
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} else
		{
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
	}

	private void loadProperties()
	{
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight() - 40;
		Properties.setScreenSize(width, height);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		sensorManager.unregisterListener(sensorListener);
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		sensorManager.unregisterListener(sensorListener);
	}

	public void setSensorListener(SensorEventListener listener)
	{
		sensorListener = listener;
	}

	public GameThread getGame()
	{
		return gameThread;
	}
}