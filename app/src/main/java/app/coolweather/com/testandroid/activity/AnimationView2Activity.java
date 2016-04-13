package app.coolweather.com.testandroid.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.coolweather.com.testandroid.R;
/**
 * 两个活动的中的view交互动画（单个view的消失动画）：ViewCompat
 * 用于4.0+，否则没有动画效果
 */
public class AnimationView2Activity extends AppCompatActivity {
    private ImageView img;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_view2);
        img = (ImageView) findViewById(R.id.img);
        img1 = (ImageView) findViewById(R.id.img1);
    }

    public void onClick(View view) {
        removeDoneFab(new Runnable() {
            @Override
            public void run() {
                if (null == img) {
                    performSignInWithTransition(img);//没有选择头像？？
                } else {
                    performSignInWithTransition(img);//点中的view（头像）用来做动画
                }
            }
        });
    }
    private void removeDoneFab(@Nullable Runnable endAction) {//对号消失动画后执行线程
        ViewCompat.animate(img1)
                .scaleX(0)
                .scaleY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .withEndAction(endAction)
                .start();
    }
    private void performSignInWithTransition(View v) {

        final Pair[] pairs = createSafeTransitionParticipants(this, true,
                new Pair<>(v, getString(R.string.transition_avatar)));//头像平移动画（描述），3个元素的数组（statusBar、navigationBar、v）
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, pairs);//启动activity和添加动画:创建 ActivityOptions 活动使用交叉活动场景动画之间的过渡
        AnimationActivity2.start(this, activityOptions);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar,
                                                                        @Nullable Pair... otherParticipants) {
        // Avoid system UI glitches as described here:
        // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
        View decor = activity.getWindow().getDecorView();//获得装饰view
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);//获取statusBar
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);//导航bar

        // Create pair of transition participants.
        List<Pair> participants = new ArrayList<>(3);//参与的3个
        addNonNullViewToTransitionParticipants(statusBar, participants);//添加进去
        addNonNullViewToTransitionParticipants(navBar, participants);//2个
        // only add transition participants if there's at least one none-null element，至少要有一个
        if (otherParticipants != null && !(otherParticipants.length == 1
                && otherParticipants[0] == null)) {//返回指定数组中的对象的List。不能修改List的大小，即添加和删除不受支持，但可以设置元素。设置元素修改底层数组。
            participants.addAll(Arrays.asList(otherParticipants));//总共添加了3，把数组转化为list对象（静态的），
        }
        //noinspection unchecked
        return participants.toArray(new Pair[participants.size()]);//转化为数组，当使用ArrayList时，有时想获得一个实际的数组
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new Pair<>(view, view.getTransitionName()));
    }
}
