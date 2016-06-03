package zkzl.xiaofeibao.fragments;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.FragmentRaisingAdapter;
import zkzl.xiaofeibao.bean.Shop;
import zkzl.xiaofeibao.utils.DateUtils;

@ContentView(R.layout.fragment_raising_layout)
public class FragmentRaising extends FragmentBase {

    @ViewInject(R.id.indicator_pull_list_view)
    private PullToRefreshListView refreshListView;

    private FragmentRaisingAdapter adapter;


    public FragmentRaising() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPullToReflushListView();
        adapter = new FragmentRaisingAdapter(getActivity());
        adapter.setData(getData());
        refreshListView.setAdapter(adapter);
    }


    private void initPullToReflushListView() {
//        refreshListView.setRefreshing(true);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
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
                startOtherActivity(ACTION_RAISE_DETAIL);
            }
        });
    }


    private ArrayList<Shop> getData() {
        ArrayList<Shop> list = new ArrayList<Shop>();
        for(int i = 0;i<10;i++) {
            Shop shop = new Shop();
            shop.id ="" + i;
            shop.name = "北京烤鸭";
            shop.targetmoney = "200万";
            shop.image = "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu3.jpg";
            shop.progress = "100万";
            shop.invest = "10万";
            list.add(shop);
        }
        return list;
    }



}
