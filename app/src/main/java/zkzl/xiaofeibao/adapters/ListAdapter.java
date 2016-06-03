package zkzl.xiaofeibao.adapters;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.toolbox.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.utils.Const;

/**
 * Created by zhcf-01 on 2015/12/7.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<String> list = null;
    private ImageLoader imageLoader = null;
    private Cache cache = null;

    public ListAdapter() {
        list = new ArrayList<String>();
        imageLoader = MyApplication.getInstance().getImageLoader();
        cache = MyApplication.getInstance().getRequestQueue().getCache();
    }

    public void setDatas(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void appendDatas(String value) {
        list.add(0, value);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView = null;
        if (convertView == null) {

        } else {

        }

       Cache.Entry entry = cache.get(Const.URL_IMAGE);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            // Cached response doesn't exists. Make network call here
            imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(holdView.imageView, R.drawable.side_nav_bar, R.drawable.side_nav_bar));
            MyApplication.getInstance().getRequestQueue().getCache().invalidate(Const.URL_IMAGE, true);
        }

        return null;

    }


    static class HoldView {
        public ImageView imageView;
    }
}
