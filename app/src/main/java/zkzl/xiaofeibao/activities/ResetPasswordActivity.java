package zkzl.xiaofeibao.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.persenter.ResetPwdPerstener;
import zkzl.xiaofeibao.view.IResetPwdView;

@ContentView(R.layout.activity_reset_password)
public class ResetPasswordActivity extends BaseActivity implements IResetPwdView{

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView saveButton;
    @ViewInject(R.id.reset_old_pwd_1)
    private EditText oldPwdEditText;
    @ViewInject(R.id.reset_new_pwd_1)
    private EditText newPwdEditText;
    @ViewInject(R.id.reset_new_pwd_2)
    private EditText affrimPwdEditText;

    private ResetPwdPerstener pwdPerstener;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.setText("保存");
        titleButton.setText("密码修改");

        pwdPerstener = new ResetPwdPerstener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("修改中...");
        dialog.setCanceledOnTouchOutside(false);
    }


    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.other_button)
    private void resetPwdEvent(View v) {
        pwdPerstener.resetPwd("phone",
                oldPwdEditText.getText().toString().trim(),
                newPwdEditText.getText().toString().trim(),
                affrimPwdEditText.getText().toString().trim());
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

    @Override
    public void toLogin() {
        screenManager.popAllActivityExceptOne(ResetPasswordActivity.class);
        sharedPreferenceManager.saveBoolean(this, LOGIN_STATUS, false);
        startOtherActivity(ACTION_LOGIN);
        this.finish();
    }


    @Override
    public void hideLoadding() {
        dialog.dismiss();
    }


    @Override
    public void showLoadding() {
        dialog.show();
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
