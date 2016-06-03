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
import zkzl.xiaofeibao.persenter.ResetPhonePersenter;
import zkzl.xiaofeibao.utils.RegUtils;
import zkzl.xiaofeibao.view.IResetPhoneView;

@ContentView(R.layout.activity_submit_phone)
public class SubmitPhoneActivity extends BaseActivity implements IResetPhoneView {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.submit_phone_new_mobile_et)
    private EditText newPhoneEditText;

    @ViewInject(R.id.submit_phone_reg_et)
    private EditText regPhoneEditText;

    @ViewInject(R.id.submit_phone_send_phone_msg)
    private Button sendRegButton;

    @ViewInject(R.id.submit_phone_button)
    private Button submitButton;

    private ResetPhonePersenter phonePersenter;
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
                    phonePersenter.resetPhone(newPhoneEditText.getText().toString().trim(),
                            regPhoneEditText.getText().toString().trim());
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
        titleButton.setText("手机修改");

        phonePersenter = new ResetPhonePersenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("修改中...");
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

    @Event(R.id.submit_phone_button)
    private void submitButtonClick(View v) {
        hideSystemKeyBoard(this, v);

        if (TextUtils.isEmpty(newPhoneEditText.getText().toString().trim())) {
            showShortToast("手机号不能为空！");
            return;
        }

        if (newPhoneEditText.getText().toString().trim().length() != 11) {
            showShortToast("手机号必须11位！");
            return;
        }

        if (!RegUtils.isMobileNumber(newPhoneEditText.getText().toString().trim())) {
            showShortToast("必须是手机号！");
            return;
        }
        SMSSDK.submitVerificationCode("86",
                newPhoneEditText.getText().toString(),
                regPhoneEditText.getText().toString());
    }


    @Event(R.id.submit_phone_send_phone_msg)
    private void sendRegEvent(View v) {
        hideSystemKeyBoard(this, v);
        if (TextUtils.isEmpty(newPhoneEditText.getText().toString())) {
            showShortToast("手机号不能为空");
            return;
        }
        MyCountTimer timeCount = new MyCountTimer(sendRegButton, 0xff666666, 0xff666666);//传入了文字颜色值
        timeCount.start();
        SMSSDK.getVerificationCode("86", newPhoneEditText.getText().toString(), new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String country, String phone) {
                return false;
            }
        });
    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
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

    }

    @Override
    public void toNextActivity() {
        startOtherActivity(ACTION_SETTING);
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
