package zkzl.xiaofeibao.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;


import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2016/1/14.
 */
public class LoadmoreListView extends ListView implements android.widget.AbsListView.OnScrollListener {
    //是否到达ListView底部
    boolean isLastRow = false;
    private Handler handler;
    private Button loadmoreButton;


    public LoadmoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadmoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadmoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        handler = new Handler();
        View bottomView = LayoutInflater.from(context).inflate(R.layout.list_view_bottom, null);
        loadmoreButton = (Button) findViewById(R.id.load_more_view);
        this.addFooterView(bottomView);
        loadmoreButton.setOnClickListener(listener);
    }


    private OnClickListener listener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadmoreButton.setText("添加银行卡");
                }
            }, 2000);
        }
    };


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断是否滚动到最后一行
        if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
            isLastRow=true;
        }
    }



    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    //当滚动到最后一行并且停止滚动时，执行加载
        if (isLastRow && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            //执行加载代码
            isLastRow = false;
        }
    }
}
