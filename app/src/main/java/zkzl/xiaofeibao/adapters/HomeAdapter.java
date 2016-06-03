package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.Shop;

/**
 * Created by zhcf-01 on 2015/12/11.
 */
public class HomeAdapter extends SectionedBaseAdapter {

    private LayoutInflater mInflater;
    private List<List<Shop>> partentList;
    private List<Shop> chirdenList;
    private String[] listHead = {"众筹项目","预热项目"};

    public HomeAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        partentList = partent;
    }

    @Override
    public Object getItem(int section, int position) {
        // TODO Auto-generated method stub
        return chirdenList.get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        // TODO Auto-generated method stub
        return 0;
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
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.home_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //((TextView) layout.findViewById(R.id.textItem)).setText("Section " + section + " Item " + position);
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.home_header_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        holder.headerNameView.setText(listHead[section]);
        return convertView;
    }


    static class ViewHolder {
//        @ViewInject(R.id.home_list_avator)
//        NetworkImageView avtor;
//        @ViewInject(R.id.raise_shop_name)
//        TextView shopNameView;
//        @ViewInject(R.id.pre_targer_money_tv)
//        TextView moneView;
//        @ViewInject(R.id.pre_day_value)
//        TextView dateView;
//        @ViewInject(R.id.attender_value)
//        TextView watcherView;
    }

    static class HeaderViewHolder {
        @ViewInject(R.id.header_textItem)
        TextView headerNameView;
    }
}
