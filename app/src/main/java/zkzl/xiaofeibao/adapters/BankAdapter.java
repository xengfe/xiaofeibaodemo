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
import zkzl.xiaofeibao.bean.Bank;

/**
 * Created by Admin on 2016/1/18.
 */
public class BankAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Bank> list;

    public BankAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<Bank>();
    }


    public void setData(List<Bank> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public void append(Bank bank) {
        list.add(bank);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public long getItemId(int position) {
        return Integer.parseInt(list.get(position).id);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Bank bank = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bank_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(bank.name);
        return convertView;
    }


    static class ViewHolder {
        @ViewInject(R.id.bank_list_view_item_name)
        TextView textView;
    }
}
