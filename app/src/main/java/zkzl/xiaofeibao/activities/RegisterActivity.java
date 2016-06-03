package zkzl.xiaofeibao.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


import cn.smssdk.EventHandler;

import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.MyCountTimer;
import zkzl.xiaofeibao.persenter.RegistPersenter;

import zkzl.xiaofeibao.utils.RegUtils;
import zkzl.xiaofeibao.view.IRegistView;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements IRegistView {



    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.regist_new_button)
    private Button registButton;

    @ViewInject(R.id.user_name)
    private EditText userNameEditText;

    @ViewInject(R.id.user_phone)
    private EditText userPhoneEditText;

    @ViewInject(R.id.phone_reg)
    private EditText phoneRegEditText;

    @ViewInject(R.id.send_phone_reg_button)
    private Button sendRegButton;

    @ViewInject(R.id.user_password)
    private EditText passwordEditText;

    @ViewInject(R.id.affirm_password)
    private EditText affirmPasswordEditText;

    private ProgressDialog dialog;
    private RegistPersenter registPersenter;


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    registPersenter.registSuccess(
                            userNameEditText.getText().toString(),
                            userPhoneEditText.getText().toString(),
                            phoneRegEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            affirmPasswordEditText.getText().toString());
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    showShortToast("验证码已经发送");
                }
            } else {
                ((Throwable) data).printStackTrace();
                showShortToast("验证码错误");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("注册");


        registPersenter = new RegistPersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("注册中...");
        dialog.setCancelable(false);
        initSMS();

    }

    private void initSMS() {
        SMSSDK.initSDK(this, APPKEY, APPSECRET, false);
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }


    @Event(R.id.back_button)
    private void backEvent(View v) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.regist_new_button)
    private void registEvent(View v) {
        hideSystemKeyBoard(this, v);
        if (TextUtils.isEmpty(userNameEditText.getText().toString().trim())) {
            showShortToast("姓名不能为空！");
            return ;
        }

        if (TextUtils.isEmpty(userPhoneEditText.getText().toString().trim())) {
            showShortToast("手机号不能为空！");
            return ;
        }

        if (userPhoneEditText.getText().toString().trim().length() != 11) {
            showShortToast("手机号必须11位！");
            return ;
        }

        if (!RegUtils.isMobileNumber(userPhoneEditText.getText().toString().trim())) {
            showShortToast("必须是手机号！");
            return ;
        }
        SMSSDK.submitVerificationCode("86",
                userPhoneEditText.getText().toString(),
                phoneRegEditText.getText().toString());
    }

    @Event(R.id.send_phone_reg_button)
    private void sendRegEvent(View v) {
        hideSystemKeyBoard(this,v);
        if(TextUtils.isEmpty(userPhoneEditText.getText().toString())) {
            showShortToast("手机号不能为空");
            return;
        }
        MyCountTimer timeCount = new MyCountTimer(sendRegButton,0xff666666, 0xff666666);//传入了文字颜色值
        timeCount.start();
        SMSSDK.getVerificationCode("86", userPhoneEditText.getText().toString(), new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String country, String phone) {
                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        backPressed(this);
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
    public void showMsg(String msg) {
        showShortToast(msg);
    }


    @Override
    public void moveToLogin() {
        startOtherActivity(ACTION_LOGIN);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }


}
