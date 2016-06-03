package zkzl.xiaofeibao.recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"网络状态发生变化了",Toast.LENGTH_SHORT).show();

        throw new UnsupportedOperationException("Not yet implemented");



    }
}
