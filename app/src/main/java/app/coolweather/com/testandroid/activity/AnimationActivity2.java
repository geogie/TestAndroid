package app.coolweather.com.testandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import app.coolweather.com.testandroid.R;

public class AnimationActivity2 extends AppCompatActivity {
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        img = (ImageView) findViewById(R.id.img);
    }
    public static void start(Activity activity, ActivityOptionsCompat options) {
        Intent starter = getStartIntent(activity);
        ActivityCompat.startActivity(activity, starter, options.toBundle());//为该活动应该如何启动的附加选项
    }
    static Intent getStartIntent(Context context) {
        Intent starter = new Intent(context, AnimationActivity2.class);
        return starter;
    }

}
