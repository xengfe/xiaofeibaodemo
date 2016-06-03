package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.IResetPwdStatus;
import zkzl.xiaofeibao.utils.RegUtils;

/**
 * Created by Admin on 2016/1/7.
 */
public class ResetPwdStatus implements IResetPwdStatus{

    private int status = ResetPwdStatus.STATUS_DOING;
    private String message = "";

    public void resetPwd(final String phone,final String oldpwd,final String newpwd1,final String newpwd2,final IStatusCallback callback) {
        new AsyncTask<String, Void, IResetPwdStatus>(){
            @Override
            protected IResetPwdStatus doInBackground(String... params) {
                if (varify(oldpwd,newpwd1,newpwd2)){
                    try {//模拟网络请求耗时处理
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    status = ResetPwdStatus.STATUS_SUCCESS;
                    message = "修改成功！";
                }
                return ResetPwdStatus.this;
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callback.onStatus(ResetPwdStatus.this);
            }


            @Override
            protected void onPostExecute(IResetPwdStatus iResetPwdStatus) {
                super.onPostExecute(iResetPwdStatus);
                callback.onStatus(iResetPwdStatus);
            }
        }.execute();
    }


    private boolean varify (String oldpwd,String newpwd1,String newpwd2) {
        if (TextUtils.isEmpty(oldpwd)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "密码不能为空！";
            return false;
        }

        if(!RegUtils.isLength(oldpwd,8)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "旧密码位数不对！";
            return false;
        }

        if (TextUtils.isEmpty(newpwd1)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "密码不能为空！";
            return false;
        }

        if(!RegUtils.isLength(newpwd1,8)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "新密码位数不对！";
            return false;
        }

        if (TextUtils.isEmpty(newpwd2)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "确认密码不能为空！";
            return false;
        }

        if(!RegUtils.isLength(newpwd1,8)) {
            status = IResetPwdStatus.STATUS_ERROR;
            message = "确认密码位数不对！";
            return false;
        }

        if (!newpwd1.equals(newpwd2)) {
            status = IResetPwdStatus.STATUS_ERROR;
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
