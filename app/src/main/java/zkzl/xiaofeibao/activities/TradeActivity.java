package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.TradeAdapter;
import zkzl.xiaofeibao.bean.Trade;
import zkzl.xiaofeibao.widget.PinnedHeaderListView;

@ContentView(R.layout.activity_trade)
public class TradeActivity extends BaseActivity {

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.trade_log_pinned_header_list)
    private PinnedHeaderListView pinnedHeaderListView;

    @ViewInject(R.id.refresh)
    private MaterialRefreshLayout materialRefreshLayout;

    private TradeAdapter tradeAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    materialRefreshLayout.finishRefresh();

                    break;
                case 2:
                    materialRefreshLayout.finishRefreshLoadMore();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);
        titleButton.setText("交易记录");

        tradeAdapter = new TradeAdapter(this);
        tradeAdapter.setDatas(getDatas());
        pinnedHeaderListView.setAdapter(tradeAdapter);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(2);
                    }
                }).start();
            }
        });
        materialRefreshLayout.autoRefresh();


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

    private List<Trade> getDatas() {
        List<Trade> list = new ArrayList<Trade>();
        for (int i = 0; i < 2; i++) {
            Trade trade = new Trade();
            trade.id = i + "";
            trade.avatar = "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu1.jpg";
            trade.week = "周五";
            trade.time = "9:55";
            trade.trade = "盈利：1000";
            trade.who = "消费宝科技有限公司";
            list.add(trade);
        }

        return list;
    }

}
