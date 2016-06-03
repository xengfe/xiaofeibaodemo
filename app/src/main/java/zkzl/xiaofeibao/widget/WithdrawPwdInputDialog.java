package zkzl.xiaofeibao.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iwhys.library.widget.BaseDialog;
import com.jungly.gridpasswordview.GridPasswordView;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2016/1/13.
 */
public class WithdrawPwdInputDialog extends BaseDialog implements GridPasswordView.OnPasswordChangedListener {

    private WithdrawListener withdrawListener;


    public WithdrawPwdInputDialog(final Context context,final String msg, final WithdrawListener withdrawListener) {
        super(context, R.layout.withdraw_dialog);
        this.withdrawListener = withdrawListener;
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
        ImageButton closeButton = (ImageButton) findViewById(R.id.cancle_withdraw_button);
        closeButton.setOnClickListener(listener);
        TextView showmoney = (TextView) findViewById(R.id.withdraw_money_tv);
        showmoney.setText(msg);
        GridPasswordView gridPasswordView = (GridPasswordView) findViewById(R.id.withdraw_input_pwd);
        gridPasswordView.setOnPasswordChangedListener(this);
    }

    @Override
    public void onTextChanged(String s) {
        withdrawListener.onTextChanged(s);
    }


    @Override
    public void onInputFinish(String s) {
        withdrawListener.onInputFinish(s);
        dismiss();
    }


    public interface WithdrawListener {
        void onTextChanged(String password);

        void onInputFinish(String password);
    }




    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancle_withdraw_button:
                    dismiss();
                    break;
                default:
                    break;
            }

        }
    };





}
