package zkzl.xiaofeibao.model.impl;

import android.os.AsyncTask;
import android.text.TextUtils;

import zkzl.xiaofeibao.listener.IStatusCallback;
import zkzl.xiaofeibao.model.ISuggestStatus;

/**
 * Created by Admin on 2016/1/8.
 */
public class SuggestStatus implements ISuggestStatus{

    private int status = ISuggestStatus.STATUS_DOING;
    private String message = "";

    @Override
    public void submit(final String suggestion,final IStatusCallback callback) {
        new AsyncTask<String,Void,ISuggestStatus>(){
            @Override
            protected ISuggestStatus doInBackground(String... params) {
                if (varify(suggestion)){
                    try {//模拟网络请求耗时处理
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    status = ISuggestStatus.STATUS_SUCCESS;
                    message = "建议成功！";
                }
                return SuggestStatus.this;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callback.onStatus(SuggestStatus.this);
            }


            @Override
            protected void onPostExecute(ISuggestStatus iSuggestStatus) {
                super.onPostExecute(iSuggestStatus);
                callback.onStatus(iSuggestStatus);
            }
        }.execute();
    }


    private boolean varify(String suggestion) {
        if (TextUtils.isEmpty(suggestion)){
            status = ISuggestStatus.STATUS_ERROR;
            message = "内容不能为空！";
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
