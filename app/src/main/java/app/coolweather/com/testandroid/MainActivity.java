package app.coolweather.com.testandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;


public class MainActivity extends Activity {
    ImageLoader imageLoader = ImageLoader.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView test = (ImageView) findViewById(R.id.test);
        imageLoader.displayImage("assets://img/back.png", test);
    }
}
