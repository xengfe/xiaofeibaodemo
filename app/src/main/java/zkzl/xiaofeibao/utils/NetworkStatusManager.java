package zkzl.xiaofeibao.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhcf-01 on 2015/12/2.
 */
public class NetworkStatusManager {
    private static final Logger logger = LoggerFactory.getLogger(NetworkStatusManager.class);
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
//        logger.debug("messageReceived:"+p);
    }


    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkStatusManager.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkStatusManager.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == NetworkStatusManager.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == NetworkStatusManager.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
}
