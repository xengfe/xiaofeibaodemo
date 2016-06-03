package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.umeng.update.UmengUpdateAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.SystemExitDialog;
import zkzl.xiaofeibao.widget.VersionDialog;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity implements
        VersionDialog.VersionListener ,SystemExitDialog.ExitListener {



    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView otherButton;

    @ViewInject(R.id.avoid_disturb_button)
    private Button avoidDisturbButton;

    @ViewInject(R.id.reset_password_button)
    private Button resetPwdButton;

    @ViewInject(R.id.reset_phone_button)
    private Button resetPhoneButton;

    @ViewInject(R.id.about_us_button)
    private Button aboutButton;

    @ViewInject(R.id.log_out_button)
    private Button logoutButton;

    @ViewInject(R.id.check_version_button)
    private Button versionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("设置");
    }

    @Event(R.id.back_button)
    private void backEvent(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.avoid_disturb_button)
    private void avoidDisturbClick(View view) {
        startOtherActivity(ACTION_AVOID_DISTURB);
        startActivityAnimation();
    }

    @Event(R.id.reset_password_button)
    private void resetPwdClick(View view) {
        startOtherActivity(ACTION_RESET_PASSWORD);
        startActivityAnimation();
    }

    @Event(R.id.reset_phone_button)
    private void resetPhoneButton(View view) {
        startOtherActivity(ACTION_RESET_PHONE);
        startActivityAnimation();
    }

    @Event(R.id.suggestion_button)
    private void suggestClick(View view) {
        startOtherActivity(ACTION_SUGGESTION);
        startActivityAnimation();
    }

    @Event(R.id.about_us_button)
    private void aboutClick(View view) {
        startOtherActivity(ACTION_ABOUT);
        startActivityAnimation();
    }

    @Event(R.id.log_out_button)
    private void logOutClick(View view) {
//        LogoutPopu logoutPopu = new LogoutPopu(this, "确认退出", this);
//        logoutPopu.showAtLocation(findViewById(R.id.setting_main), Gravity.CENTER, 0, 0);

        SystemExitDialog exitDialog = new SystemExitDialog(this, "确认退出", this);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.show();
    }

    @Event(R.id.check_version_button)
    private void checkVersionClick(View view) {
        UmengUpdateAgent.forceUpdate(this);
//        VersionPopuWin versionPopuWin = new VersionPopuWin(this, "版本已最新", this);
//        versionPopuWin.showAtLocation(findViewById(R.id.setting_main), Gravity.CENTER, 0, 0);
        VersionDialog versionDialog = new VersionDialog (this, "版本已最新", this);
        versionDialog.setCanceledOnTouchOutside(false);
        versionDialog.show();
    }

    @Override
    public void exit(View view) {
        switch (view.getId()) {
            case R.id.alert_ok_button:

                break;

            case R.id.logout_ok_button:
                screenManager.popAllActivityExceptOne(SettingActivity.class);
                sharedPreferenceManager.saveBoolean(this, LOGIN_STATUS, false);
                startOtherActivity(ACTION_LOGIN);
                this.finish();
                break;

            default:
                break;
        }
    }


    @Override
    public void showVersion(View view) {

    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }
}
