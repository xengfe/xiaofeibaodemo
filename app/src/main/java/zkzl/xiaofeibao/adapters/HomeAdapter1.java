package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.Shop;

/**
 * Created by Admin on 2015/12/23.
 */
public class HomeAdapter1 extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Shop> lists;
    private final ImageLoader imageLoader;


    public HomeAdapter1(Context context) {
        lists = new ArrayList<Shop>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = MyApplication.getInstance().getImageLoader();
    }


    public void setDatas(List<Shop> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }


    @Override
    public long getItemId(int position) {
        return Integer.valueOf(lists.get(position).id);
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = lists.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.home_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.avatar.setImageUrl(shop.image, imageLoader);
        holder.picProgressBar.setVisibility(View.GONE);
        holder.shopNameView.setText(shop.name);
        holder.targerView.setText(shop.targetmoney);
        holder.attendView.setText(shop.attender);
        holder.doneView.setText(shop.progress);
        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.home_list1_avatar)
        NetworkImageView avatar;
        @ViewInject(R.id.raise_shop_name)
        TextView shopNameView;
        @ViewInject(R.id.targer_money_tv)
        TextView targerView;
        @ViewInject(R.id.raise_money_tv)
        TextView doneView;
        @ViewInject(R.id.attender_tv)
        TextView attendView;
        @ViewInject(R.id.home_raise_progressbar)
        ProgressBar progressBar;
        @ViewInject(R.id.home_pic_progressBar)
        ProgressBar picProgressBar;

    }
}
