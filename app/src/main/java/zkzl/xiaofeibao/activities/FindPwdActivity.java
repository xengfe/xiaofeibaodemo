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
import zkzl.xiaofeibao.persenter.FindPwdPersenter;
import zkzl.xiaofeibao.utils.RegUtils;
import zkzl.xiaofeibao.view.IFindPwdView;

@ContentView(R.layout.activity_find_pwd)
public class FindPwdActivity extends BaseActivity implements IFindPwdView {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.find_user_phone)
    private EditText phoneEditText;

    @ViewInject(R.id.find_phone_reg)
    private EditText phoneRegEditText;

    @ViewInject(R.id.find_user_password)
    private EditText oldPwdEditText;

    @ViewInject(R.id.find_affirm_password)
    private EditText oldPwd2EditText;

    @ViewInject(R.id.send_phone_reg)
    private Button sendRegButton;

    private FindPwdPersenter pwdPersenter;
    private ProgressDialog dialog;


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    pwdPersenter.submit(
                            phoneEditText.getText().toString(),
                            phoneRegEditText.getText().toString(),
                            oldPwdEditText.getText().toString(),
                            oldPwd2EditText.getText().toString());
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
        titleButton.setText("找回密码");

        pwdPersenter = new FindPwdPersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("请稍等...");
        dialog.setCanceledOnTouchOutside(false);
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

    @Event(R.id.find_pwd_submit_button)
    private void submitEvent(View v) {
        hideSystemKeyBoard(this, v);


        if (TextUtils.isEmpty(phoneEditText.getText().toString().trim())) {
            showShortToast("手机号不能为空！");
            return ;
        }

        if (phoneEditText.getText().toString().trim().length() != 11) {
            showShortToast("手机号必须11位！");
            return ;
        }

        if (!RegUtils.isMobileNumber(phoneEditText.getText().toString().trim())) {
            showShortToast("必须是手机号！");
            return ;
        }
        SMSSDK.submitVerificationCode("86",
                phoneEditText.getText().toString(),
                phoneRegEditText.getText().toString());
    }

    @Event(R.id.send_phone_reg)
    private void sendRegEvent(View v) {
        hideSystemKeyBoard(this,v);
        if(TextUtils.isEmpty(phoneEditText.getText().toString())) {
            showShortToast("手机号不能为空");
            return;
        }
        MyCountTimer timeCount = new MyCountTimer(sendRegButton,0xff666666, 0xff666666);//传入了文字颜色值
        timeCount.start();
        SMSSDK.getVerificationCode("86", phoneEditText.getText().toString(), new OnSendMessageHandler() {
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
        dialog.hide();
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }

    @Override
    public void toLogin() {
        screenManager.popAllActivityExceptOne(FindPwdActivity.class);
        sharedPreferenceManager.saveBoolean(this, LOGIN_STATUS, false);
        startOtherActivity(ACTION_LOGIN);
        this.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
