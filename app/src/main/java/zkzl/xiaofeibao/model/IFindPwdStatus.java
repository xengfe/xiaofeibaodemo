package zkzl.xiaofeibao.model;

import zkzl.xiaofeibao.listener.IStatusCallback;

/**
 * Created by Admin on 2016/1/7.
 */
public interface IFindPwdStatus extends IStatus{

    void submit(String phone,String regNumber,String oldpwd1,String oldpwd2,IStatusCallback callback);
}
