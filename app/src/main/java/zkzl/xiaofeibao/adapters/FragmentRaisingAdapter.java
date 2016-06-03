package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
 * Created by Admin on 2015/12/17.
 */
public class FragmentRaisingAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;

    public FragmentRaisingAdapter(Context context) {
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
    public Object getItem(int position) {
        return shops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.valueOf(shops.get(position).id);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = shops.get(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.raising_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameTV.setText(shop.name);
        holder.targerTV.setText(shop.targetmoney);
        holder.attenderTV.setText(shop.invest);
        holder.progressTV.setText(shop.progress);

        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.raising_list_avatar)
        NetworkImageView shopPic;
        @ViewInject(R.id.raising_pic_progressBar)
        ProgressBar progressBar;


        @ViewInject(R.id.raising_shop_name)
        TextView shopNameTV;
        @ViewInject(R.id.raising_status_IV)
        ImageView raisingStatusIV;
        @ViewInject(R.id.raising_targer_money_tv)
        TextView targerTV;
        @ViewInject(R.id.raising_money_tv)
        TextView progressTV;
        @ViewInject(R.id.raising_attender_tv)
        TextView attenderTV;
        @ViewInject(R.id.raising_progressbar)
        ProgressBar doingProgressBar;
    }
}
