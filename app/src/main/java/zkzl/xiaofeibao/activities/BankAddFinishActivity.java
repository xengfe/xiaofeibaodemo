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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
@ContentView(R.layout.activity_bank_add_finish)
public class BankAddFinishActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleTextView;

    @ViewInject(R.id.bank_add_finish_name)
    private TextView bankNameTextView;

    @ViewInject(R.id.bank_add_finish_type)
    private TextView bankTypeTextView;

    @ViewInject(R.id.bank_add_finish_mobile)
    private EditText mobileEditText;

    @ViewInject(R.id.bank_add_finish_reg)
    private EditText regEditText;

    @ViewInject(R.id.bank_add_finish_send_reg)
    private Button sendRegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleTextView.setText("添加银行卡");
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

    @Event(R.id.bank_list_add_commit)
    private void submitEvent(View v) {

    }
}
