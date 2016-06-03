package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Pattern;

import cn.smssdk.SMSSDK;
import zkzl.xiaofeibao.listener.IStatusCallback;

import zkzl.xiaofeibao.model.IRegisterStatus;

/**
 * Created by feixiang on 2015/12/22.
 */
public class RegistStatus implements IRegisterStatus{

    private int status = IRegisterStatus.STATUS_REGIST_ING;
    private String msg = "";




    @Override
    public void regist(final String username,final String phone,final String regNum,
                       final String psw,final String affirmPwd, final IStatusCallback callback) {
        new AsyncTask<String, Void, IRegisterStatus>() {

            @Override
            protected IRegisterStatus doInBackground(String... params) {
                if (varify(username,phone,regNum,psw,affirmPwd)) {

                    try {//模拟网络请求耗时处理
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ("feifei".equals(username) && "12345678".equals(psw) && phone.equals("15010183136")) {
                        status = IRegisterStatus.STATUS_REGIST_SUCCESS;
                        msg = "注册成功！";
                    } else {
                        status = IRegisterStatus.STATUS_REGIST_FAIL;
                        msg = "注册失败！";
                    }
                }
                return RegistStatus.this;
            }

            @Override
            protected void onPostExecute(IRegisterStatus result) {
                callback.onStatus(result);
            }

            @Override
            protected void onPreExecute() {
                callback.onStatus(RegistStatus.this);
            }
        }.execute();
    }


    /**
     * 本地校验
     *
     * @return
     */
    private boolean varify(String username,String phone,String regNum, String psw,String affirmPwd) {

        if (TextUtils.isEmpty(regNum)) {
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "验证码不能为空！";
            return false;
        }

        if (TextUtils.isEmpty(psw)) {
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "密码不能为空！";
            return false;
        }

        if (TextUtils.isEmpty(affirmPwd)) {
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "确认密码不能为空！";
            return false;
        }


        if (psw.length() != 8) {
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "密码必须8位！";
            return false;
        }

        if (affirmPwd.length() != 8) {
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "确认密码必须8位！";
            return false;
        }

        if (!psw.equals(affirmPwd)){
            status = IRegisterStatus.STATUS_REGIST_VERIFY_FAIL;
            msg = "密码不一致！";
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
