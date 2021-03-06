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

@ContentView(R.layout.activity_re_bind_bank)
public class ReBindBankActivity extends BaseActivity {
    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("重绑银行卡");
    }


    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }


    @Event(R.id.rebind_next_btn)
    private void nextStepEvent(View v) {
        startOtherActivity(ACTION_FINSHPAYPWD);
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

}
