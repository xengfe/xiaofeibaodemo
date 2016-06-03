package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.ProfitAdapter;
import zkzl.xiaofeibao.bean.Bussness;
import zkzl.xiaofeibao.widget.ScrollListview;

@ContentView(R.layout.activity_look_profit)
public class LookProfitActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.other_button)
    private TextView shareButton;
    @ViewInject(R.id.look_profit_pull_list_view)
    private ScrollListview listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setText("分享");
        titleButton.setText("查看收益");

        ProfitAdapter adapter = new ProfitAdapter(this);
        adapter.setDatas(getData());
        listView.setAdapter(adapter);

    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }


    @Event(R.id.other_button)
    private void shareClick(View view) {

    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }


    private List<Bussness> getData() {
        List<Bussness> list = new ArrayList<Bussness>();
        for (int i = 0 ;i < 20;i ++) {
            Bussness bussness = new Bussness();
            bussness.id = ""+ i;
            bussness.date = "2016/01";
            bussness.number = "20160106" + i;
            bussness.weight = "30%";
            bussness.prifit = "5000.000";
            bussness.less = "1000";
            list.add(bussness);
        }
        return list;
    }

}
