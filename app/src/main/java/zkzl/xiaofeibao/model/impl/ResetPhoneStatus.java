package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IResetPhoneStatus;
import zkzl.xiaofeibao.utils.RegUtils;

/**
 * Created by Admin on 2016/1/7.
 */
public class ResetPhoneStatus implements IResetPhoneStatus{

    private int status = IResetPhoneStatus.STATUS_DOING;
    private String message = "";

    public void resetPhone(final String newMobile,final String reg,final IStatusCallback callback) {
        new AsyncTask<String, Void, IResetPhoneStatus>() {
            @Override
            protected IResetPhoneStatus doInBackground(String... params) {
                if(varify(newMobile,reg)) {
                    try {//模拟网络请求耗时处理
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    status = IResetPhoneStatus.STATUS_SUCCESS;
                    message = "修改成功";
                }
                return ResetPhoneStatus.this;
            }


            @Override
            protected void onPostExecute(IResetPhoneStatus iResetPhoneStatus) {
                super.onPostExecute(iResetPhoneStatus);
                callback.onStatus(iResetPhoneStatus);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callback.onStatus(ResetPhoneStatus.this);
            }
        }.execute();
    }


    private boolean varify (String phone,String reg) {
        if (TextUtils.isEmpty(phone)){
            status = IResetPhoneStatus.STATUS_ERROR;
            message = "手机能不为空！";
            return false;
        }

        if(!RegUtils.isLength(phone,11)) {
            status = IResetPhoneStatus.STATUS_ERROR;
            message = "手机必须号11位！";
            return false;
        }


        if (!RegUtils.isMobileNumber(phone)) {
            status = IResetPhoneStatus.STATUS_ERROR;
            message = "手机号不合法！";
            return false;
        }

        if (TextUtils.isEmpty(reg)){
            status = IResetPhoneStatus.STATUS_ERROR;
            message = "验证码不能为空！";
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
