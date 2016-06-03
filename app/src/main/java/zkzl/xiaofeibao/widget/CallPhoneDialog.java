package zkzl.xiaofeibao.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.iwhys.library.widget.BaseDialog;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2016/1/7.
 */
public class CallPhoneDialog extends BaseDialog {

    private String name;
    private String phoneNumber;
    private CallListener callListener;


    public CallPhoneDialog (final Context context, String name, final String phoneNumber,CallListener callListener) {
        super(context, R.layout.call_pop);
        this.callListener = callListener;
        this.name = name;
        this.phoneNumber =phoneNumber;
        init();
    }

    @Override
    protected int dialogAnimation() {
        return com.iwhys.library.R.style.AnimationBottomDialog;
    }

    @Override
    protected int dialogGravity() {
        return Gravity.BOTTOM;
    }


    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    private void init() {
        Button callNameButton = (Button)findViewById(R.id.call_name);
        callNameButton.setText(name);
        callNameButton.setOnClickListener(listener);
        Button callPhoneButton = (Button)findViewById(R.id.call_phone);
        callPhoneButton.setText(phoneNumber);
        callPhoneButton.setOnClickListener(listener);
        Button btn_cancel = (Button)findViewById(R.id.btn_call_cancle);
        btn_cancel.setOnClickListener(listener);
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
