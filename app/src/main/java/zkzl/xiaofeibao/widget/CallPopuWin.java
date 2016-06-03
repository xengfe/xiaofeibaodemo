package zkzl.xiaofeibao.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2015/12/25.
 */
public class CallPopuWin extends PopupWindow {




    private String phoneNumber;
    private CallListener callListener;

    public CallPopuWin(final Context context, String name, final String phoneNumber,CallListener callListener) {


        this.phoneNumber = phoneNumber;
        this.callListener = callListener;
        View view = LayoutInflater.from(context).inflate(R.layout.call_pop, null);
        Button callNameButton = (Button) view.findViewById(R.id.call_name);
        callNameButton.setText(name);
        callNameButton.setOnClickListener(listener);
        Button callPhoneButton = (Button) view.findViewById(R.id.call_phone);
        callPhoneButton.setText(phoneNumber);
        callPhoneButton.setOnClickListener(listener);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_call_cancle);
        btn_cancel.setOnClickListener(listener);
        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.share_popu_anim);


    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            callListener.call(phoneNumber);
            dismiss();
        }
    };


    public interface CallListener {
        void call(String phoneNumber);
    }
}
