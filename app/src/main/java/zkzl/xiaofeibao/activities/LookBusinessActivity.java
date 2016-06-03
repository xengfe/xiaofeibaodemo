package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.iwhys.library.widget.DatePickerDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.ProfitAdapter;
import zkzl.xiaofeibao.bean.Bussness;
import zkzl.xiaofeibao.widget.ScrollListview;


@ContentView(R.layout.activity_look_business)
public class LookBusinessActivity extends BaseActivity  {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.date_label_2)
    private Button dateButton;

    @ViewInject(R.id.look_business_pull_list_view)
    private ScrollListview listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
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

    @Event(value = {R.id.date_label,R.id.date_label_2})
    private void datePickerEvent(View view) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int[] dateValue = new int[]{year, month, date};

        DatePickerDialog datePickerDialog =  new DatePickerDialog(this, dateValue[0], dateValue[1], dateValue[2], new DatePickerDialog.OnSelectListener() {
            @Override
            public void onSelect(int[] values, String displayName) {
                dateButton.setText(displayName);
            }
        });
        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
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
