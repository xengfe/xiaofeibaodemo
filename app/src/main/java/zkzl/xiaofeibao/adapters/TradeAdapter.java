package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import zkzl.xiaofeibao.bean.Trade;
import zkzl.xiaofeibao.widget.CircleImageView;

/**
 * Created by zhcf-01 on 2015/12/11.
 */
public class TradeAdapter extends SectionedBaseAdapter {

    private LayoutInflater mInflater;
    private List<Trade> list;
    private String[] listHead = {"今天", "昨天"};
    private final ImageLoader imageLoader;

    public TradeAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = MyApplication.getInstance().getImageLoader();
        list = new ArrayList<Trade>();
    }

    public void setDatas(List<Trade> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int section, int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return Integer.parseInt(list.get(position).id);
    }

    @Override
    public int getSectionCount() {
        return listHead.length;
    }

    @Override
    public int getCountForSection(int section) {
        return 2;
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        Trade trade = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.pinned_child_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.avatar.setImageUrl(trade.avatar, imageLoader);
        holder.avatar.setDefaultImageResId(R.mipmap.default_user_avatar_setting);
        holder.weekDayView.setText(trade.week);
        holder.timeView.setText(trade.time);
        holder.tradeView.setText(trade.trade);
        holder.whoView.setText(trade.who);
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.pinned_header_list_item_title, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.monthView.setText(listHead[section]);
        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.pinned_child_list_item_avatar)
        CircleImageView avatar;
        @ViewInject(R.id.pinned_child_list_item_week_day)
        TextView weekDayView;
        @ViewInject(R.id.pinned_child_list_item_time)
        TextView timeView;
        @ViewInject(R.id.pinned_child_list_item_trade)
        TextView tradeView;
        @ViewInject(R.id.pinned_child_list_item_who)
        TextView whoView;
    }

    static class HeaderViewHolder {
        @ViewInject(R.id.pinned_header_list_item_title)
        TextView monthView;
    }
}
