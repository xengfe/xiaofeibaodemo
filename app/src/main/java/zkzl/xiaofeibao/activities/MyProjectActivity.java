package zkzl.xiaofeibao.activities;

import android.os.Bundle;
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
import zkzl.xiaofeibao.fragments.FragmentOver;
import zkzl.xiaofeibao.fragments.FragmentProfit;
import zkzl.xiaofeibao.fragments.FragmentRaising;

@ContentView(R.layout.activity_my_project)
public class MyProjectActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    private FragmentRaising fragmentRaising;
    private FragmentProfit  fragmentProfit;
    private FragmentFail  fragmentFail;
    private FragmentOver fragmentOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);

        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("加入的众筹");

        initView();
    }

    private void initView() {
        updateRadioButtonStytle((RadioButton)((RadioGroup) findViewById(R.id.my_project_rg)).getChildAt(0));
        fragmentRaising = new FragmentRaising();
        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentRaising).commit();
        ((RadioGroup) findViewById(R.id.my_project_rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.my_project_doing:
                        if (fragmentRaising == null) {
                            fragmentRaising = new FragmentRaising();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentRaising).commit();
                        break;
                    case R.id.my_project_fail:
                        if (fragmentFail == null) {
                            fragmentFail = new FragmentFail();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentFail).commit();
                        break;
                    case R.id.my_project_good:
                        if (fragmentProfit == null) {
                            fragmentProfit = new FragmentProfit();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentProfit).commit();
                        break;
                    case R.id.my_project_over:
                        if (fragmentOver == null) {
                            fragmentOver = new FragmentOver();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.tab_content, fragmentOver).commit();
                        break;
                    default:
                        break;
                }

              int count = group.getChildCount();
                for (int i = 0;i < count;i++) {
                    updateRadioButtonStytle((RadioButton)group.getChildAt(i));
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
