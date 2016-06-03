package zkzl.xiaofeibao.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iwhys.library.widget.BaseDialog;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2016/1/7.
 */
public class PayDialog extends BaseDialog {

    private PayTypeListener payTypeListener;

    public PayDialog (Context context,PayTypeListener payTypeListener) {
        super(context, R.layout.pay_type_pop);
        this.payTypeListener = payTypeListener;
        init();
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

    private void init() {
        ((ImageView)findViewById(R.id.pay_type_back)).setOnClickListener(listener);
        ((Button)findViewById(R.id.ali_pay_button)).setOnClickListener(listener);
        ((Button)findViewById(R.id.wei_chat_pay_button)).setOnClickListener(listener);
        ((Button)findViewById(R.id.bank_pay_button)).setOnClickListener(listener);
        ((Button)findViewById(R.id.xfb_pay_button)).setOnClickListener(listener);
        ((Button)findViewById(R.id.xfb_pay_button_2)).setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            payTypeListener.typePay(v);
            dismiss();
        }
    };


    public interface PayTypeListener {
        void typePay(View view);
    }
}
