package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.Wallet;

/**
 * Created by Admin on 2016/1/14.
 */
public class WalletListAdapter extends BaseAdapter {

    private List<Wallet> list;
    private LayoutInflater inflater;

    public WalletListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list = new ArrayList<Wallet>();
    }


    public void setDatas(List<Wallet> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Wallet wallet = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.wallet_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.walletListItemNameView.setText(wallet.name);
        holder.walletListItemInfoView.setText(wallet.info);
        return convertView;
    }


    class ViewHolder {
        @ViewInject(R.id.wallet_list_item_name_btn)
        TextView walletListItemNameView;
        @ViewInject(R.id.wallet_list_item_info_btn)
        TextView walletListItemInfoView;
    }


}
