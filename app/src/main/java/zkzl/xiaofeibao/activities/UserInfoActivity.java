package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.CircleImageView;

@ContentView(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.username_layout)
    private LinearLayout usernameLinearLayout;

    @ViewInject(R.id.profile_image)
    private CircleImageView circleImageView;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("基本信息");
        imageLoader = MyApplication.getInstance().getImageLoader();
        circleImageView.setImageUrl("http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu2.jpg",imageLoader);
      
    }

    @Event(R.id.back_button)
    private void backEvent(View v) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.username_layout)
    private void editUsernameClick(View view) {
        startOtherActivity(ACTION_SET_USERNAME);
        startActivityAnimation();
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }
}
