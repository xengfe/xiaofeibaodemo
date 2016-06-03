package zkzl.xiaofeibao.custom;

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
public class VersionDialog extends BaseDialog {

    private VersionListener versionListener;
    private String message ;

    public VersionDialog (Context context,String messgae,VersionListener versionListener) {
        super(context, R.layout.version_pop);
        this.versionListener = versionListener;
        this.message = messgae;
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
        ((TextView)findViewById(R.id.dialog_context)).setText(message);
        Button okButton = (Button)findViewById(R.id.alert_ok_button);
        okButton.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            versionListener.showVersion(v);
            dismiss();
        }
    };

    public interface VersionListener {
        void showVersion(View view);
    }
}
