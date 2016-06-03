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
 * Created by Admin on 2016/1/19.
 */
public class PayResultDialog extends BaseDialog {

    private PayListener listener;


    public PayResultDialog(Context context, String msg, PayListener listener) {
        super(context, R.layout.pay_result_dialog);
        this.listener = listener;
        init(msg);
    }

    @Override
    protected int dialogAnimation() {
        return com.iwhys.library.R.style.AnimationBottomDialog;
    }

    @Override
    protected int dialogGravity() {
        return Gravity.CENTER;
    }


    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }


    private void init(String msg) {
        Button payResultMsg = (Button) findViewById(R.id.pay_result_msg);
        payResultMsg.setText(msg);
        Button payResultTry = (Button) findViewById(R.id.pay_result_try);
        Button payResultForget = (Button) findViewById(R.id.pay_result_forget);

        payResultTry.setOnClickListener(clickListener);
        payResultForget.setOnClickListener(clickListener);

    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            listener.result(v);
        }
    };

    public interface PayListener {

        void result(View v);

    }
}
