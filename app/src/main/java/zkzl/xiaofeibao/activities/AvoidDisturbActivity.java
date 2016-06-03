package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;

@ContentView(R.layout.activity_avoid_disturb)
public class AvoidDisturbActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView saveButton;

    @ViewInject(R.id.switch_number)
    private CheckBox numberCB;

    @ViewInject(R.id.switch_profit)
    private CheckBox profitCB;

    private String hintNumber = "hintNumber";
    private String hintProfit = "hintProfit";
    private boolean hintNumberFlag = false;
    private boolean hintProfitFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.setText("保存");
        titleButton.setText("免打扰");

        initListener();
    }

    private void initListener() {
        numberCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hintNumberFlag = true;
                } else {
                    hintNumberFlag = false;
                }

            }
        });

        profitCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hintProfitFlag = true;
                } else {
                    hintProfitFlag =  false;
                }
            }
        });
    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }

    @Event(R.id.other_button)
    private void saveConfigClick(View view) {
        sharedPreferenceManager.saveBoolean(AvoidDisturbActivity.this, hintNumber, hintNumberFlag);
        sharedPreferenceManager.saveBoolean(AvoidDisturbActivity.this, hintProfit, hintProfitFlag);
        showShortToast("保存成功！");
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (sharedPreferenceManager.getBoolean(AvoidDisturbActivity.this, hintNumber)) {
            numberCB.setChecked(true);
        } else {
            numberCB.setChecked(false);
        }

        if (sharedPreferenceManager.getBoolean(AvoidDisturbActivity.this, hintProfit)) {
            profitCB.setChecked(true);
        } else {
            profitCB.setChecked(false);
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }


}
