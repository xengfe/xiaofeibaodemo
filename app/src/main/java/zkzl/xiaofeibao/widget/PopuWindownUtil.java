package zkzl.xiaofeibao.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import zkzl.xiaofeibao.R;

/**
 * Created by zhcf-01 on 2015/12/14.
 */
public class PopuWindownUtil {

    private final LayoutInflater layoutInflater;
    private List<String> lists;
    private PopuWindownSelectListener selectListener;
    private PopupWindow popupWindow = null;
    private View partentView = null;
    private Activity activity;
    private boolean isSelected = false;//判断条件是否选中


    public PopuWindownUtil(Activity context, List<String> list,
                           PopuWindownSelectListener listener,
                           View partent, int popuWidth, int popuHeight) {
        activity = context;
        this.lists = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.selectListener = listener;
        this.partentView = partent;
        init(popuWidth, popuHeight);
    }

    private void init(int popuWidth, int popuHeight) {
        final View view = layoutInflater.inflate(R.layout.popu_layout, null);
        ListView listview = (ListView) view.findViewById(R.id.popu_list_view);


        setViewTextColor(partentView);
        PopuAdapter popuAdapter = new PopuAdapter();
        listview.setAdapter(popuAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isSelected = true;
                selectListener.select(lists.get(position));
                setViewValue(partentView, lists.get(position));
                dismiss();

            }
        });

        initPopu(view, popuWidth, popuHeight);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelected) {
                    defaultTextColor(partentView);
                }
                dismiss();
            }
        });
    }

    private void setViewTextColor (View partent) {
        if (partent instanceof Button) {
            ((Button) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        } else if (partent instanceof TextView) {
            ((TextView) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        } else if (partent instanceof EditText) {
            ((EditText) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        }

    }


    private void defaultTextColor (View partent) {
        if (partent instanceof Button) {
            ((Button) partent).setTextColor(activity.getResources().getColor(R.color.light_black));
        } else if (partent instanceof TextView) {
            ((TextView) partent).setTextColor(activity.getResources().getColor(R.color.light_black));
        } else if (partent instanceof EditText) {
            ((EditText) partent).setTextColor(activity.getResources().getColor(R.color.light_black));
        }
    }

    private void setViewValue(View partent, String value) {

        if (partent instanceof Button) {
            ((Button) partent).setText(value);
            ((Button) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        } else if (partent instanceof TextView) {
            ((TextView) partent).setText(value);
            ((TextView) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        } else if (partent instanceof EditText) {
            ((EditText) partent).setText(value);
            ((EditText) partent).setTextColor(activity.getResources().getColor(R.color.title_blue));
        }
    }

    private void initPopu(View convertView, int popuWidth, int popuHeight) {
        popupWindow = new PopupWindow(convertView, popuWidth, popuHeight);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void show() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(partentView);
        }
    }


    public void dismiss() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


    class PopuAdapter extends BaseAdapter {


        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }


        @Override
        public int getCount() {
            return lists.size();
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }


        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;


            if (convertView == null) {
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.popu_list_item, null);
                x.view().inject(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.popuListItemlable.setText(lists.get(position));
            return convertView;
        }


        class ViewHolder {

            @ViewInject(R.id.popu_list_item_tv)
            TextView popuListItemlable;
        }
    }


    public interface PopuWindownSelectListener {
        void select(String value);
    }

}
