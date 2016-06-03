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
 * Created by Admin on 2016/1/14.
 */
public class WalletDialog extends BaseDialog {

    private ClickListener clickListener;

    public WalletDialog(Context context, ClickListener clickListener) {
        super(context, R.layout.wallet_dialog);
        this.clickListener = clickListener;
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
        Button tradePwdManagerBtn = (Button) findViewById(R.id.trade_log_btn);
        Button loginPwdManagerBtn = (Button) findViewById(R.id.pay_pwd_magager_btn);
        Button walletCancleBtn = (Button) findViewById(R.id.wallet_dialog_canlce_btn);

        tradePwdManagerBtn.setOnClickListener(listener);
        loginPwdManagerBtn.setOnClickListener(listener);
        walletCancleBtn.setOnClickListener(listener);

    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickListener.click(v);
            dismiss();
        }
    };


    public interface ClickListener {
        void click(View v);
    }
}
