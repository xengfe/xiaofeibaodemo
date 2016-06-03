package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.BankListAdapter;
import zkzl.xiaofeibao.bean.Bank;
import zkzl.xiaofeibao.utils.DateUtils;

@ContentView(R.layout.activity_bank_list)
public class BankListActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleTextView;

    @ViewInject(R.id.other_button)
    private TextView addBankTextView;

    @ViewInject(R.id.bank_list_view)
    private PullToRefreshListView refreshListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        BankListAdapter adapter = new BankListAdapter(this);
        adapter.setData(getData());
        refreshListView.setAdapter(adapter);
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

    @Override
    public void onBackPressed() {
        backPressed(this);
    }


    private void init() {
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        addBankTextView.setVisibility(View.VISIBLE);
        titleTextView.setText("银行卡");
        addBankTextView.setText("添加");
        refreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        refreshListView.setScrollingWhileRefreshingEnabled(true);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(DateUtils.formatDateTime(new Date()));
                refreshListView.postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        refreshListView.onRefreshComplete();
                    }
                }, 2000);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(DateUtils.formatDateTime(new Date()));
                refreshListView.postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        refreshListView.onRefreshComplete();
                    }
                }, 2000);

            }
        });

        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startOtherActivity(ACTION_BANKINFO);
            }
        });
    }


    private List<Bank> getData() {
        List<Bank> list = new ArrayList<Bank>();
        for (int i = 0; i < 3 ; i++) {
            Bank bank = new Bank();
            bank.id = i+ "";
            bank.name = "招商银行";
            bank.number = "622848238164741668";
            bank.type = "储蓄卡";
            list.add(bank);
        }

        return list;
    }

}
