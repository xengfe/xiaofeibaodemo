package zkzl.xiaofeibao.activities;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.persenter.LoginPersenter;
import zkzl.xiaofeibao.view.ILoginView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ILoginView {


    @ViewInject(R.id.login_button)
    private Button loginButton;
    @ViewInject(R.id.user_name)
    private EditText userNameEditText;
    @ViewInject(R.id.password)
    private EditText passwordEditText;

    private LoginPersenter mPersenter;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        mPersenter = new LoginPersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("登录中...");
        dialog.setCancelable(false);
    }

    @Event(R.id.login_button)
    private void loginEvent(View v) {
        hideSystemKeyBoard(this,v);
        mPersenter.loginSuccess(userNameEditText.getText().toString(),
                passwordEditText.getText().toString());
    }


    @Event(R.id.regist_button)
    private void registEvent(View v) {
        hideSystemKeyBoard(this,v);
        startOtherActivity(ACTION_REGIST);
        startActivityAnimation();
    }

    @Event(R.id.find_pwd_button)
    private void findPwdClick(View v) {
        hideSystemKeyBoard(this,v);
        startOtherActivity(ACTION_FIND_PASSWORD);
        startActivityAnimation();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MainActivity.TAG_EXIT, true);
        startActivity(intent);

    }


    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showLoadding() {
        dialog.show();
    }

    @Override
    public void hideLoadding() {
        dialog.cancel();
    }

    @Override
    public void moveToMain() {
        sharedPreferenceManager.saveBoolean(LoginActivity.this,LOGIN_STATUS,true);
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        startActivityAnimation();
    }
}
