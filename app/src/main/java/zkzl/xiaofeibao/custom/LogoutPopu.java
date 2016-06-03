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
public class LogoutPopu extends PopupWindow{

    private LogoutListener logoutListener;

    public LogoutPopu(Context context, String messgae, LogoutListener logoutListener) {
        this.logoutListener = logoutListener;
        View view = LayoutInflater.from(context).inflate(R.layout.logout_pop, null);
        ((TextView)view.findViewById(R.id.logout_context)).setText(messgae);
        Button cancleButton = (Button) view.findViewById(R.id.logout_cancle_button);
        cancleButton.setOnClickListener(listener);
        Button okButton = (Button) view.findViewById(R.id.logout_ok_button);
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
            logoutListener.dispatchEvent(v);
            dismiss();
        }
    };

    public interface LogoutListener {
        void dispatchEvent(View view);
    }
}
