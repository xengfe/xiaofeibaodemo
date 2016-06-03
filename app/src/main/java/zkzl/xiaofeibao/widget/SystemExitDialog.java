package zkzl.xiaofeibao.widget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iwhys.library.widget.BaseDialog;

import zkzl.xiaofeibao.R;

/**
 * Created by Admin on 2016/1/7.
 */
public class SystemExitDialog extends BaseDialog{

    private ExitListener exitListener;
    private String messgae;


    public SystemExitDialog (Context context, String messgae, ExitListener exitListener) {
        super(context, R.layout.logout_pop);
        this.exitListener = exitListener;
        this.messgae = messgae;
        init ();
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


    private void init () {
        ((TextView)findViewById(R.id.logout_context)).setText(messgae);
        Button cancleButton = (Button)findViewById(R.id.logout_cancle_button);
        cancleButton.setOnClickListener(listener);
        Button okButton = (Button)findViewById(R.id.logout_ok_button);
        okButton.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            exitListener.exit(v);
            dismiss();
        }
    };

    public interface ExitListener {
        void exit(View view);
    }
}
