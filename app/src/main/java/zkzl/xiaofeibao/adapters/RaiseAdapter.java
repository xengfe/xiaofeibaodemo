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


import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.Shop;

/**
 * Created by zhcf-01 on 2015/12/11.
 */
public class RaiseAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;


    public RaiseAdapter(Context context) {
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
            convertView = mInflater.inflate(R.layout.raise_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameView.setText(shop.name);
        holder.targerView.setText(shop.targetmoney);
        holder.attendView.setText(shop.attender);
        holder.doneView.setText(shop.progress);
        return convertView;
    }

    static class ViewHolder {
        @ViewInject(R.id.raise_picture_view)
        NetworkImageView shopPic;
        @ViewInject(R.id.raise_shop_name)
        TextView shopNameView;
        @ViewInject(R.id.targer_money_tv)
        TextView targerView;
        @ViewInject(R.id.attender_value)
        TextView attendView;
        @ViewInject(R.id.day_value)
        TextView doneView;
        @ViewInject(R.id.img_pb)
        ProgressBar progressBar;

    }


}
