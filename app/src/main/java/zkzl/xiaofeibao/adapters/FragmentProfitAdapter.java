package zkzl.xiaofeibao.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.activities.LookBusinessActivity;
import zkzl.xiaofeibao.activities.LookProfitActivity;
import zkzl.xiaofeibao.bean.Shop;

/**
 * Created by Admin on 2015/12/17.
 */
public class FragmentProfitAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Shop> shops;
    private final ImageLoader imageLoader;
    private Activity activity;

    public FragmentProfitAdapter(Activity activity) {
        this.activity = activity;
        shops = new ArrayList<Shop>();
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            convertView = mInflater.inflate(R.layout.profit_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.shopPic.setImageUrl(shop.image, imageLoader);
        holder.progressBar.setVisibility(View.GONE);
        holder.shopNameTV.setText(shop.name);
        holder.investTV.setText(shop.invest);
        holder.profitTV.setText(shop.progress);

        holder.lookProfitButotn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LookProfitActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        holder.lookBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LookBusinessActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            }
        });

        return convertView;
    }


   static  class ViewHolder {
        @ViewInject(R.id.profit_list_avatar)
        NetworkImageView shopPic;
        @ViewInject(R.id.profit_shop_name)
        TextView shopNameTV;
        @ViewInject(R.id.profit_status_IV)
        ImageView profitStatusIV;
        @ViewInject(R.id.profit_progressbar)
        ProgressBar doingProgressBar;
        @ViewInject(R.id.profit_money_tv)
        TextView profitTV;
        @ViewInject(R.id.profit_invest_tv)
        TextView investTV;
        @ViewInject(R.id.profit_pic_progressBar)
        ProgressBar progressBar;
        @ViewInject(R.id.look_profit_button)
        Button lookProfitButotn;
        @ViewInject(R.id.look_business_button)
        Button lookBusinessButton;
    }

//
//    @Event(R.id.look_over_profit_button)
//    private void lookProfitEvent(View view) {
//        Intent intent = new Intent(activity, LookProfitActivity.class);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
//    }
//
//    @Event(R.id.look_over_business_button)
//    private void lookBusinessEvent(View view) {
//        Intent intent = new Intent(activity, LookBusinessActivity.class);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
//    }
}
