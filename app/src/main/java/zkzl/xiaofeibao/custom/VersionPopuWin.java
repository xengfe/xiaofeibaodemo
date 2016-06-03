package zkzl.xiaofeibao.custom;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2015/12/28.
 */
public class VersionPopuWin extends PopupWindow{

    private VersionListener versionListener;

    public VersionPopuWin (Context context,String messgae,VersionListener versionListener) {
        this.versionListener = versionListener;
        View view = LayoutInflater.from(context).inflate(R.layout.version_pop, null);
        ((TextView)view.findViewById(R.id.dialog_context)).setText(messgae);
        Button okButton = (Button) view.findViewById(R.id.alert_ok_button);
        okButton.setOnClickListener(listener);
        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.share_popu_anim);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            versionListener.dispatchEvent(v);
            dismiss();
        }
    };

    public interface VersionListener {
        void dispatchEvent(View view);
    }
}
