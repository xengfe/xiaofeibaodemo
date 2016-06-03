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
public class PayPwdDialog extends BaseDialog implements GridPasswordView.OnPasswordChangedListener {

    private PayInputListener payInputListener;
    private SelectPayTypeListener selectPayTypeListener;
    private static final int SELECT_PAY = 0;
    private static final int SUCCESS = 1;
    private static final int ERROR = 2;
    private int[] icons = {R.mipmap.alipay_icon, R.mipmap.wx_icon, R.mipmap.upay_icon, R.mipmap.xfb_icon,R.mipmap.arrow_right};
    private String[] names = {"  支付宝支付", "  微信支付", "  银联支付", "  消费宝支付"};
    private Button selectPaypwd;

    public PayPwdDialog(final Context context,final String msg, final PayInputListener payInputListener, final SelectPayTypeListener selectPayTypeListener) {
        super(context, R.layout.paypwd_dialog);
        this.payInputListener = payInputListener;
        this.selectPayTypeListener = selectPayTypeListener;
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
        ImageButton closeButton = (ImageButton) findViewById(R.id.close_pay_button);
        closeButton.setOnClickListener(listener);
        TextView showmoney = (TextView) findViewById(R.id.paypwd_show_money);
        showmoney.setText(msg);
        selectPaypwd = (Button) findViewById(R.id.paypwd_select_button);
        selectPaypwd.setOnClickListener(listener);
        GridPasswordView gridPasswordView = (GridPasswordView) findViewById(R.id.paypwd_wiew);
        gridPasswordView.setOnPasswordChangedListener(this);
    }

    @Override
    public void onTextChanged(String s) {
        payInputListener.onTextChanged(s);
    }


    @Override
    public void onInputFinish(String s) {
        payInputListener.onInputFinish(s);
        dismiss();
    }


    public void setPayType(PayType payType) {

        switch (payType) {
            case ALIPAY:
                selectPaypwd.setText(names[0]);
                selectPaypwd.setCompoundDrawablesWithIntrinsicBounds(icons[0],0,icons[4],0);
                break;
            case WXPAY:
                selectPaypwd.setText(names[1]);
                selectPaypwd.setCompoundDrawablesWithIntrinsicBounds(icons[1], 0,icons[4], 0);
                break;
            case BANK:
                selectPaypwd.setText(names[2]);
                selectPaypwd.setCompoundDrawablesWithIntrinsicBounds(icons[2], 0,icons[4], 0);
                break;
            case XFBPAY:
                selectPaypwd.setText(names[3]);
                selectPaypwd.setCompoundDrawablesWithIntrinsicBounds(icons[3], 0,icons[4], 0);
                break;
            default:
                break;

        }
    }

    public interface PayInputListener {
        void onTextChanged(String password);

        void onInputFinish(String password);
    }


    public interface SelectPayTypeListener {
        void startForResult(int action, int result);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.close_pay_button:
                    dismiss();
                    break;
                case R.id.paypwd_select_button:
                    selectPayTypeListener.startForResult(SELECT_PAY, SUCCESS);
                    dismiss();
                    break;
                default:
                    break;
            }

        }
    };

    public enum PayType {
        WXPAY, ALIPAY, BANK, XFBPAY
    }



}
