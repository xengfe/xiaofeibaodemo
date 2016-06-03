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
public class FragmentFailAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;

    public FragmentFailAdapter(Context context) {
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
            convertView = mInflater.inflate(R.layout.fail_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameTV.setText(shop.name);
        holder.targerTV.setText(shop.targetmoney);
        holder.investTV.setText(shop.invest);
        holder.progressTV.setText(shop.progress);

        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.fail_list_avatar)
        NetworkImageView shopPic;
        @ViewInject(R.id.fail_shop_name)
        TextView shopNameTV;
        @ViewInject(R.id.fail_status_IV)
        ImageView overStatusIV;
        @ViewInject(R.id.fail_targer_money_tv)
        TextView targerTV;
        @ViewInject(R.id.fail_money_tv)
        TextView progressTV;
        @ViewInject(R.id.fail_invest_tv)
        TextView investTV;
        @ViewInject(R.id.fail_pic_progressBar)
        ProgressBar progressBar;

        @ViewInject(R.id.fail_progressbar)
        ProgressBar doingProgressBar;
    }
}
