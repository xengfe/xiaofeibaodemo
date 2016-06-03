package zkzl.xiaofeibao.recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Admin on 2015/12/21.
 */
public class StartAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName()));
    }
}
