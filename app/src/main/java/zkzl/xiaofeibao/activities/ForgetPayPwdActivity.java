package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.BankAdapter;
import zkzl.xiaofeibao.bean.Bank;
import zkzl.xiaofeibao.widget.PinnedHeaderListView;

@ContentView(R.layout.activity_forget_pay_pwd)
public class ForgetPayPwdActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.bank_list_view)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("忘记支付密码");


        BankAdapter adapter = new BankAdapter(this);
        adapter.setData(getData());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startOtherActivity(ACTION_REBINDBANK);
            }
        });
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

    public List<Bank> getData() {
        List<Bank> list = new ArrayList<Bank>();
        for (int i = 0; i < 5; i++) {
            Bank bank = new Bank();
            bank.name = "中国银行";
            bank.id = i + "";
            list.add(bank);
        }
        return list;
    }

}
