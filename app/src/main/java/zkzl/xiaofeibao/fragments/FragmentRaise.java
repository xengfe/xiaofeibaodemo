package zkzl.xiaofeibao.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import zkzl.xiaofeibao.R;

import zkzl.xiaofeibao.adapters.RaiseAdapter;
import zkzl.xiaofeibao.widget.PopuWindownUtil;
import zkzl.xiaofeibao.bean.Shop;
import zkzl.xiaofeibao.utils.DateUtils;


@ContentView(R.layout.fragment_fragment_raise)
public class FragmentRaise extends FragmentBase implements PopuWindownUtil.PopuWindownSelectListener {

    @ViewInject(R.id.title)
    private TextView titleView;

    @ViewInject(R.id.kind_image_button)
    private Button kingButton;
    @ViewInject(R.id.sort_image_button)
    private Button sortButton;
    @ViewInject(R.id.pull_list_view)
    private PullToRefreshListView refreshListView;

    private RaiseAdapter raiseAdapter;


    public FragmentRaise() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView.setText(R.string.app_name);
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

    @Event(R.id.kind_image_button)
    private void selectKindClick(View view) {
        initPopu(kingButton);

    }

    @Event(R.id.sort_image_button)
    private void selectSortClick(View view) {
        initPopu(sortButton);
    }

    private void initPopu(Button button) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            list.add("酒店");
            list.add("超市");
        }
        PopuWindownUtil popuWindownUtil = new PopuWindownUtil(
                getActivity(), list, this, button,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popuWindownUtil.show();
    }

    @Override
    public void select(String value) {

        Toast.makeText(getActivity(), value, Toast.LENGTH_LONG).show();
    }
}
