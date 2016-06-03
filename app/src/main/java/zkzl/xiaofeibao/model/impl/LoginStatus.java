package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.regex.Pattern;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.ILoginStatus;
import zkzl.xiaofeibao.utils.SharedPreferenceManager;

/**
 * Created by feixiang on 2015/12/22.
 */
public class LoginStatus implements ILoginStatus {

    private int status = ILoginStatus.STATUS_LOGIN_ING;
    private String msg = "";

    @Override
    public void login(final String account, final String psw, final IStatusCallback callback) {
        new AsyncTask<String, Void, ILoginStatus>() {
            @Override
            protected ILoginStatus doInBackground(String... arg0) {
                if (varify(account, psw)) {
                    try {//模拟网络请求耗时处理
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ("15093457875".equals(account) && "12345678".equals(psw)) {
                        status = ILoginStatus.STATUS_LOGIN_SUCCESS;
                        msg = "登录成功！";
                    } else {
                        status = ILoginStatus.STATUS_LOGIN_FAIL;
                        msg = "登录失败！";
                    }
                }
                return LoginStatus.this;
            }

            @Override
            protected void onPreExecute() {
                callback.onStatus(LoginStatus.this);

            }

            @Override
            protected void onPostExecute(ILoginStatus result) {
                callback.onStatus(result);
            }
        }.execute();
    }


    /**
     * 本地校验
     *
     * @param account
     * @param psw
     * @return
     */
    private boolean varify(String account, String psw) {
        if (TextUtils.isEmpty(account)) {
            status = ILoginStatus.STATUS_VERIFY_FAIL;
            msg = "账号不能为空！";
            return false;
        }
        if (TextUtils.isEmpty(psw)) {
            status = ILoginStatus.STATUS_VERIFY_FAIL;
            msg = "密码不能为空！";
            return false;
        }
        if (account.length() != 11) {
            status = ILoginStatus.STATUS_VERIFY_FAIL;
            msg = "账号必须11位！";
            return false;
        }

        if(!isMobileNumber(account)) {
            status = ILoginStatus.STATUS_VERIFY_FAIL;
            msg = "手机号不合法！";
            return false;
        }

        if (psw.length() != 8) {
            status = ILoginStatus.STATUS_VERIFY_FAIL;
            msg = "密码必须8位！";
            return false;
        }

        return true;
    }


    private  boolean isMobileNumber(String mobiles) {
        return Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}")
                .matcher(mobiles).matches();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
