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
 * Created by Admin on 2015/12/17.
 */
public class IndicatorFragmentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;

    public IndicatorFragmentAdapter(Context context) {
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
        return  shops.size();
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
            convertView = mInflater.inflate(R.layout.my_project_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setAdjustViewBounds(true);
        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameView.setText(shop.name);
        holder.targerView.setText(shop.targetmoney);
        holder.vestView.setText(shop.invest);
        holder.progressView.setText(shop.progress);

        return convertView;
    }


   static  class ViewHolder {
        @ViewInject(R.id.my_raise_picture_view)
        NetworkImageView shopPic;
        @ViewInject(R.id.my_raise_shop_name)
        TextView shopNameView;
        @ViewInject(R.id.my_targer_money_tv)
        TextView targerView;
        @ViewInject(R.id.my_vested_value)
        TextView progressView;
        @ViewInject(R.id.my_attender_value)
        TextView vestView;
        @ViewInject(R.id.my_img_pb)
        ProgressBar progressBar;
    }
}
