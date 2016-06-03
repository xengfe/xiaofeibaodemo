package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.fragments.FragmentFail;
import zkzl.xiaofeibao.fragments.FragmentFoucsDoing;
import zkzl.xiaofeibao.fragments.FragmentFoucsPre;
import zkzl.xiaofeibao.fragments.FragmentOver;
import zkzl.xiaofeibao.fragments.FragmentProfit;
import zkzl.xiaofeibao.fragments.FragmentRaising;

@ContentView(R.layout.activity_focus_project)
public class FocusProjectActivity extends BaseActivity {
    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    private FragmentFoucsPre fragmentFoucsPre;
    private FragmentFoucsDoing fragmentFoucsDoing;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);

        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("关注的众筹");
        initView();
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


    private void initView() {
        updateRadioButtonStytle((RadioButton)((RadioGroup) findViewById(R.id.foucs_project_rg)).getChildAt(0));
        fragmentFoucsPre = new FragmentFoucsPre();
        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentFoucsPre).commit();
        ((RadioGroup) findViewById(R.id.foucs_project_rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foucs_pre_rb:
                        if (fragmentFoucsPre == null) {
                            fragmentFoucsPre = new FragmentFoucsPre();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentFoucsPre).commit();
                        break;
                    case R.id.foucs_doing_rb:
                        if (fragmentFoucsDoing == null) {
                            fragmentFoucsDoing = new FragmentFoucsDoing();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentFoucsDoing).commit();
                        break;

                    default:
                        break;
                }

                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    updateRadioButtonStytle((RadioButton) group.getChildAt(i));
                }
            }
        });

    }

    private void updateRadioButtonStytle(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setTextColor(getResources().getColor(R.color.title_blue));
        } else {
            radioButton.setTextColor(getResources().getColor(R.color.light_black));
        }
    }
}
