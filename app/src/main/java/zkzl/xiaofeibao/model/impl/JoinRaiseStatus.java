package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IJoinRaiseStatus;

/**
 * Created by Admin on 2015/12/31.
 */
public class JoinRaiseStatus implements IJoinRaiseStatus{

    private int status = IJoinRaiseStatus.STATUS_PAY_ING;
    private String message = "";

    @Override
    public void pay(final String money,final IStatusCallback callback) {
            new AsyncTask<String,Void,IJoinRaiseStatus>() {
                @Override
                protected IJoinRaiseStatus doInBackground(String... params) {
                    if (varify(money)) {
                        try {//模拟网络请求耗时处理
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    status = IJoinRaiseStatus.STATUS_ERROR;
                    message = "支付成功";
                    return JoinRaiseStatus.this;
                }


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    callback.onStatus(JoinRaiseStatus.this);
                }


                @Override
                protected void onPostExecute(IJoinRaiseStatus iJoinRaiseStatus) {
                    super.onPostExecute(iJoinRaiseStatus);
                    callback.onStatus(iJoinRaiseStatus);
                }
            }.execute();

    }

    private boolean varify (String money) {
        if (TextUtils.isEmpty(money)) {
            message = "金额不能为空";
            return false;
        }

        return true;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
