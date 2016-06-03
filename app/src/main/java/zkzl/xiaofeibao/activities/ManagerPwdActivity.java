package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
@ContentView(R.layout.activity_manager_pwd)
public class ManagerPwdActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("密码管理");

    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.change_pay_pwd)
    private void changePayPwdEvent(View v) {
        startOtherActivity(ACTION_RESET_PAYPWD);
    }

    @Event(R.id.forget_pay_pwd)
    private void forgetPayPwdEvent(View v) {
        startOtherActivity(ACTION_FORGET_PAYPWD);
    }



    @Override
    public void onBackPressed() {
        backPressed(this);
    }

}
