package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.PayDialog;
import zkzl.xiaofeibao.widget.PayPwdDialog;
import zkzl.xiaofeibao.widget.WithdrawPwdInputDialog;

@ContentView(R.layout.activity_withdraw)
public class WithdrawActivity extends BaseActivity implements
        WithdrawPwdInputDialog.WithdrawListener{


    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.withdraw_money)
    private EditText moneyEditText;

    @ViewInject(R.id.withdraw_bank_name)
    private Button bankNameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("提现");

    }

    @Event(R.id.withdraw_next_step)
    private void nextEvent(View v) {
        if (TextUtils.isEmpty(moneyEditText.getText().toString())) {
            showShortToast("请输入金额！");
            return;
        }
        WithdrawPwdInputDialog dialog = new WithdrawPwdInputDialog(this,"提现:" + moneyEditText.getText().toString(),this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onTextChanged(String password) {

    }

    @Override
    public void onInputFinish(String password) {

    }




    @Event(R.id.withdraw_bank_name)
    private void selectBankEvent(View v) {
    startOtherActivity(ACTION_BANKLIST);
    }


    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }
}
