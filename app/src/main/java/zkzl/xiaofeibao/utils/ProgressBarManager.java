package zkzl.xiaofeibao.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import zkzl.xiaofeibao.R;

/**
 * Created by zhcf-01 on 2015/12/3.
 */
public class ProgressBarManager {

    public static ProgressBarManager instance = null;
    private  ProgressBar progressBar = null;


    private ProgressBarManager() {
    }

    public static ProgressBarManager getProgressBarManager() {
        if (instance == null) {
            synchronized (ProgressBarManager.class) {
                if (instance == null) {
                    instance = new ProgressBarManager();
                }
            }
        }
        return instance;
    }

    public  void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_progressbar, null);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    public  void showProgressBar() {
        if (progressBar !=null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    public  void hideProgressBar() {
        if (progressBar !=null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
