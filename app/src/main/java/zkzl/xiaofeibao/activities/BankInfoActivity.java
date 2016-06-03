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
@ContentView(R.layout.activity_bank_info)
public class BankInfoActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleTextView;

    @ViewInject(R.id.other_button)
    private TextView deleteBankTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.other_button)
    private void addEvent(View v) {
        startOtherActivity(ACTION_BANKADD);
    }

    private void init() {
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        deleteBankTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("银行卡信息");
        deleteBankTextView.setText("解除");
    }

}
