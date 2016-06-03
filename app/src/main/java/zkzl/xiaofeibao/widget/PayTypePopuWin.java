package zkzl.xiaofeibao.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2015/12/25.
 */
public class PayTypePopuWin extends PopupWindow{

    private PayTypeListener payTypeListener;

    public PayTypePopuWin (Context context,PayTypeListener payTypeListener) {
        this.payTypeListener = payTypeListener;
        View view = LayoutInflater.from(context).inflate(R.layout.pay_type_pop, null);
        ((ImageView)view.findViewById(R.id.pay_type_back)).setOnClickListener(listener);
        ((Button)view.findViewById(R.id.ali_pay_button)).setOnClickListener(listener);
        ((Button)view.findViewById(R.id.wei_chat_pay_button)).setOnClickListener(listener);
        ((Button)view.findViewById(R.id.bank_pay_button)).setOnClickListener(listener);
        ((Button)view.findViewById(R.id.xfb_pay_button)).setOnClickListener(listener);
        ((Button)view.findViewById(R.id.xfb_pay_button_2)).setOnClickListener(listener);

        this.setContentView(view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(R.style.share_popu_anim);
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
