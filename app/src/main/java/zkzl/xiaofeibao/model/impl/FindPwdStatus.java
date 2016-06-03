package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IFindPwdStatus;
import zkzl.xiaofeibao.model.IJoinRaiseStatus;
import zkzl.xiaofeibao.utils.PhoneUtil;
import zkzl.xiaofeibao.utils.RegUtils;

/**
 * Created by Admin on 2016/1/7.
 */
public class FindPwdStatus implements IFindPwdStatus {

    private int status = IFindPwdStatus.STATUS_DOING;
    private String message = "";

    @Override
    public void submit(final String phone, final String regNumber, final String oldpwd1, final String oldpwd2, final IStatusCallback callback) {
        new AsyncTask<String, Void, IFindPwdStatus>() {
            @Override
            protected IFindPwdStatus doInBackground(String... params) {

                if (varify(phone, regNumber, oldpwd1, oldpwd2)) {
                    try {//模拟网络请求耗时处理
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    status = FindPwdStatus.STATUS_SUCCESS;
                    message = "支付成功";
                }

                return FindPwdStatus.this;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callback.onStatus(FindPwdStatus.this);
            }

            @Override
            protected void onPostExecute(IFindPwdStatus iFindPwdStatus) {
                super.onPostExecute(iFindPwdStatus);
                callback.onStatus(iFindPwdStatus);
            }
        }.execute();
    }

    private boolean varify(String phone, String regNumber, String oldpwd1, String oldpwd2) {

        if (TextUtils.isEmpty(phone)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "手机号不能为空！";
            return false;
        }


        if (!RegUtils.isLength(phone, 11)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "手机号必须11位！";
            return false;
        }

        if (!RegUtils.isMobileNumber(phone)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "手机号不合法！";
            return false;
        }

        if (TextUtils.isEmpty(regNumber)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "验证码不能为空！";
            return false;
        }

        if (TextUtils.isEmpty(oldpwd1)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "密码不能为空！";
            return false;
        }

        if(!RegUtils.isLength(oldpwd1,8)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "密码位数不对！";
            return false;
        }

        if (TextUtils.isEmpty(oldpwd2)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "确认密码不能为空！";
            return false;
        }

        if(!RegUtils.isLength(oldpwd2,8)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "确认密码位数不对！";
            return false;
        }

        if (!oldpwd1.equals(oldpwd2)) {
            status = IFindPwdStatus.STATUS_ERROR;
            message = "两次密码不相同！";
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
