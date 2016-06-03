package zkzl.xiaofeibao.fragments;

import android.os.Bundle;

import android.view.View;


import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.umeng.update.UmengUpdateAgent;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zkzl.xiaofeibao.R;

import zkzl.xiaofeibao.adapters.HomeAdapter1;
import zkzl.xiaofeibao.adapters.HomeAdapter2;
import zkzl.xiaofeibao.widget.Carousel;

import zkzl.xiaofeibao.bean.Shop;
import zkzl.xiaofeibao.utils.DateUtils;

@ContentView(R.layout.fragment_fragment_home)
public class FragmentHome extends FragmentBase {

    @ViewInject(R.id.home_title)
    private TextView titleView;

    @ViewInject(R.id.crs)
    private Carousel mCarousel;
    private List<Shop> data;

    @ViewInject(R.id.home_pull_scrollview)
    private PullToRefreshScrollView pullRefreshScrollView;

    @ViewInject(R.id.home_list_view)
    private zkzl.xiaofeibao.widget.ScrollListview listView1;
    @ViewInject(R.id.pre_home_list_view)
    private zkzl.xiaofeibao.widget.ScrollListview listView2;

    private final static String[] urls = new String[]{
            "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu1.jpg",
            "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu2.jpg",
            "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu3.jpg"
    };


    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView.setText(R.string.app_name);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(getActivity());


        initControl();
        //开启轮播控件
        data = new ArrayList<Shop>();

        for (int i = 0; i < urls.length; i++) {
            Shop shop = new Shop();
            shop.image = (urls[i]);
            shop.name = ("粥立方");
            shop.id = i+"";
            data.add(shop);
        }
        mCarousel.startup(5,data);


        HomeAdapter1 raiseAdapter = new HomeAdapter1(getActivity());
        raiseAdapter.setDatas(getRaiseData());
        listView1.setAdapter(raiseAdapter);


        HomeAdapter2 preraiseAdaptr = new HomeAdapter2(getActivity());
        preraiseAdaptr.setDatas(getPreRaiseData());
        listView2.setAdapter(preraiseAdaptr);

        initPullReflushView();
        initListViewOnItemClick();
    }

    private void initPullReflushView() {
        pullRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullRefreshScrollView.setScrollingWhileRefreshingEnabled(true);
        pullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pullRefreshScrollView.getLoadingLayoutProxy().setLastUpdatedLabel(DateUtils.formatDateTime(new Date()));
                pullRefreshScrollView.postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        pullRefreshScrollView.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pullRefreshScrollView.getLoadingLayoutProxy().setLastUpdatedLabel(DateUtils.formatDateTime(new Date()));
                pullRefreshScrollView.postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        pullRefreshScrollView.onRefreshComplete();
                    }
                }, 2000);
            }
        });
    }


    private void initListViewOnItemClick() {
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startOtherActivity(ACTION_RAISE_DETAIL);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startOtherActivity(ACTION_PRE_RAISE_DETAIL);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCarousel.shutdown();
    }

    private void initControl() {

        mCarousel.setCallback(new Carousel.ClickCallback() {
            @Override
            public void perform(int id, int position) {
                startOtherActivity(ACTION_RAISE_DETAIL);
            }
        });

    }


    private List<Shop>  getRaiseData() {
        List<Shop> list = new ArrayList<Shop>();
        for (int i = 0; i <2 ; i++) {
            Shop shop = new Shop();
            shop.id = ""+i;
            shop.image = "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu3.jpg";
            shop.name = "北京烤鸭";
            shop.targetmoney = "600万";
            shop.progress = "300万";
            shop.attender = "5000人";
            list.add(shop);
        }
        return list;
    }


    private List<Shop>  getPreRaiseData() {
        List<Shop> list = new ArrayList<Shop>();
        for (int i = 0; i <2 ; i++) {
            Shop shop = new Shop();
            shop.id = ""+i;
            shop.image = "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu3.jpg";
            shop.name = "北京大酒店";
            shop.targetmoney = "2000万";
            shop.lefttime = "50天";
            shop.attender = "1000人";
            list.add(shop);
        }
        return list;
    }

    @Event(R.id.search_view)
    private void searchEvent(View v) {
        startOtherActivity(ACTION_SEARCH);
    }
}
