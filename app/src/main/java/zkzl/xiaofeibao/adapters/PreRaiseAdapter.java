package zkzl.xiaofeibao.adapters;

import android.widget.BaseAdapter;

import zkzl.xiaofeibao.bean.Shop;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;

/**
 * Created by zhcf-01 on 2015/12/16.
 */
public class PreRaiseAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;


    public PreRaiseAdapter(Context context) {
        shops = new ArrayList<Shop>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = MyApplication.getInstance().getImageLoader();

    }

    public void setData(ArrayList<Shop> shops) {
        this.shops = shops;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public long getItemId(int position) {

        return Integer.valueOf(shops.get(position).id);
    }

    @Override
    public Object getItem(int position) {
        return shops.get(position);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = shops.get(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pre_raise_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameView.setText(shop.name);
        holder.moneyView.setText(shop.targetmoney);
        holder.leftDayView.setText(shop.lefttime);
        holder.watcherView.setText(shop.attender);
        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.pre_raise_picture_view)
        NetworkImageView shopPic;
        @ViewInject(R.id.pre_raise_shop_name)
        TextView shopNameView;
        @ViewInject(R.id.pre_targer_money_tv)
        TextView moneyView;
        @ViewInject(R.id.pre_day_value)
        TextView leftDayView;
        @ViewInject(R.id.pre_watcher_tv)
        TextView watcherView;
        @ViewInject(R.id.pre_img_pb)
        ProgressBar progressBar;

    }
}
