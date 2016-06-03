package zkzl.xiaofeibao.custom;

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
public class ShareDialog extends BaseDialog {

    private ShareListener shareListener;
    public ShareDialog (Context context,ShareListener shareListener) {
        super(context, R.layout.share_pop);
        this.shareListener = shareListener;
        init ();
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

    public interface ShareListener {
        void share(View view);
    }

    private void init () {
        ImageView shareQQ = (ImageView)findViewById(R.id.share_qq);
        ImageView shareQzion = (ImageView)findViewById(R.id.share_qzion);
        ImageView shareWechat = (ImageView)findViewById(R.id.share_wechat);
        ImageView shareWechatFri = (ImageView)findViewById(R.id.share_wechat_fri);
        Button btn_cancel = (Button)findViewById(R.id.btn_share_cancle);

        shareQQ.setOnClickListener(listener);
        shareQzion.setOnClickListener(listener);
        shareWechat.setOnClickListener(listener);
        shareWechatFri.setOnClickListener(listener);
        btn_cancel.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            shareListener.share(v);
            dismiss();
        }
    };

}
