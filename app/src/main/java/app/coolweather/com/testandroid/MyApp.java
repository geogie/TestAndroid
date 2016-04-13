package app.coolweather.com.testandroid;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by Administrator on 2016/3/25 0025.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration
                .Builder(this)
                .threadPoolSize(3)//1-5
                .threadPriority(Thread.NORM_PRIORITY)
                .memoryCacheExtraOptions(480, 800)// maxwidth, max height，即保存的每个缓存文件的最大长宽
                .memoryCache(new WeakMemoryCache())
//                .writeDebugLogs()// Remove for releaseapp
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}
