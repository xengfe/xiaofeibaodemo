package zkzl.xiaofeibao.model;

import zkzl.xiaofeibao.listener.IStatusCallback;

/**
 * Created by Admin on 2015/12/31.
 */
public interface IJoinRaiseStatus extends IStatus{

    public static final int STATUS_PAY_SUCCESS = 1;//支付成功
    public static final int STATUS_PAY_FAIL = 0;//支付失败
    public static final int STATUS_PAY_ING = 2;//支付中
    public static final int STATUS_PAY_ERROR = -1;//支付异常


    void pay(String money,IStatusCallback callback);


}
