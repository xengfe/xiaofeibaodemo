package zkzl.xiaofeibao.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ta.utdid2.android.utils.StringUtils;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.security.MessageDigest;

import zkzl.xiaofeibao.R;
import zkzl.xiaofeibao.bean.GetAccessTokenResult;
import zkzl.xiaofeibao.bean.GetPrepayIdResult;
import zkzl.xiaofeibao.bean.LocalRetCode;
import zkzl.xiaofeibao.widget.PayDialog;
import zkzl.xiaofeibao.widget.PayPwdDialog;
import zkzl.xiaofeibao.persenter.JoinRaisePersenter;
import zkzl.xiaofeibao.utils.Constants;
import zkzl.xiaofeibao.view.IJoinRaiseView;
import zkzl.xiaofeibao.widget.PayResultDialog;

@ContentView(R.layout.activity_join_raise)
public class JoinRaiseActivity extends BaseActivity implements
        PayPwdDialog.PayInputListener,PayPwdDialog.SelectPayTypeListener,
        PayDialog.PayTypeListener, IJoinRaiseView ,PayResultDialog.PayListener {
    private static final String TAG = JoinRaiseActivity.class.getSimpleName();

    @ViewInject(R.id.back_button)
    private Button backButton;

    @ViewInject(R.id.title)
    private TextView titleButton;

    @ViewInject(R.id.pay_button)
    private Button payButton;

    @ViewInject(R.id.join_money_tv)
    private EditText moneyEditView;

    private ProgressDialog progressDialog;
    private JoinRaisePersenter persenter;


    private IWXAPI api;
    private PayPwdDialog payPwdDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenManager.pushActivity(this);
        backButton.setVisibility(View.VISIBLE);

        String type = getIntent().getStringExtra("type");
        String title = getIntent().getStringExtra("title");

        if (!StringUtils.isEmpty(title)) {
            titleButton.setText(title);
        }
        if (!StringUtils.isEmpty(type)) {

            if (type.equals(RECHARGE)) {
                LogUtil.e("recharge doing ...");
            } else if (type.equals(JOINRAISE)) {
                LogUtil.e("joinraise doing ...");
            }
        }



        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        persenter = new JoinRaisePersenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("支付中...");


    }

    @Override
    public void toMain() {
        finish();
    }

    @Override
    public void hideLoadding() {
        progressDialog.dismiss();
    }

    @Override
    public void showLoadding() {
        progressDialog.show();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void error() {
        PayResultDialog payResultDialog = new PayResultDialog(this,"支付密码错误，请重试",this);
        payResultDialog.setCanceledOnTouchOutside(false);
        payResultDialog.show();
    }


    @Override
    public void result(View v) {
        switch (v.getId()) {
            case R.id.pay_result_try:
                payPwdDialog = new PayPwdDialog(this,"充值："+ moneyEditView.getText().toString(),this,this);
                payPwdDialog.setCanceledOnTouchOutside(false);
                payPwdDialog.show();
                break;
            case R.id.pay_result_forget:
                startOtherActivity(ACTION_FORGET_PAYPWD);
                break;
            default:break;
        }
    }

    @Event(R.id.back_button)
    private void backButtonClick(View view) {
        screenManager.popActivity(screenManager.currentActivity());
        this.finish();
        finishActivityAnimation();
    }


    @Event(R.id.pay_button)
    private void payClick(View view) {
        hideSystemKeyBoard(this,view);
        if (TextUtils.isEmpty(moneyEditView.getText().toString().trim())) {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        } else {
            payPwdDialog = new PayPwdDialog(this,"充值："+ moneyEditView.getText().toString(),this,this);
            payPwdDialog.setCanceledOnTouchOutside(false);
            payPwdDialog.show();
        }

    }

    @Override
    public void typePay(View view) {
        switch (view.getId()) {
            case R.id.ali_pay_button:
                payPwdDialog.setPayType(PayPwdDialog.PayType.ALIPAY);
                payPwdDialog.show();
                break;

            case R.id.wei_chat_pay_button:
//                new GetAccessTokenTask().execute();
                payPwdDialog.setPayType(PayPwdDialog.PayType.WXPAY);
                payPwdDialog.show();
                break;

            case R.id.bank_pay_button:
                payPwdDialog.setPayType(PayPwdDialog.PayType.BANK);
                payPwdDialog.show();
                break;

            case R.id.xfb_pay_button:
            case R.id.xfb_pay_button_2:
                payPwdDialog.setPayType(PayPwdDialog.PayType.XFBPAY);
                payPwdDialog.show();
                break;
            case R.id.pay_type_back:
                payPwdDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressed(this);
    }


    @Override
    public void onInputFinish(String password) {
        persenter.pay(moneyEditView.getText().toString().trim());
    }


    @Override
    public void onTextChanged(String password) {

    }

    @Override
    public void startForResult(int action, int result) {
        PayDialog payDialog = new PayDialog(this, this);
        payDialog.setCanceledOnTouchOutside(false);
        payDialog.show();
    }

    class GetAccessTokenTask extends AsyncTask<Void, Void, GetAccessTokenResult> {

        @Override
        protected GetAccessTokenResult doInBackground(Void... params) {
            GetAccessTokenResult result = new GetAccessTokenResult();

            String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                    Constants.APP_ID, Constants.APP_SECRET);
            Log.d(TAG, "get access token, url = " + url);

//            byte[] buf = Util.httpGet(url);
//            if (buf == null || buf.length == 0) {
//                result.localRetCode = LocalRetCode.ERR_HTTP;
//                return result;
//            }
//
//            String content = new String(buf);
//            result.parseFrom(content);
            return result;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(GetAccessTokenResult result) {
            if (result.localRetCode == LocalRetCode.ERR_OK) {
                Toast.makeText(JoinRaiseActivity.this, "", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onPostExecute, accessToken = " + result.accessToken);

                GetPrepayIdTask getPrepayId = new GetPrepayIdTask(result.accessToken);
                getPrepayId.execute();
            } else {
                Toast.makeText(JoinRaiseActivity.this, "", Toast.LENGTH_LONG).show();
            }
        }
    }


    class GetPrepayIdTask extends AsyncTask<Void, Void, GetPrepayIdResult> {

        private String accessToken;

        public GetPrepayIdTask(String accessToken) {
            this.accessToken = accessToken;
        }

        @Override
        protected GetPrepayIdResult doInBackground(Void... params) {
            String url = String.format("https://api.weixin.qq.com/pay/genprepay?access_token=%s", accessToken);
            String entity = genProductArgs();

            Log.d(TAG, "doInBackground, url = " + url);
            Log.d(TAG, "doInBackground, entity = " + entity);

            GetPrepayIdResult result = new GetPrepayIdResult();

//            byte[] buf = Util.httpPost(url, entity);
//            if (buf == null || buf.length == 0) {
//                result.localRetCode = LocalRetCode.ERR_HTTP;
//                return result;
//            }
//
//            String content = new String(buf);
//            Log.d(TAG, "doInBackground, content = " + content);
//            result.parseFrom(content);
            return result;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(GetPrepayIdResult result) {
            if (result.localRetCode == LocalRetCode.ERR_OK) {
                Toast.makeText(JoinRaiseActivity.this, "", Toast.LENGTH_LONG).show();
                sendPayReq(result);
            } else {
                Toast.makeText(JoinRaiseActivity.this, "", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void sendPayReq(GetPrepayIdResult result) {

        PayReq req = new PayReq();
        req.appId = Constants.APP_ID;
        req.partnerId = Constants.PARTNER_ID;
        req.prepayId = result.prepayId;
        req.nonceStr = nonceStr;
        req.timeStamp = String.valueOf(timeStamp);
        req.packageValue = "Sign=" + packageValue;

//        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
//        signParams.add(new BasicNameValuePair("appid", req.appId));
//        signParams.add(new BasicNameValuePair("appkey", Constants.APP_KEY));
//        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
//        signParams.add(new BasicNameValuePair("package", req.packageValue));
//        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
//        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
//        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
//        req.sign = genSign(signParams);

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    private long timeStamp;
    private String nonceStr, packageValue;

    private String genProductArgs() {
        JSONObject json = new JSONObject();

        try {
//            json.put("appid", Constants.APP_ID);
//            String traceId = getTraceId();  // traceId 由开发者自定义，可用于订单的查询与跟踪，建议根据支付用户信息生成此id
//            json.put("traceid", traceId);
//            nonceStr = genNonceStr();
//            json.put("noncestr", nonceStr);

//            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
//            packageParams.add(new BasicNameValuePair("bank_type", "WX"));
//            packageParams.add(new BasicNameValuePair("body", "千足金箍棒"));
//            packageParams.add(new BasicNameValuePair("fee_type", "1"));
//            packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
//            packageParams.add(new BasicNameValuePair("notify_url", "http://weixin.qq.com"));
//            packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo()));
//            packageParams.add(new BasicNameValuePair("partner", "1900000109"));
//            packageParams.add(new BasicNameValuePair("spbill_create_ip", "196.168.1.1"));
//            packageParams.add(new BasicNameValuePair("total_fee", "1"));
//            packageValue = genPackage(packageParams);
//
//            json.put("package", packageValue);
//            timeStamp = genTimeStamp();
//            json.put("timestamp", timeStamp);
//
//            List<NameValuePair> signParams = new LinkedList<NameValuePair>();
//            signParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
//            signParams.add(new BasicNameValuePair("appkey", Constants.APP_KEY));
//            signParams.add(new BasicNameValuePair("noncestr", nonceStr));
//            signParams.add(new BasicNameValuePair("package", packageValue));
//            signParams.add(new BasicNameValuePair("timestamp", String.valueOf(timeStamp)));
//            signParams.add(new BasicNameValuePair("traceid", traceId));
//            json.put("app_signature", genSign(signParams));

            json.put("sign_method", "sha1");
        } catch (Exception e) {
            Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
            return null;
        }

        return json.toString();
    }

//    private String genNonceStr() {
//        Random random = new Random();
//        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
//    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 建议 traceid 字段包含用户信息及订单信息，方便后续对订单状态的查询和跟踪
     */
    private String getTraceId() {
        return "crestxu_" + genTimeStamp();
    }

    /**
     * 注意：商户系统内部的订单号,32个字符内、可包含字母,确保在商户系统唯一
     */
//    private String genOutTradNo() {
//        Random random = new Random();
//        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
//    }

//    private String genPackage(List<NameValuePair> params) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < params.size(); i++) {
//            sb.append(params.get(i).getName());
//            sb.append('=');
//            sb.append(params.get(i).getValue());
//            sb.append('&');
//        }
//        sb.append("key=");
//        sb.append(Constants.PARTNER_KEY); // 注意：不能hardcode在客户端，建议genPackage这个过程都由服务器端完成
//
//        // 进行md5摘要前，params内容为原始内容，未经过url encode处理
//        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//
//        return URLEncodedUtils.format(params, "utf-8") + "&sign=" + packageSign;
//    }

//    private String genSign(List<NameValuePair> params) {
//        StringBuilder sb = new StringBuilder();
//
//        int i = 0;
//        for (; i < params.size() - 1; i++) {
//            sb.append(params.get(i).getName());
//            sb.append('=');
//            sb.append(params.get(i).getValue());
//            sb.append('&');
//        }
//        sb.append(params.get(i).getName());
//        sb.append('=');
//        sb.append(params.get(i).getValue());
//
//        String sha1 = sha1(sb.toString());
//        Log.d(TAG, "genSign, sha1 = " + sha1);
//        return sha1;
//    }

    public static String sha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes());

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
