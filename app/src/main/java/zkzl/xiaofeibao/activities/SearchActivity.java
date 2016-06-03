package zkzl.xiaofeibao.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.widget.Tag;
import zkzl.xiaofeibao.widget.TagListView;
import zkzl.xiaofeibao.widget.TagView;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity implements TagListView.OnTagClickListener {

    @ViewInject(R.id.search_view)
    private SearchView searchView;

//    @ViewInject(R.id.title)
//    private TextView titleView;
//
//    @ViewInject(R.id.back_button)
//    private Button bankView;

    @ViewInject(R.id.tag_view)
    private TagListView tagListView;

    private final String[] titles = {"消费宝", "中控智联", "苹果", "西三旗",
            "百度", "腾讯", "人民大学", "微信", "最美奶茶"};

    private String currentSearchTip;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    showShortToast((String) msg.obj);
                    break;
            }
        }
    };
    private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        titleView.setText("搜索");
//        bankView.setVisibility(View.VISIBLE);
        screenManager.pushActivity(this);
        tagListView.setOnTagClickListener(this);
        tagListView.setTags(getDatas());
        searchView.setIconified(false);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() > 0) {
                    currentSearchTip = newText;
                    showSearchTip(newText);
                }
                return true;
            }
        });

    }


    private void showSearchTip(String newText) {
        schedule(new SearchTipThread(newText), 500);
    }

    class SearchTipThread implements Runnable {

        String newText;

        public SearchTipThread(String newText) {
            this.newText = newText;
        }

        public void run() {
            // keep only one thread to load current search tip, u can get data from network here
            if (newText != null && newText.equals(currentSearchTip)) {
                mHandler.sendMessage(mHandler.obtainMessage(1, newText + " search tip"));
            }
        }
    }

    public ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return scheduledExecutor.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }

    private List<Tag> getDatas() {
        List<Tag> list = new ArrayList<Tag>();
        for (int i = 0; i < titles.length; i++) {
            Tag tag = new Tag();
            tag.setId(i);
            tag.setChecked(true);
            tag.setTitle(titles[i]);
            list.add(tag);
        }
        return list;
    }

    @Override
    public void onTagClick(TagView tagView, Tag tag) {
        searchView.setQuery(tag.getTitle(), true);
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }

//    @Event(R.id.back_button)
//    private void backEvent(View v) {
//        screenManager.popActivity(screenManager.currentActivity());
//        this.finish();
//        finishActivityAnimation();
//    }
}
