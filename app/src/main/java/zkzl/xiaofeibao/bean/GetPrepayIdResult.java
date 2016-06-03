package zkzl.xiaofeibao.bean;



import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Admin on 2015/12/31.
 */
public class GetPrepayIdResult {

    private static final String TAG = "MicroMsg.SDKSample.PayActivity.GetPrepayIdResult";

    public LocalRetCode localRetCode = LocalRetCode.ERR_OTHER;
    public String prepayId;
    public int errCode;
    public String errMsg;

    public void parseFrom(String content) {

        if (content == null || content.length() <= 0) {
            localRetCode = LocalRetCode.ERR_JSON;
            return;
        }

        try {
            JSONObject json = new JSONObject(content);
            if (json.has("prepayid")) { // success case
                prepayId = json.getString("prepayid");
                localRetCode = LocalRetCode.ERR_OK;
            } else {
                localRetCode = LocalRetCode.ERR_JSON;
            }

            errCode = json.getInt("errcode");
            errMsg = json.getString("errmsg");

        } catch (Exception e) {
            localRetCode = LocalRetCode.ERR_JSON;
        }
    }
}
