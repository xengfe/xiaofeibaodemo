package zkzl.xiaofeibao.model;

import zkzl.xiaofeibao.listener.IStatusCallback;

/**
 * Created by feixiang on 2015/12/22
 * <br/>
 * MVP模式的m（模型）层。
 * 登陆状态，登陆的实际逻辑实现它去完成。
 */
public interface ILoginStatus extends IStatus{

    public static final int STATUS_VERIFY_FAIL = -1;//验证失败
    public static final int STATUS_LOGIN_FAIL = -2;//登陆失败
    public static final int STATUS_LOGIN_SUCCESS = 0;//登陆成功
    public static final int STATUS_LOGIN_ING = 1;//登陆中


    /**
     * 登陆行为
     * @param account 账号
     * @param psw 密码
     * @param callback 状态码
     */
    public void login(String account,String psw,IStatusCallback callback);
}
