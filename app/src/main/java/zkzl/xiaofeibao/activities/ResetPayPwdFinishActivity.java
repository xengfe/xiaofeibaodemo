package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
@ContentView(R.layout.activity_reset_pay_pwd_finish)
public class ResetPayPwdFinishActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.bank_pwd_affirm)
    private GridPasswordView affrimPwd;

    @ViewInject(R.id.bank_pwd)
    private GridPasswordView pwd;

    @ViewInject(R.id.bank_mobile_reg)
    private EditText regEditText;

    @ViewInject(R.id.bank_mobile)
    private EditText mobileEditText;

    @ViewInject(R.id.bank_name)
    private TextView bankNameTextView;

    @ViewInject(R.id.bank_type)
    private TextView bankTypeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("填写银行卡信息");

    }

    @Event(R.id.bank_pwd_commit)
    private void submitEvent(View v) {

    }

    @Event(R.id.send_bank_mobile_btn)
    private void sendRegEvent(View v) {

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
