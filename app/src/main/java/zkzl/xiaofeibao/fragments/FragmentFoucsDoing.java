package zkzl.xiaofeibao.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.adapters.RaiseAdapter;
import zkzl.xiaofeibao.bean.Shop;
import zkzl.xiaofeibao.utils.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_fragment_foucs_doing)
public class FragmentFoucsDoing extends FragmentBase {
    @ViewInject(R.id.foucs_doing_pull_list_view)
    private PullToRefreshListView refreshListView;
    private RaiseAdapter raiseAdapter;
    public FragmentFoucsDoing() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPullToReflushListView();
        raiseAdapter = new RaiseAdapter(getActivity());
        raiseAdapter.setData(getData());
        refreshListView.setAdapter(raiseAdapter);
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
        Shop shop = new Shop();
        shop.id = "000001";
        shop.name = "沃尔玛";
        shop.progress = "800万";
        shop.attender = "500人";
        shop.targetmoney = "1000万";
        shop.image = "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu1.jpg";
        for (int i = 0; i < 15; i++) {
            list.add(shop);
        }
        return list;
    }
}
