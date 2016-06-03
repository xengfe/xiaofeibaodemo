package zkzl.xiaofeibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import zkzl.xiaofeibao.MyApplication;
import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.ChatGroup;

/**
 * Created by Admin on 2016/1/13.
 */
public class ChatGroupAdapter extends BaseAdapter{

    private List<ChatGroup> list;
    private LayoutInflater inflater;
    private final ImageLoader imageLoader;
    public ChatGroupAdapter(Context context) {
        list = new ArrayList<ChatGroup>();
        inflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getInstance().getImageLoader();
    }

    public void setData(List<ChatGroup> list) {
        this.list = list;
        notifyDataSetChanged();
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
        ChatGroup chatGroup = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.chat_group_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.chatGroupAvatarView.setImageUrl(chatGroup.avatar, imageLoader);
        holder.chatGroupNameTV.setText(chatGroup.name);
        holder.chatGroupMessageTV.setText(chatGroup.message.fromContent);
        holder.chatGroupMessageTimeTV.setText(chatGroup.message.recevietime);
        return convertView;
    }


    private static class ViewHolder{

        @ViewInject(R.id.chat_group_avatar)
        NetworkImageView chatGroupAvatarView;

        @ViewInject(R.id.chat_group_name)
        TextView chatGroupNameTV;

        @ViewInject(R.id.chat_group_message)
        TextView chatGroupMessageTV;

        @ViewInject(R.id.chat_group_message_time)
        TextView chatGroupMessageTimeTV;


    }
}
