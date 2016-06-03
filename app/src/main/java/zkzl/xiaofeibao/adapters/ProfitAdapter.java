package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.Bussness;

/**
 * Created by Admin on 2016/1/6.
 */
public class ProfitAdapter extends BaseAdapter {

    private List<Bussness> datas;
    private LayoutInflater inflater;

    public ProfitAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<Bussness>();
    }


    public void setDatas(List<Bussness> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }


    @Override
    public long getItemId(int position) {
        return Integer.parseInt(datas.get(position).id);
    }


    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bussness bussness = datas.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.prifit_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.monthTextView.setText(bussness.date);
        holder.numberTextView.setText(bussness.number);
        holder.weightTextView.setText(bussness.weight);
        holder.earnedTextView.setText(bussness.prifit);
        holder.lessTextView.setText(bussness.less);
        return convertView;
    }


    class ViewHolder {
        @ViewInject(R.id.month_tv)
        TextView monthTextView;
        @ViewInject(R.id.serir_tv)
        TextView numberTextView;
        @ViewInject(R.id.weight_tv)
        TextView weightTextView;
        @ViewInject(R.id.prifit_tv)
        TextView earnedTextView;
        @ViewInject(R.id.less_tv)
        TextView lessTextView;
    }
}
